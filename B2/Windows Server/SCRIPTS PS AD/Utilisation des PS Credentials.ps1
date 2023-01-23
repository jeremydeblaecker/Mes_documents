# génération du mot de passe en type secure string
$pw= ConvertTo-SecureString -AsPlainText -force -string "formation13@"

# générer l'identité les credentials

$cred= new-object -typename System.Management.Automation.PSCredential -ArgumentList "testman01@corp",$pw

$session = New-PSSession -ComputerName "SERVEUR2" -Credential $cred



