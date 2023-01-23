

$lesordi = Get-ADComputer -Filter '*' | Where-Object {  $_.name -like "*VM*"    }

add-ADGroupMember -identity fred -members $lesordi

