#include <ESP32_Servo.h>
#include <Wire.h>
#include <WiFi.h>
#include <WebServer.h>
#include <LiquidCrystal_I2C.h>
#include <ESP32Tone.h>
#include <ArduinoJson.h>
LiquidCrystal_I2C mylcd(0x27,16,2);
#include "MFRC522_I2C.h"
MFRC522 mfrc522(0x28);
Servo myservo;

#include <Adafruit_NeoPixel.h>

#define LED_PIN    26
#define LED_COUNT 4

Adafruit_NeoPixel strip(LED_COUNT, LED_PIN, NEO_GRB + NEO_KHZ800);


#define windowPin 5

String password = "";

#define btnPin 16
boolean btnFlag = 0;

const char* ssid = "";  // Mettre le ssid de votre réseau Wifi
const char* wifi_password = "";   // Mettre le mot de passe de votre

int etat_sonnerie;
int rfid_tries = 0;
unsigned long myTime;
bool is_alarm = false;


bool isRGBSpin = false;
int redSpin, greenSpin, blueSpin;

WebServer server(80);

class FanController{
    uint8_t fanPin1;
    uint8_t fanPin2;
    public:
    bool isRunning;
    FanController(): fanPin1(18), fanPin2(19), isRunning(false){ 
      pinMode(fanPin1, OUTPUT);
      pinMode(fanPin2, OUTPUT);
    }
    FanController(uint8_t _fanPin1, uint8_t _fanPin2): fanPin1(_fanPin1), fanPin2(_fanPin2), isRunning(false){ 
      pinMode(fanPin1, OUTPUT);
      pinMode(fanPin2, OUTPUT);
    }
    void setPower(int powerValue){
        if(powerValue <= 255 || powerValue >= 0){
          digitalWrite(fanPin1, HIGH);
          analogWrite(fanPin2, 255-powerValue);
          isRunning = true;    
        }  
    }
    void powerOff(){
      if(this->isRunning){
        digitalWrite(fanPin1, LOW);
        analogWrite(fanPin2, 0);  
        digitalWrite(fanPin2, LOW);  
        isRunning = false;  
      }
    }
};

class WindowController{
    Servo servoWindow;
    int servoWinPin;
    
  public:
    bool window_open = false;
    WindowController(): servoWinPin(5) {
      this->servoWindow.attach(5);
      this->servoWindow.write(0); 
    }
    WindowController(int _servoWinPin): 
    servoWinPin(_servoWinPin){
      this->servoWindow.attach(_servoWinPin); 
      this->servoWindow.write(0);   
    }
    void setWindow(int value){
      if(value >= 0 || value <= 180){
        this->servoWindow.write(value);  
      }
    }
    void openWindow(){
      if(!this->window_open) {
        this->servoWindow.write(180);
        this->window_open = true;
      }  
    }
    
    void closeWindow(){
      if(this->window_open) {
        this->servoWindow.write(0);
        this->window_open = false;
      }  
    }
};

class BuzzerControl {
    uint8_t pin;
    bool is_playing;
  public:
    BuzzerControl() : pin(25), is_playing(false) {}
    BuzzerControl(uint8_t pin) : pin(pin), is_playing(false) {
        pinMode(pin, OUTPUT);
    }
    void play_door_opened() {
      if(!this->is_playing) {
        this->is_playing = true;
        tone(this->pin,294,250,0);  //The four parameters are pin, frequency, delay and channel 
        tone(this->pin,440,250,0);
        tone(this->pin,392,250,0);
        tone(this->pin,532,250,0);
        tone(this->pin,494,250,0);
        noTone(this->pin,0); 
        this->is_playing = false;     
      }
    }

    void play_door_bell() {
      if(!this->is_playing){
        this->is_playing = true;
          tone(this->pin,660,250,0);  //The four parameters are pin, frequency, delay and channel 
          tone(this->pin,550,250,0);
          tone(this->pin,440,250,0);
          tone(this->pin,532,250,0);
          noTone(this->pin,0); 
      }  
    }

    void alarm(bool reset = false) {
      if (!reset) {
        is_alarm = true;

        tone(this->pin,660,250,0);  //The four parameters are pin, frequency, delay and channel 
        tone(this->pin,550,250,0);
        
      } else {
        noTone(this->pin,0);
        is_alarm = false;
      }
    }
};
class DoorControl {
    Servo door_servo;
    uint8_t pin;
    
