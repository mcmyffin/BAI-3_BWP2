<link rel="stylesheet" href="navigation/navyBar.css">
<link rel="stylesheet" href="Content/body.css">
<link rel="stylesheet" href="bootstrap/css/bootstrap.css" type="text/css">
<link rel="stylesheet" href="produkte/produkte.css" type="text/css">

<div class="container bodyContainer">
    <div class="productSuche">

        <form action="../index.php?page=suche" method="post">
            Geben Sie einen Suchbegriff ein:
            <input name="name" type="text" maxlength="255" size="20" />
            <input type="submit" value="suchen" />
        </form>
    </div>

        <?php
        $sql = "SELECT * FROM produkt";

        $db_erg = mysqli_query( $db_link, $sql );
        if ( ! $db_erg )
        {
            die('Ungültige Abfrage: ' . mysqli_error());
        }
        ?>

        <?php

        while ($zeile = mysqli_fetch_array( $db_erg, MYSQL_ASSOC))
        {
        ?>

    <div class="jumbotron productContainer">


        <img src="<?php echo $zeile['Dateipfad']; ?>" alt="Bett" class="img-thumbnail productPic">
        <div class="productInfo img-thumbnail">
            <b><?php echo $zeile['Bezeichnung']."</br>";?></b>
            <?php
              echo $zeile['ProduktInfo']."</br>";
              echo "Kategorie: ".$zeile['Kategorie']."</br>";
              echo "Artikel-Nr.:". $zeile['ProduktID'];
            ?>
        </div>

        <div class="img-thumbnail productBestand">
            <center><?php echo $zeile['Artikelbestand']; ?><i>  Vorhanden</i></center>
        </div>

        <div class="img-thumbnail productPreis">
            <center><b><?php echo $zeile['Preis']; ?> €</b></center>
        </div>

        <button class="glyphicon glyphicon-plus pruductHinzufuegen"></button>
    </div>

        <?php
        }
        mysqli_free_result( $db_erg );
        ?>

</div>