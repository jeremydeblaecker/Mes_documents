new-aduser `
-SamAccountName TESTMAN03 `
-UserPrincipalName 'TESTMAN03@corp.contoso.com' `
-name 'testman 03'  `
-Enabled $true -path `
'CN=Users,DC=CORP,DC=CONTOSO,DC=COM' `
-AccountPassword (ConvertTo-SecureString -AsPlainText 'formation13@' -Force) `
-ChangePasswordAtLogon $false