    BuzzerControl buzzer;
    public:
        bool door_open;
        DoorControl() : pin(13), door_open(false) {}
        DoorControl(uint8_t pin, BuzzerControl buzzer) : pin(pin), door_open(false), buzzer(buzzer) {
            this->door_servo.attach(pin);
            this->door_servo.write(0);
        }
        void open_door() {
          if(!this->door_open){
            this->door_servo.write(180);  
            this->buzzer.play_door_opened();
            this->door_open = true;
          }
        }
        void close_door() {
          if(this->door_open){
            this->door_servo.write(0);  
            this->door_open = false;
          }
        }        
};

DoorControl door;
BuzzerControl buzzer;
WindowController window;
FanController fan;


int read_card(String cardId) {
  mylcd.setCursor(0, 0);
  if ( ! mfrc522.PICC_IsNewCardPresent() || ! mfrc522.PICC_ReadCardSerial() || millis()%1200 < 100) {
    password = "";
    return 0;
  }

  // save UID
  for (byte i = 0; i < mfrc522.uid.size; i++) {
    password = password + String(mfrc522.uid.uidByte[i]);
  }
  if(password == cardId)  //The card number is correct, open the door
  {
    Serial.println("Door open !");
    mylcd.print("Welcome back !");
    return 1;
  }
  else   //The card number is wrong，LCD displays error
  {
    password = "";
    int remain = 3-rfid_tries;
    String remain_sentence = "Error ! Remain : ";
    remain_sentence += String(remain);
    Serial.println(remain_sentence);
    return 2;
  }
}

void ShowReaderDetails() {
  byte v = mfrc522.PCD_ReadRegister(mfrc522.VersionReg);
  Serial.print(F("MFRC522 Software Version: 0x"));
  Serial.print(v, HEX);
  if (v == 0x91)
    Serial.print(F(" = v1.0"));
  else if (v == 0x92)
    Serial.print(F(" = v2.0"));
  else
    Serial.print(F(" (unknown)"));
  Serial.println("");
  // when returning to 0x00 or 0xFF, may fail to transmit communication signals
  if ((v == 0x00) || (v == 0xFF)) {
    Serial.println(F("WARNING: Communication failure, is the MFRC522 properly connected?"));
  }
}

String formatResponse(String res) {
  String response = "{";
  response += "\"res\": \"" + res + "\"}";
  return response;
}

void check_for_alarm() {
  String res;
  if (is_alarm) {
    res = formatResponse("alarm.in.process");
    server.send(403, "text/json", res);  
  }  
}

void init_endpoints() {
  server.enableCORS(true);

  server.on("/door/check", [](){
    String res;
    if(door.door_open) {
      res = formatResponse("door.opened"); 
    } else {
      res = formatResponse("door.closed");
    }
    server.send(200, "text/json", res);
  });
  
  server.on("/door", [](){
     check_for_alarm();
     String res;
     if(!door.door_open) {
        door.open_door();
        res = formatResponse("door.opened");
     } else {
        door.close_door();
        res = formatResponse("door.closed");
     }
     server.send(200, "text/json", res);
  });

  server.on("/window/check", [](){
    String res;
    if(window.window_open) {
      res = formatResponse("window.opened");  
    } else {
      res = formatResponse("window.closed");  
    }

    server.send(200, "text/json", res);
  });

  server.on("/window", [](){
    check_for_alarm();
    String res;
    if(window.window_open) {
      window.closeWindow();
      res = formatResponse("window.closed");  
    } else {
      window.openWindow();
      res = formatResponse("window.opened");  
    }
    server.send(200, "text/json", res);
  });

  server.on("/alarm", [](){
    String res;
    if(is_alarm) {
      buzzer.alarm(true);
      window.openWindow();
      rfid_tries = 0;
      res = formatResponse("alarm.deactivated");  
    } else {
      res = formatResponse("alarm.deactivated");  
    }
    server.send(200, "text/json", res);
  });

  server.on("/alarm/check", [](){
    String res;
    if(is_alarm) {
      res = formatResponse("alarm.activated");  
    } else {
      res = formatResponse("alarm.deactivated");  
    }
    server.send(200, "text/json", res);
  });

  server.on("/fan", [](){
    String res;
    for(int i=0; i < server.args(); i++) {
      if (server.argName(i) == "speed") {
        int power_value = server.arg(i).toInt();
        fan.setPower(power_value);
        res = formatResponse("fan."+power_value);
      }  
    }
    server.send(200, "text/json", res);
  });
  server.on("/fan/off", [](){
    String res = formatResponse("fan.turnOff");
    fan.powerOff();
    server.send(200, "text/json", res);
  });
  server.on("/fan/check", [](){
    String res;
    if(fan.isRunning) {
      res = formatResponse("fan.on");  
    } else {
      res = formatResponse("fan.off");  
    }
    server.send(200, "text/json", res);
  });

  server.on("/rgb", [](){
    String res;
    int pixel, red, green, blue;
    for(int i=0; i < server.args(); i++) {
      if (server.argName(i) == "colors") {
        Serial.print(server.arg(i)); 
      }
    }
    setColor(pixel, 1, 1, 1);
    res = formatResponse("Ok");  
    server.send(200, "text/json", res);
  });

  server.on("/rgb/spin", [](){
    String res;
    for(int i=0; i < server.args(); i++) {
      if (server.argName(i) == "red") {
        if(server.arg(i).toInt() <= 255 &&  server.arg(i).toInt() >= 0){
          redSpin = server.arg(i).toInt();    
        }
      }
      else if (server.argName(i) == "green") {
        if(server.arg(i).toInt() <= 255 &&  server.arg(i).toInt() >= 0){
          greenSpin = server.arg(i).toInt();    
        }
      }
      else if (server.argName(i) == "blue") {
        if(server.arg(i).toInt() <= 255 &&  server.arg(i).toInt() >= 0){
          blueSpin = server.arg(i).toInt();    
        }
      }  
    }
    isRGBSpin = true;
    res = formatResponse("Ok");
    server.send(200, "text/json", res);
  });

  server.on("/rgb/spin/off", [](){
    String res;
    isRGBSpin = false;
    res = formatResponse("spin.off");
    server.send(200, "text/json", res);
  });

  
  server.on("/rgb/spin/check", [](){
    String res;
    if(isRGBSpin){
      res = formatResponse("spin.on");  
    }else {
      res = formatResponse("spin.off");
    }
    server.send(200, "text/json", res);
  });
  
}


