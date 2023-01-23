 $users= Get-ADUser  -filter * -Properties *

 $lefichier= ""

 foreach ($UnUser in $users)  {  
 
  $time = $UnUser.LastLogon
  $name = $UnUser.Name


 $dt = [DateTime]::FromFileTime($time)


 $laligne = "$name;$dt"
 $lefichier = $lefichier + $laligne + "`r`n" 
 
   }

   $lefichier | out-file -FilePath c:\lastlogon.csv
