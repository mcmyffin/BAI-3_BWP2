<link rel="stylesheet" href="../navigation/navyBar.css">
<link rel="stylesheet" href="body.css">
<link rel="stylesheet" href="../bootstrap/css/bootstrap.css" type="text/css">

<div class="container bodyContainer">


    <?php
        $name = $_POST['name'];
        echo "<b>Du hast nach dem suchbegriff: \"<u>$name</u>\" gesucht. Dadurch wurden folgende Eintr&auml;ge gefunden:</b><br /><br />";

    //* Überprüfung der Eingabe
        $abfrage = "SELECT * FROM `produkt` WHERE `Bezeichnung` LIKE '%$name%' OR `Kategorie` LIKE '%$name%' OR `ProduktID` LIKE '%$name%' ORDER BY `Bezeichnung` ASC ";
        $ergebnis = mysqli_query($db_link,$abfrage) or die(mysql_error());

        while ($ausgabe = mysqli_fetch_array( $ergebnis, MYSQL_ASSOC)) {
    ?>
            <div class="jumbotron productContainer">

                <img src="<?php echo "" . $ausgabe['Dateipfad']; ?>" alt="Bett" class="img-thumbnail productPic">

                <div class="productInfo img-thumbnail">
                    <b><?php echo "" . $ausgabe['Bezeichnung'] . "</br>"; ?></b> <?php
                    echo "" . $ausgabe['ProduktInfo'] . "</br>";
                    echo "Kategorie: " . $ausgabe['Kategorie'] . "</br>";
                    echo "Artikel-Nr.:" . $ausgabe['ProduktID'];
                    ?>
                </div>

                <div class="img-thumbnail productBestand">
                    <center><?php echo "" . $ausgabe['Artikelbestand']; ?><i> Vorhanden</i></center>
                </div>

                <div class="img-thumbnail productPreis">
                    <center><b><?php echo "" . $ausgabe['Preis']; ?> €</b></center>
                </div>

                <button class="glyphicon glyphicon-plus pruductHinzufuegen"></button>
            </div>
        <?php
        } ?>
            <a href='../index.php?page=produkte'>Zur&uuml;ck!</a>

        <?php
            if($ausgabe = mysqli_fetch_assoc($ergebnis)) {
                echo "Es wurde kein Produkt unter den Produkte \"<u>$name</u>\" gefunden.<br />
                Bitte versuche es mit einem anderen suchbegriff.<br />
                <a href='../index.php?page=produkte'>Zur&uuml;ck!</a>";
            }    // * Wenn nichts gefunden wurde, dann kommt diese Fehlermeldung.
        ?>
</div>