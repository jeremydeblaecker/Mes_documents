/**\file Evaluation_B1A.ino 
 * 
 * Consignes :
 * - Nom du fichier : Nom_Prénom_B1A.ino
 * - Date limite de remise : 12h00
 * - Nombre de fichier : un seul fichier .ino
 * 
 * Pour cette évaluation nous allons réaliser une plante connectée avec les fonctionnalités suivantes :
 * - Wifi : 2 Points
 *   Ma plante connectée se connecte au réseau WiFi sur le SSID d'YNOV "YNOVAIX IOT" avec le mot de 
 *   passe "9xUNb8mk7Vs3A3Y" (ou celui de votre connexion partagée).
 * - Capteurs : 4 points
 *   Ma plante connectée utilise un capteur de température et d'humidité de l'air connecté sur la broche
 *   D0 ainsi qu'un capteur d'humidité du sol connecté sur la broche D2. Ma plante relève les données 
 *   et les stocke dans des variables globales (présentes dans le code de départ):
 *   - fHumidity pour l'humidité de l'air, en pourcentage (entre 0 et 100%),
 *   - fTemperature pour la température de l'air, en degrés Celcius (entre -20 et 50°C),
 *   - iSoilMoisture pour l'humidité du sol, en pourcentage (entre 0 et 100%).
 * - Actuateur : 2 points
 *   Ma plante connectée dispose d'un actuateur qui est une pompe à eau; elle sera représentée par
 *   un LED dans le cadre de cette évaluation. On utilisera la LED du NodeMCU connectée sur la Pin D4.
 *   Il faut ici définir les informations et initialiser l'actuateur (la pompe) à l'état OFF au démarrage
 *   de la plante.
 * - Web Server : 3 points
 *   Ma plante connectée dispose d'un serveur web, accessible depuis son adresse IP sur le port 80 et
 *   affiche :
 *   - Le SSID sur lequel elle est connectée,
 *   - Les dernières valeurs des 3 données de capteur,
 *   - Un bouton qui permet d'activer la pompe à eau (la LED dans notre cas). Quand on allume la pompe 
 *     la LED s'éteind et quand j'arrête la pompe la LED s'allume,
 *   - La page web se rafraichit toutes les 10 secondes.
 * - Adafruit IO : 3 points
 *   Ma plante est connectée à la plateforme Adafruit IO. Un tableau de bord permet de visualiser : 
 *   - Les courbes d'évolution des 3 données,
 *   - Les valeurs instantannées des 3 données sont visibles par des jauges,
 *   - Un bouton permet de visualiser l'état de l'actuateur (pompe ou LED) et d'en changer l'état,
 *   - Un slider permet de configurer un niveau de température entre 0 et 50 degrés,
 *   - Un slider permet de configurer un niveau d'humidité de l'air entre 0 et 100 %.
 * - Règle métier : 3 points
 *   Ma plante connectée vérifie toutes les 30 secondes si 
 *   - la température de l'air n'est pas au dessus du niveau défini par le slider sur le tableau de bord
 *   - l'humidité du sol n'est pas au dessous du niveau défini par le slider sur le tableau de bord
 *   - Si un de ces critères n'est pas respecté alors elle active la pompe (donc éteint la LED)
 *   - Si les 2 critères sont respectés alors la pompe est arrêtée
 * - Configuration : 3 points
 *   Ma plante connectée dispose d'un fichier de configuration (sur le SPIFFS) contenant :
 *   - Le type de plante
 *   - Le taux optimal d'humidité du sol
 *   - La température athmosphérique optimale
 * - Réinitialisation : 2 points
 *   Sur la page principale du serveur web de ma plante, un bouton permet de réinitialiser les valeurs
 *   des seuils définis par les sliders 
 * - Commentaires :
 *   0,5 point pour chaque fonctionnalité implémentée correctement documentée (permettant de comprendre 
 *   les étapes et facilitant la lecture du code) et les logs sont utilies et explicites
 *   
*/

