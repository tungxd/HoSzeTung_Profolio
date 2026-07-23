<?php
	$host = "localhost";
	$user = "root";
	$password = "";
	$dbname = "treatment";
	$linkID = mysqli_connect($host, $user, $password, $dbname) or die("Could not connect to MYSQL server");
	mysqli_query($linkID, "SET NAMES 'UTF8'");
	$select_db_result = mysqli_select_db($linkID, $dbname);
?>