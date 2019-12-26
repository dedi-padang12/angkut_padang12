<?php
$server		= "localhost";
$user		= "root";
$password	= "";
$database	= "bang_angut";
	/* ====== UNTUK MENGGUNAKAN MYSQLI DI UNREMARK YANG INI, YANG MYSQL_CONNECT DI REMARK ======= */
  $con = mysqli_connect($server, $user, $password, $database); if (mysqli_connect_errno()) {
	echo "Gagal terhubung MySQL: " . mysqli_connect_error();
}

?>
