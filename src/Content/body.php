<?php

    $page = $_GET["page"];
    $home = "home";
    $produkte = "produkte";
    $aktionen = "aktionen";
    $warenkorb= "warenkorb";
    $login    = "login";
    $suche    = "suche";

    if($page == $home OR $page == ""){

        include "home.html";
    }elseif($page == $produkte){

        include "product.php";
    }elseif($page == $suche){

        include "suche.php";
    }elseif($page == $aktionen){

        include "aktionen.html";

    }elseif($page == $warenkorb){

        include "warenkorb.html";

    }elseif($page = $login) {

        include "login.html";

    }else{

        include "home.html";
    }

?>