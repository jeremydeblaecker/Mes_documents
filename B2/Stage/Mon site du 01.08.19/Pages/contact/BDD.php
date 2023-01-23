<?php
include("dbconnect.php");
$expediteur=$_REQUEST['expediteur'];
$mail=$_REQUEST['mail'];
$date=new DateTime();
$date = $date->format('Y-m-d H:i:s');
$msg=$_REQUEST['message'];
 
if(empty($expediteur)) 
{ 
print("<center>'<b>Expediteur</b>' est vide !</center>"); 
} 
elseif(empty($msg)){
  print("<center>'<b>Message</b>' est vide !</center>");
}
       


$query=mysqli_query($db_connect,"INSERT INTO web (expediteur, mail, date, texte) VALUES ('$expediteur','$mail', '$date', '$msg')") or die(mysqli_error($db_connect));

mysqli_close($db_connect);
header("location:contact.php? note=success");

$to = 'jeremy.deblaecker@ynov.com'; 
$email_subject = "Vous avez été contacté par:  $expediteur";
$email_body = "Vous avez recu un nouveau message depuis le formulaire de contact de votre site.\n\n"."Nom: $expediteur\n\nEmail: $mail\n\nDate d'envoie: $date\n\nMessage:\n\n$msg";
$headers = "De: noreply@jeremydeblaecker.fr\n"; 
$headers .= "De la part de: $email_address"; 
mail($to,$email_subject,$email_body,$headers);
return true; 
?>