<html>
	<head>
		<title> das ist der sinnloseste Titel auf der scheiss Welt</title>
	</head>

	<style type="text/css">
	div.navyContainer {
		background-color: red;
		top:0px;
		left: 0px;
		width: 100%;
		height: 80px;
		position: fixed;
		z-index: 100;
	}

	div.bodyContainer{
		background-color: blue;
		
	    position:relative;
	    top:85px;
	    left: 50%;
	    width:680px;
	    height:100%;
	    margin-left: -340px;
	    z-index: 1;
	}

	div.mainBody{

	}
	
	</style>

	<body bgcolor="yellow">
		<div class="mainBody">
			<div class="navyContainer">
				home navy
			</div>
			<div class="bodyContainer">
				<?php
					$x = 10;

					while($x < 100){
						$x = $x+1;
						echo $x;
					}
				?>
				<text fontsize="70">bla</text><br><br><br><br>
			</div>
		</div>

	</body>
</html>