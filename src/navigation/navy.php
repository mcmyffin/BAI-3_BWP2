
<div class="navyContainer">
    <div class="navbar navbar-default navbar-fixed-top navyContentContainer">
        <ul class="nav nav-pills">

            <?php
                $page = $_GET["page"];
                $home = "home";
                $produkte = "produkte";
                $aktionen = "aktionen";
                $warenkorb= "warenkorb";
                $login    = "login";

                if($page == "" OR $page == $home){

                    include "home_1.html";
                    include "produkte_0.html";
                    include "aktionen_0.html";
                    include "warenkorb_0.html";
                    include "login_0.html";

                }elseif($page == $produkte){

                    include "home_0.html";
                    include "produkte_1.html";
                    include "aktionen_0.html";
                    include "warenkorb_0.html";
                    include "login_0.html";

                }elseif($page == $aktionen){

                    include "home_0.html";
                    include "produkte_0.html";
                    include "aktionen_1.html";
                    include "warenkorb_0.html";
                    include "login_0.html";

                }elseif($page == $warenkorb){

                    include "home_0.html";
                    include "produkte_0.html";
                    include "aktionen_0.html";
                    include "warenkorb_1.html";
                    include "login_0.html";

                }elseif($page = $login) {

                    include "home_0.html";
                    include "produkte_0.html";
                    include "aktionen_0.html";
                    include "warenkorb_0.html";
                    include "login_1.html";

                }else{

                    include "home_1.html";
                    include "produkte_0.html";
                    include "aktionen_0.html";
                    include "warenkorb_0.html";
                    include "login_0.html";
                }

            ?>
        </ul>
    </div>
</div>


