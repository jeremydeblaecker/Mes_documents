<?php
include("dbconnect.php");
?>

<!DOCTYPE html>
<html>

<head>
    <title>Jeremy Deblaecker Contact</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="icon" type="image/png" href="../../Images/favicon1.png" />
    <link rel="stylesheet" type="text/css" media="screen" href="contact.css" />
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
    integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous" />
    <meta charset="utf-8">
  	<!-- Global site tag (gtag.js) - Google Analytics -->
    <script async src="https://www.googletagmanager.com/gtag/js?id=UA-143006450-1"></script>
    <script>
      window.dataLayer = window.dataLayer || [];
      function gtag(){dataLayer.push(arguments);}
      gtag('js', new Date());

      gtag('config', 'UA-143006450-1');
    </script>

</head>

<header>
<div class="topnav" id="myTopnav">
            <a href="../../index.html" class="active">Accueil</a>
            <a href="../cv/cv.html" class="#cv">CV</a>
            <a href="../projets/projets.html" class="#projets">Projets</a>
            <a href="../competence/competence.php" class="#competences">Compétences</a>
            <a href="contact.php" class="#contact">Contact</a>
            <a href="javascript:void(0);" style="font-size:15px;" class="icon" onclick="myFunction()">&#9776;</a>
        </div>
</header>

<body>
    <div id="nom">
<h1>Formulaire de contact<br></h1>
    </div>
<div id="formulaire">
    <div id="form-style-6">

        <form method="post" action="BDD.php">
            <label for="expediteur">Expediteur:<br></label>
            <input required type="text" name="expediteur" id="expediteur" />

            <br>

            <label for="mail">Mail:<br></label>
            <input required type="email" name="mail" id="mail" />

            <br>
            
            <label for="message">Message:</label><br />
            <input required type="text" name="message" id="message" />
            <!--<textarea name="message" rows="10" cols="50" id="message"></textarea>-->
            <br>
            <input type="submit" name="forminscription" value="Envoyer" class="submit-button" />

        </form>
    </div>
</div>

<script type="text/javascript" src="../../JS/navbar.js"></script>

</body>

</html>