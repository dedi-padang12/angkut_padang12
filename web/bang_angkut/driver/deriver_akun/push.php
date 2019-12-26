<?php
$ch = curl_init("https://fcm.googleapis.com/fcm/send");
$header=array('Content-Type: application/json',
"Authorization: key= AIzaSyAFiFekwfY991YkZPKDge_3LBhbVMd5zb0");
curl_setopt($ch, CURLOPT_HTTPHEADER, $header);
curl_setopt( $ch,CURLOPT_SSL_VERIFYPEER, false );

$conn = mysqli_connect("localhost","root","","bang_angut");

$sql = " Select * from akun_driver";
$result = mysqli_query($conn,$sql);



	while ($row = mysqli_fetch_assoc($result)) {


		$tokens = $row["token"];


	curl_setopt($ch, CURLOPT_POST, 1);
		curl_setopt($ch, CURLOPT_POSTFIELDS, "{ \"notification\": { \"action\": \"dedi\",  \"title\": \"Test desde curl\",    \"text\": \"Otra prueba\"  },    \"to\" : \"$tokens\"}");

	}



curl_exec($ch);
curl_close($ch);

 ?>