/**
 * Liste des fonctionnalités que j'ai implémentées :
 * Attention à retirer les fonctionnalités que vous n'avez pas implémentées : -1 point par fonctionnalité
 * affichée comme réalisée et qui n'est pas opérationnelle.
 * 
 * - Wifi :             2 (+0,5) points ok
 * - Capteurs :         4 (+0,5) points
 * - Actuateur :        2 (+0,5) points
 * - Web Server :       3 (+0,5) points
 * - Adafruit IO :      3 (+0,5) points
 * - Règle métier :     3 (+0,5) points
 * - Configuration :    3 (+0,5) points
 * - Réinitialisation : 2 (+0,5) points
 * - Consignes :        1 point
 */
 
// -----------------------------------------------------------------------------------------------
//Bibliotheque WIFI 
// -----------------------------------------------------------------------------------------------
#include <ESP8266WiFi.h>          //https://github.com/esp8266/Arduino Bibliothèque WIFI


// -----------------------------------------------------------------------------------------------
// Capteur d'humidité et température
// -----------------------------------------------------------------------------------------------
#define SOIL_ANALOG_PIN   A0      // PIN digitale du niveau d'humidité du sol
#define MOISTURE_HIGH     1024    // Calibration du capteur : valeur haute (sec)
#define MOISTURE_LOW      0       // Calibration du capteur : valeur basse (dans l'eau)
#define DHT_U 1                        // 0 si DHT "classique" / 1 pour DHT Unified Sensor

#include "DHT.h"                       // https://github.com/adafruit/DHT-sensor-library
#include <DHT_U.h>                     // https://github.com/adafruit/Adafruit_Sensor

#define DHT_PIN    D2                  // PIN numérique du DHT
#define DHT_TYPE   DHT11               // Type de capteur DHT : DHT11 ou DHT22

#if DHT_U
DHT_Unified myDht(DHT_PIN, DHT_TYPE); // Instantiation du DHT Unified
#else
DHT myDht(DHT_PIN, DHT_TYPE);         // Instantiation du DHT "classique"
#endif

// -----------------------------------------------------------------------------------------------
// Led
// -----------------------------------------------------------------------------------------------
#define LedPin D4            // D4 correspond également à la LED de l'ESP8266 (HIGH & LOW inversés)


// -----------------------------------------------------------------------------------------------
// Debug
// -----------------------------------------------------------------------------------------------
#define MYDEBUG 1 

#ifdef MYDEBUG
 #define MYDEBUG_PRINT(x)     Serial.print (x)
 #define MYDEBUG_PRINTDEC(x)  Serial.print (x, DEC)
 #define MYDEBUG_PRINTHEX(x)  Serial.print (x, HEX)
 #define MYDEBUG_PRINTLN(x)   Serial.println (x)
#else
 #define MYDEBUG_PRINT(x)
 #define MYDEBUG_PRINTDEC(x)
 #define MYDEBUG_PRINTHEX(x)
 #define MYDEBUG_PRINTLN(x) 
#endif

#ifndef Pins_Arduino_h
#define Pins_Arduino_h

#define PIN_WIRE_SDA (4)
#define PIN_WIRE_SCL (5)

static const uint8_t SDA = PIN_WIRE_SDA;
static const uint8_t SCL = PIN_WIRE_SCL;

#define LED_BUILTIN 2 /*!< A noter que la Led de l'ESP8266 est cablée du la broche 2 et les polarités inversées*/

static const uint8_t D0   = 16;
static const uint8_t D1   = 5;
static const uint8_t D2   = 4;
static const uint8_t D3   = 0;
static const uint8_t D4   = 2;
static const uint8_t D5   = 14;
static const uint8_t D6   = 12;
static const uint8_t D7   = 13;
static const uint8_t D8   = 15;
static const uint8_t D9   = 3;
static const uint8_t D10  = 1;

#endif 
// -----------------------------------------------------------------------------------------------
// Wifi et adafruit
// -----------------------------------------------------------------------------------------------
char* ssid     = "YNOVAIX IOT";
char* password = "9xUNb8mk7Vs3A3Y";
#define ADAFRUIT_USERNAME  "andiore"
#define AIO_KEY  "a8dbad903d5848ebad0c187860b5247b"
#define FEED_PATH ADAFRUIT_USERNAME "andiore/Dashboards/dashboard"


