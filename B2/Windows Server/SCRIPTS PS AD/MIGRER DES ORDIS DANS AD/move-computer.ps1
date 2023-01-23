# Demander au user le nom de l'ordinateur à chercher

[string]$ordi = read-host -Prompt "Nom de l'ordi à déplacer: "

# les caracteres joker sont acceptés

# Demander le DN de l'ou de destination
# OU=HERVE,OU=SIEGE,DC=bo,DC=provencecloud,DC=com
[string]$OU_DE_DESTINATION = read-host -Prompt "DN de l'OU de destination: "


# Recuperer la liste des objets et les transferer dans l'OU correspondant au DN

$lesordi = Get-ADComputer -Filter '*' | Where-Object {  $_.name -like $ordi    }

$lesordi | Move-ADObject -TargetPath $OU_DE_DESTINATION

