 #$user= Get-ADUser  -identity "administrateur" -Properties *
   $users = Get-ADUser  -filter * -Properties *

   foreach ($user in $users) {
   $timelogon =[datetime]::FromFileTime($user.lastlogon)
  $timelogoff =[datetime]::FromFileTime($user.lastlogoff)
 
  Write-Host "$user last logged on at: $timelogon "
    Write-Host "$user last logged off at: $timelogoff "


   }

 
  