// -----------------------------------------------------------------------------------------------
//Capteur
// -----------------------------------------------------------------------------------------------
int iSoilMoisture;                      // Taux d'humidité du sol, mesure analogique
uint32_t delayMS = 5000;                // Délai entre 2 mesures
float fHumidity;                        // Taux d'humidité de l'air
float fTemperature;                     // Température de l'air

// -----------------------------------------------------------------------------------------------
// FONCTION WIFI
// -----------------------------------------------------------------------------------------------
void setupWiFi(){
  MYDEBUG_PRINTLN();
  MYDEBUG_PRINT("-WIFI : Connexion en cours à ");
  MYDEBUG_PRINT(ssid);
  WiFi.mode(WIFI_STA);
  WiFi.begin(ssid, password);

  while (WiFi.status() != WL_CONNECTED) {
    delay(500);
    MYDEBUG_PRINT(".");
  }
  MYDEBUG_PRINTLN("");

  MYDEBUG_PRINT("-WIFI : connecté avec l'adresse IP : ");
  MYDEBUG_PRINTLN(WiFi.localIP());
}
// Web server
WiFiServer server(80);                  // Serveur web sur le port 80 (protocole HTTP)
String header;                          // Requête HTTP 
String strActuatorState = "off";        // Etat de l'actuateur
int webInterval = 10;                   // Actualisation de la page web
// -------------------------------------------------------------------------------------------------
// Capteur d'humidité et de température
// -------------------------------------------------------------------------------------------------

void setupDhtSensor(){
  myDht.begin();

#if DHT_U
  MYDEBUG_PRINTLN("DHT Unified Sensor");
  // Information sur le capteur de température
  sensor_t sensor;
  myDht.temperature().getSensor(&sensor);
  MYDEBUG_PRINTLN("------------------------------------");
  MYDEBUG_PRINTLN("Temperature");
  MYDEBUG_PRINT  ("Sensor:       "); MYDEBUG_PRINTLN(sensor.name);
  MYDEBUG_PRINT  ("Driver Ver:   "); MYDEBUG_PRINTLN(sensor.version);
  MYDEBUG_PRINT  ("Unique ID:    "); MYDEBUG_PRINTLN(sensor.sensor_id);
  MYDEBUG_PRINT  ("Max Value:    "); MYDEBUG_PRINT(sensor.max_value); MYDEBUG_PRINTLN(" *C");
  MYDEBUG_PRINT  ("Min Value:    "); MYDEBUG_PRINT(sensor.min_value); MYDEBUG_PRINTLN(" *C");
  MYDEBUG_PRINT  ("Resolution:   "); MYDEBUG_PRINT(sensor.resolution); MYDEBUG_PRINTLN(" *C");  
  MYDEBUG_PRINTLN("------------------------------------");
  // Information sur le capteur de température
  myDht.humidity().getSensor(&sensor);
  MYDEBUG_PRINTLN("------------------------------------");
  MYDEBUG_PRINTLN("Humidity");
  MYDEBUG_PRINT  ("Sensor:       "); MYDEBUG_PRINTLN(sensor.name);
  MYDEBUG_PRINT  ("Driver Ver:   "); MYDEBUG_PRINTLN(sensor.version);
  MYDEBUG_PRINT  ("Unique ID:    "); MYDEBUG_PRINTLN(sensor.sensor_id);
  MYDEBUG_PRINT  ("Max Value:    "); MYDEBUG_PRINT(sensor.max_value); MYDEBUG_PRINTLN("%");
  MYDEBUG_PRINT  ("Min Value:    "); MYDEBUG_PRINT(sensor.min_value); MYDEBUG_PRINTLN("%");
  MYDEBUG_PRINT  ("Resolution:   "); MYDEBUG_PRINT(sensor.resolution); MYDEBUG_PRINTLN("%");  
  MYDEBUG_PRINTLN("------------------------------------");
  delayMS = sensor.min_delay/1000;
  MYDEBUG_PRINT  ("Delay:   "); MYDEBUG_PRINT(delayMS); MYDEBUG_PRINTLN(" ms");  
  MYDEBUG_PRINTLN("------------------------------------");
#endif
}

