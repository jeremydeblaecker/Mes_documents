
[string]$nom =read-host "Login cherché " #"test"

$lefiltre ="name -like '*$nom*'"
$lefiltre


Get-ADUser -filter $lefiltre  | FT