void setup () {
    Serial.begin(115200);
    myservo.attach(13);
    Wire.begin();
    buzzer = BuzzerControl(25);
    door = DoorControl(13, buzzer);

    

    pinMode(16, INPUT);

    mylcd.init();
    mylcd.backlight();
    mylcd.setCursor(0, 0);
    mylcd.print("");
  
    mfrc522.PCD_Init();
    ShowReaderDetails(); 
    Serial.println(F("Scan PICC to see UID, type, and data blocks..."));

    while(true) {
      int cpt = 0;
      WiFi.begin(ssid, wifi_password);
      Serial.print("Attente de connexion ...");
      while (WiFi.status() != WL_CONNECTED)
      {
        if(cpt == 20){
          break; 
        }
        
        Serial.print(".");
        delay(500);

        cpt += 1;
      }

      if(WiFi.status() == WL_CONNECTED) {break;}
    }
    
  
    Serial.print("Adresse IP: ");
    Serial.println(WiFi.localIP());

    init_endpoints();
    
    server.begin();  // Initialisation du serveur web
    Serial.println("Serveur web actif");

    //RGB
    #if defined(__AVR_ATtiny85__) && (F_CPU == 16000000)
      clock_prescale_set(clock_div_1);
    #endif
    strip.begin();           // INITIALIZE NeoPixel strip object (REQUIRED)
    strip.show();            // Turn OFF all pixels ASAP
    strip.setBrightness(50); // Set BRIGHTNESS to about 1/5 (max = 255)

}

void loop () {
      server.handleClient();
      int rfid_result = read_card("217246132226");
      etat_sonnerie = digitalRead(16);

      spinRGB();

      if(etat_sonnerie == 1) {
        buzzer.play_door_bell();  
      }

      //Serial.print("Tries : ");
      //Serial.println(rfid_tries);
      
      if (rfid_result == 1) {
        if(is_alarm){
          buzzer.alarm(true);
          window.openWindow();
          rfid_tries = 0;
        }
        door.open_door();
      }

      if (rfid_tries >= 3) {
          buzzer.alarm(false);
          window.closeWindow();  
      }

      if(rfid_result == 2) {
        rfid_tries += 1;
        door.close_door();
      }
}

void spinRGB(){
  if(isRGBSpin){
    for(int i = 0; i < LED_COUNT; i++){
      setColor(i, redSpin, greenSpin, blueSpin);
      delay(100);
    }
    for(int i = 0; i < LED_COUNT; i++){
      setColor(i, 0, 0, 0);
      delay(100);
    }  
  }
}

 void setColor(int pixel, int red, int green, int blue) {
    strip.setPixelColor(pixel, strip.Color(red, green, blue));
    strip.show();
}
