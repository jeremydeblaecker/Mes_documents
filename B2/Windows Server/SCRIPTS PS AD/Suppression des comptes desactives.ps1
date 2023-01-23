Get-ADUser -filter * | Where-Object  {(    $_.enabled -eq $false  )} 
| Remove-ADUser -Confirm:$false


