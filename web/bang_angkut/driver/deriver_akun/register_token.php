<?php

	if (isset($_POST["Token"])) {

		   $_uv_Token=$_POST["Token"];

		   $conn = mysqli_connect("localhost","root","","bang_angut") or die("Error connecting");

		   $q="INSERT INTO akun_driver (token) VALUES ( '$_uv_Token') "
              ." ON DUPLICATE KEY UPDATE token = '$_uv_Token';";

      mysqli_query($conn,$q) or die(mysqli_error($conn));

      mysqli_close($conn);

	}


 ?>
