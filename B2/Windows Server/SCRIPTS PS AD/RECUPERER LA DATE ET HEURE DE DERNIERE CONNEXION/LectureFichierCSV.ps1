$lefichier = import-csv -Path C:\lastlogon.csv -UseCulture -Encoding UTF8 -Header "Nom","Date logon","Actif"


#$lefichier[2].Nom


foreach ($uneligne in $lefichier)

{


write-host $uneligne.Nom, $uneligne.'Date logon',$uneligne.Actif





}
