<?php

    $page = $_GET["page"];
    $home = "home";
    $produkte = "produkte";
    $aktionen = "aktionen";
    $warenkorb= "warenkorb";
    $login    = "login";

    if($page == $home OR $page == ""){

        include "home.html";
    }elseif($page == $produkte){

        include "product.html";

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