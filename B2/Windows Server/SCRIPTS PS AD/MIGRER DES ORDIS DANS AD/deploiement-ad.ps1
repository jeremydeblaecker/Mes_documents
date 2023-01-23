function getAdGroupMembership ($user) {
   $groups = @(Get-ADPrincipalGroupMembership -Identity $user | select -ExpandProperty distinguishedname)
   $groups
   if ($groups.count -gt 0) {
    foreach ($group in $groups) {
        getAdGroupMembership $group
        }
    }
}



#read-host

# definir l'ancien ordi

$nom_ancienordi= read-host -Prompt "nom ancien ordi "

# definir le nouvel ordi
$nom_nouvelordi = read-host -Prompt "nom nouveau ordi"

# chercher l'ancien ordi

$obj_ancienordi = Get-ADComputer -Filter '*' | Where-Object {  $_.name -eq $nom_ancienordi    }


# récupérer l'OU

$DN = $obj_ancienordi.DistinguishedName

$NbreCarOrdi = $nom_ancienordi.Length

$NbreCarSubString = $NbreCarOrdi  + 4

$OU_DE_DESTINATION = $DN.Substring($NbreCarSubString)


# lister les groupes de l'ancien

$groupe_ancien_ordi= getAdGroupMembership $DN


# chercher le nouvel ordi

$obj_nouvelordi = Get-ADComputer -Filter '*' | Where-Object {  $_.name -eq $nom_nouvelordi    }

# ajouter l'ordi aux groupes
# Je liste l'ensemble des groupes lu précedememnt (DN)

foreach ($ungroupe in $groupe_ancien_ordi) 

    {

    $legroupeAD = Get-ADGroup -Identity $ungroupe 
    # JE RECUPERE L'OBJET GROUP DE l'AD CORRESPONDANT AU DN DU GROUPE

    $legroupeAD | Add-ADGroupMember -Members $obj_nouvelordi.DistinguishedName


    }






# ajouter l'ordi à l'OU

$obj_nouvelordi | Move-ADObject -TargetPath $OU_DE_DESTINATION

# suppression de l'ancien ordi de l'ad

Remove-Computer -ComputerName $nom_ancienordi -Force

# reinscription dans l'ad option 


