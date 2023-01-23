 $user= Get-ADUser  -identity "administrateur" -Properties *


 $time = $user.LastLogon

 $dt = [DateTime]::FromFileTime($time)
  Write-Host "administrateur last logged on at:" $dt 

  
  # SYNTAXE BIS
  
  
  get-aduser -filter * -identity * 
`  | format-table name,@{Label="Derniere connexion";Expression={[DateTime]::FromFileTime($_.lastlogon)}}
  
  
  