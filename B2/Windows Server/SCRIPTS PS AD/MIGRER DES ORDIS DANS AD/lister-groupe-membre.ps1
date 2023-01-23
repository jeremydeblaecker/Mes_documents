function getAdGroupMembership ($user) {
   $groups = @(Get-ADPrincipalGroupMembership -Identity $user | select -ExpandProperty distinguishedname)
   $groups
   if ($groups.count -gt 0) {
    foreach ($group in $groups) {
        getAdGroupMembership $group
        }
    }
}
 #renvoi l'ensemble des DN de chaque groupe

$recup = getAdGroupMembership 'CN=VM4,OU=Laurence,OU=SIEGE,DC=bo,DC=provencecloud,DC=com'