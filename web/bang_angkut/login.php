<?php
include 'koneksi.php';

	 class usr{}

	$email = $_POST["email"];
	 $password = $_POST["password"];

	 if ((empty($email)) || (empty($password))) {
	 	$response = new usr();
	 	$response->success = 0;
	 	$response->message = "Kolom tidak boleh kosong";
	 	die(json_encode($response));
	 }

	 $query = mysqli_query($con, "SELECT * FROM pelanggan WHERE email='$email' AND password='$password'");

	 $row = mysqli_fetch_array($query);

	 if (!empty($row)){
	 	$response = new usr();
 	$response->success = 1;
 	$response->message = "Selamat datang ".$row['email'];
 	$response->id_pelanggan = $row['id_pelanggan'];
		$response->email = $row['email'];
 	die(json_encode($response));

	 } else {
		$response = new usr();
	 	$response->success = 0;
	 	$response->message = "email atau password salah";
	 	die(json_encode($response));
	 }

	 mysqli_close($con);

?>
