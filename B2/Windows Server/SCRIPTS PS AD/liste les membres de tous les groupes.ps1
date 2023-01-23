# Lister les membres de tous les groupes de sécurité



$lesgroupes= Get-ADGroup -filter 'GroupCategory -eq "Security"'

foreach ( $ungroupe in $lesgroupes  ) 

        {

            # lister les membres du groupe
            Get-ADGroupMember -Identity $ungroupe | select-object name, DistinguishedName | FT



        }