void getDhtData(){
  MYDEBUG_PRINT("-DHT : Délai entre 2 mesure [");
  MYDEBUG_PRINT(delayMS);
  MYDEBUG_PRINTLN("] ms");
  delay(delayMS);

#if DHT_U
  sensors_event_t event;  
  myDht.temperature().getEvent(&event);
  if (isnan(event.temperature)) {
    MYDEBUG_PRINTLN("-DHT : Erreur de lecture de la température du capteur DHT !");
  }
  else {
    MYDEBUG_PRINT("-DHT : [");
    MYDEBUG_PRINT(event.temperature);
    MYDEBUG_PRINTLN("°C] température !");
  }
  myDht.humidity().getEvent(&event);
  if (isnan(event.relative_humidity)) {
    MYDEBUG_PRINTLN("-DHT : Erreur de lecture de l'humidité du capteur DHT !");
  }
  else {
    MYDEBUG_PRINT("-DHT : [");
    MYDEBUG_PRINT(event.relative_humidity);
    MYDEBUG_PRINTLN("%] Humidité !");
  }
#else
  // Lecture des données sur le capteur
  float fHumidity = myDht.readHumidity();
  float fTemperature = myDht.readTemperature();

  // Vérification que la lecture est correcte
  if (isnan(fHumidity) || isnan(fTemperature)) {
    MYDEBUG_PRINTLN("-DHT : Erreur de lecture du capteur DHT !");
    return;
  }
  // Affichage
  MYDEBUG_PRINT("-DHT : [");
  MYDEBUG_PRINT(fHumidity);
  MYDEBUG_PRINTLN("%] humidité !");
  MYDEBUG_PRINT("-DHT : [");
  MYDEBUG_PRINT(fTemperature);
  MYDEBUG_PRINTLN("°C] température !");
#endif
}

void getSoilData(){
  // MOISTURE - ANALOG
  iSoilMoisture = analogRead(SOIL_ANALOG_PIN);
  iSoilMoisture = map(iSoilMoisture,MOISTURE_HIGH,MOISTURE_LOW,0,100);
  MYDEBUG_PRINT("-SOL : [");
  MYDEBUG_PRINT(iSoilMoisture);
  MYDEBUG_PRINTLN("%] humidité !");
}

// ------------------------------------------------------------------------------------------------
// LED
// ------------------------------------------------------------------------------------------------
void setup() {
  Serial.begin(115200);   // Démarrage du port série avec l'ordinateur à la vitesse de 115200 bauds
  setupWiFi();
  // Capteurs
  pinMode(SOIL_ANALOG_PIN, INPUT);  // Initialisation du capteur d'humitidé du sol
  setupDhtSensor();
}
// ------------------------------------------------------------------------------------------------
// WEB SERVER
// ------------------------------------------------------------------------------------------------


// Setup du serveyr
void setupWebServer(){
  // Initialisation de la broche pour l'actuateur
  pinMode(LedPin, OUTPUT);                    // Mode Output
  digitalWrite(LedPin, LOW);                  // Initialisation à LOW

  // On a besoin d'une connexion WiFi !
  if (WiFi.status() != WL_CONNECTED){setupWiFi();}  // Connexion WiFi
  MYDEBUG_PRINTLN("-Web Server : Démarrage");
  server.begin();                                   // Démarrage du serveur
}

/**
 * Traitement des requêtes HTTP reçues par le serveur
 */
