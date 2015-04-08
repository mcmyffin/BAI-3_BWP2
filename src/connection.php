?php
$verbindung = mysql_connect ("localhost",
"saeed", "1234")
or die ("keine Verbindung möglich.
Benutzername oder Passwort sind falsch");

mysql_select_db("")
or die ("Die Datenbank existiert nicht.");
?>