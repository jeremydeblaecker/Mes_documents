
[string]$nom =read-host "Login cherch� " #"test"

$lefiltre ="name -like '*$nom*'"
$lefiltre


Get-ADUser -filter $lefiltre  | FT