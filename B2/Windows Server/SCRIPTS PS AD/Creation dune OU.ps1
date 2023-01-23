
New-ADOrganizationalUnit -Name "B2" -Path  "DC=CORP,DC=HELLOYNOV,DC=COM" -ProtectedFromAccidentalDeletion $false


New-ADOrganizationalUnit -Name "B2DEV" -Path  "OU=B2,DC=CORP,DC=HELLOYNOV,DC=COM" -ProtectedFromAccidentalDeletion $false

New-ADOrganizationalUnit -Name "B2RES" -Path  "OU=B2,DC=CORP,DC=HELLOYNOV,DC=COM" -ProtectedFromAccidentalDeletion $false
