Get-ADuser -property * -identity "John Doe" 
get-adcomputer -property * -identity "srv1"

Get-ADComputer -Filter '*' -Properties *| format-table Name, LastLogonDate