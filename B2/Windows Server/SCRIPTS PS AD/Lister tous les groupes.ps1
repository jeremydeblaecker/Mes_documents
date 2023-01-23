#Get-ADGroup -filter 'GroupCategory -eq "Security"' | FT

Get-ADGroup -filter 'GroupCategory -eq "Security"' | Select-Object DistinguishedName, Name | FT 
