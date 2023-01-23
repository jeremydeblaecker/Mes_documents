<?php
 if (isset($_POST['utilisateur']) && isset($_POST['MDP'])) { 
 $connect = new PDO('mysql:host=db5000109013.hosting-data.io;dbname=dbs103504','dbu324224','Jdebla123?');//Paramètre de la base de donnée
 $connect->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);


if (!isset($_POST['utilisateur']) || !isset($_POST['MDP'])){ //Si le MDP et utilisateur sont erronés on redirige vers la page de connexion
    header('location: connexion.html');
    exit;
  }
  $stmt = $connect->prepare('SELECT * FROM `user` WHERE `utilisateur` = :utilisateur AND `MDP` = :MDP'); //On cherche dans la BDD les informations utilisateur et MDP
  $stmt->execute(array("utilisateur" => $_POST['utilisateur'], "MDP" => $_POST['MDP']));
  $result = $stmt->fetch();

  if ($result != null){ //Si le résultat est différent de null
    $_SESSION['utilisateur'] = $_POST['utilisateur']; //Et que l'utilisateur inscrit est égal à celui de la BBD
    $_SESSION['MDP'] = $_POST['MDP']; //Et que le MDP inscrit est égal à celui de la BDD
    header('location: ../administration/administration.php'); //Alors on redirige vers la page d'admin
  }else{
    header('location: connexion.html'); //Sinon on recommence le formulaire de connexion
    exit;
  }

 }   
?>