<?php
// Create 4 variables to store these information
    $server = 'localhost';
	$username = 'root1';
    $password = '';
    $database = 'students';
    $conn = new mysqli($server, $username, $password, $database, 3307) or die("not connected");
    echo "connected"
?>

