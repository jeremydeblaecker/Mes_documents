<?php
function bissextile($a)
{
    $a = 2020;
    if (is_int($a/4) && !is_int($a/100) || is_int($a/400)){
    echo 'L annee $a est bissextile';
}else{
    echo 'L annee $a n est pas bissextile';
    }
}
?>  

<?php

function  biss($an){
    return ($an % 4 == 0 && (! $an % 100 !=0 || $an % 1000 == 0));
}
$line = readline('Entrer un nombre : ');
if (!is_numeric(trim($line)){
    echo "Il faut saisir un nombre ";
}else {
    echo "$line est " . biss($line)? "bissextile":"non bissextile";
}
echo"\n"
?>
210h

<?php
    $a = array('8', '5', '9', '2', '6');
    rsort($a);
    foreach ($a as $key => $val) {
        echo $a;
    }
?>

$fruits = array("lemon", "orange", "banana", "apple");
rsort($fruits);
foreach ($fruits as $key => $val) {
	echo "$key = $val\n";
}