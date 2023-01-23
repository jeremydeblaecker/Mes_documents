Get-ADUser -filter * | Where-Object  {(    $_.name -like '*test*'  )} | Set-ADUser -Enabled $false
