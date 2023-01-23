<?php
$db_host="db5000109013.hosting-data.io";
$db_username="dbu324224";
$db_password="Jdebla123?";
$db_name="dbs103504";


$db_connect     = mysqli_connect($db_host, $db_username, $db_password, $db_name);
//Verifier la co
if (mysqli_connect_error())
{
    echo "Erreur de co à MySQl: " . mysqli_connect_error();
}
//echo "<h1>Connected!</h1>";


?>