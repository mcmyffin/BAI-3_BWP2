<html>

<body class="basic">
        <?php
                error_reporting(E_ALL);

                // Zum Aufbau der Verbindung zur Datenbank
                define ( 'MYSQL_HOST',      'localhost' );
                define ( 'MYSQL_BENUTZER',  'root' );
                define ( 'MYSQL_KENNWORT',  '' );
                define ( 'MYSQL_DATENBANK', 'online_shop' );

                $db_link = mysqli_connect (MYSQL_HOST,
                    MYSQL_BENUTZER,
                    MYSQL_KENNWORT,
                    MYSQL_DATENBANK);

                mysqli_set_charset($db_link, 'utf8');

                if ( $db_link )
                {
                    echo 'Verbindung erfolgreich: ';

                    //print_r( $db_link);
                }
                else
                {
                    die('keine Verbindung möglich: ' . mysqli_error());
                }

                ?>

</body>
</html>







