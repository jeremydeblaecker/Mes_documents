$User = Get-ADUser -Identity GlenJohn -Properties mail,department
$User.mail = "glen@fabrikam.com"
$User.department = "Accounting"
Set-ADUser -Instance $User