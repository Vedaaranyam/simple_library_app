<?php
	$db = new mysqli("localhost","quiz","wert1234","student");
	if($db->connect_error){
		die("Connection failed:". $db->connect_error);
	}
	$q = "SELECT USRN,PWD FROM registered";
	$find = $db->query($q);
	
	if(isset($_REQUEST["USRN"])){
		$USRN = mysqli_real_escape_string($db,$_REQUEST["USRN"]);
	}
	if(isset($_REQUEST["PWD"])){
		$PWD = mysqli_real_escape_string($db,$_REQUEST["PWD"]);
	}
	$found=0;
	if($find->num_rows > 0){
		while($row = $find->fetch_assoc()){
			$usr = $row["USRN"];
			if($usr == $USRN){
				$found=1;
				break;
//chekc if pwd is correct and redirect to portal		
			}

		}
		//echo $found.$PWD;
		$pwd = $row["PWD"];
		if($pwd != $PWD){
			echo '<script language="javascript">';
			echo 'alert("Password Incorrect!")';
			echo '</script>';
			header("Refresh:0; url=login.html");
			$db->close();
			die();
		}
		if($found == 1){
			header("Refresh:0; url=portal.html");
			$db->close();
			die();
		}
	}
	
	echo '<script language="javascript">';
	echo 'alert("Not a registered Username!")';
	echo '</script>';
	header("Refresh:1; url=login.html");
	$db->close();
	die();
?>
