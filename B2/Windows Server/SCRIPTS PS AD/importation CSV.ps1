# Desactivation du mode erreur par defaut

$ErrorActionPreference = 'stop'


# Importer le csv dans une variable

$tableau = import-csv -Path C:\IMPORT\utilisateurs.csv -UseCulture

# affichage du résultat

$tableau | format-table

# parcourir le contenu ligne à ligne avec un for each

foreach ( $uneligne in $tableau )  {

 #write-host $uneligne.Bureau + $uneligne.Nom
 #write-host "Le bureau : $($uneligne.Bureau) "

 write-host "$($uneligne.Bureau), $($uneligne.Nom), $($uneligne.Prenom)"

$OUusers = "CN=Users,DC=LABENI,DC=lan"
$nomprenom= "$($uneligne.nom) $($uneligne.Prenom)"

try {
 Get-ADUser -Identity $uneligne.Login
 }
 catch 
    {

         New-ADUser -Name $nomprenom `        -SamAccountName $($uneligne.Login) `        -DisplayName $nomprenom `
        -Office $($uneligne.Bureau) `
        -OfficePhone $($uneligne.Telephone) `        -Organization $($uneligne.Fonction) `        -EmailAddress $($uneligne.Mail) `
        -Enabled:$true -ChangePasswordAtLogon:$true  `
        -AccountPassword (ConvertTo-SecureString `
        -AsPlainText 'formation13@' -force ) `        -Path $OUusers
    } 
 finally
    {
    #write-host "ERREUR FINALY *********************************"
       [boolean]$activer= [system.convert]::toboolean($uneligne.activer)       set-ADUser -identity $($uneligne.Login) `         -DisplayName $nomprenom `
        -Office $($uneligne.Bureau) `
        -OfficePhone $($uneligne.Telephone) `        -Organization $($uneligne.Fonction) `        -EmailAddress $($uneligne.Mail) `
        -Enabled:$activer
            
    }


}