void loopWebServer(){
  WiFiClient client = server.available();           // Attente de clients
  if (client) {                                     // Si un nouveau client se connecte
    MYDEBUG_PRINTLN("-Web Server : Nouveau client");
    String currentLine = "";                        // Initialisation d'un String pour les données du client
    while (client.connected()) {                    // Boucle tant que le client est connecté
      if (client.available()) {                     // S'il y a des données du client
        char c = client.read();                     // Lecture d'un octet
        header += c;                                // Stockage dans le header
        if (c == '\n') {                            // Si l'octet est un retour à la ligne
          // Si la ligne courante est vide, on a deux caractères de fin de ligne
          // on identifie donc la fin de la requête HTTP, il faut donc désormais répondre
          if (currentLine.length() == 0) {
            // Construction de la réponse avec le HTTP header qui commence par la code retour
            // (e.g. HTTP/1.1 200 OK) et une description du contenu pour que le client sache ce
            // qui lui arrive, et finalement une ligne vide pour indiquer la fin
            client.println("HTTP/1.1 200 OK");
            client.println("Content-type:text/html");
            client.println("Connection: close");
            client.println();
            
            // Analyse de la requête pour savoir si une action a été demandée
            if (header.indexOf("GET /LED/on") >= 0) {
              MYDEBUG_PRINTLN("-Web Server : Actuateur ON");
              strActuatorState = "on";
              digitalWrite(LedPin, HIGH);
            } else if (header.indexOf("GET /LED/off") >= 0) {
              MYDEBUG_PRINTLN("-Web Server : Actuateur OFF");
              strActuatorState = "off";
              digitalWrite(LedPin, LOW);
            }
            
            // Affichage de la page web
            client.println("<!DOCTYPE html><html>");
            client.println("<head><meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">");
            client.println("<link rel=\"icon\" href=\"data:,\">");
            client.println("<style>html { font-family: Helvetica; display: inline-block; margin: 0px auto; text-align: center;}");
            client.println(".button { background-color: #195B6A; border: none; color: white; padding: 16px 40px;");
            client.println("text-decoration: none; font-size: 30px; margin: 2px; cursor: pointer;}");
            client.println(".button2 {background-color: #77878A;}</style></head>");
            client.println("<body><h1>My Web Server</h1>");
            client.println("<p>Etat de l'actuateur : " + strActuatorState + "</p>");
            // Affichage du bouton selon l'état de l'actuateur
            if (strActuatorState=="off") {
              client.println("<p><a href=\"/LED/on\"><button class=\"button\">ON</button></a></p>");
            } else {
              client.println("<p><a href=\"/LED/off\"><button class=\"button button2\">OFF</button></a></p>");
            }
            client.println("<h3>capteurs</h3>");
            client.println("<p>Temperature: " + String(fTemperature) + "</p>");
            client.println("<p>Humidité: " + String(fHumidity) + "</p>");
            client.println("<p>Humidité sol: " + String(iSoilMoisture) + "</p>");
            client.println("</body></html>");
            // On termine avec une ligne vide pour indiquer la fin de la réponse            
            client.println();
            break;
          } else { // C'est une fin de ligne, on "nettoye" la ligne courante
            currentLine = "";
          }
        } else if (c != '\r') {  // C'est autre chose qu'un retour à la ligne
          currentLine += c;      // alors on l'ajoute à la ligne courante
        }
      }
    }
    // "Nettoyage" du header
    header = "";
    // Fermeture de la connection client
    client.stop();
    MYDEBUG_PRINTLN("-Web Server : Client déconnecté");
  }
}

void setupserver() {
  Serial.begin(115200);   // Démarrage du port série avec l'ordinateur à la vitesse de 115200 bauds

  // Wifi
  setupWiFi();
  
  // Capteurs
  pinMode(SOIL_ANALOG_PIN, INPUT);  // Initialisation du capteur d'humitidé du sol
  setupDhtSensor();

  // Web server
  setupWebServer();
}



/**
 * Fonction setup qui est appelée au démarrage de la carte, j'initialise ici les variables et
 * les capteurs. Cette fonction n'est appelée qu'une seule fois au démarrage (ou Reset).
 */



// Fonction loop qui est appellée en boucle dès que la fonction setup est terminée

void loop() {
  MYDEBUG_PRINTLN("------------------- LOOP");
  getSoilData();      // Lecture des données du capteur d'humidité du sol
  getDhtData();       // Lecture des données du capteur DHT
  delay(5000);        // Délai si nécessaire
  loopWebServer();
  delay(1000*webInterval);
}
