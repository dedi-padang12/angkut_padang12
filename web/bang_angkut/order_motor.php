<?php

include 'koneksi.php';

$con = mysqli_connect($HostName,$HostUser,$HostPass,$DatabaseName);



$id_pelanggan    =   $_POST['id_pelanggan'];
$berat_angkutan    =   $_POST['berat_angkutan'];
$jarak_angkutan    =   $_POST['jarak_angkutan'];
$harga_angkutan    =   $_POST['harga_angkutan'];
$keterangan    =   $_POST['keterangan'];
$harga_jarak    =   $_POST['harga_jarak'];
$total    =   $_POST['total'];
$room_chat      = $_POST['room_chat'];
$langlong      = $_POST['langlong'];
$lokasi      = $_POST['lokasi'];


$ch = curl_init("https://fcm.googleapis.com/fcm/send");
$header=array('Content-Type: application/json',
"Authorization: key= AIzaSyA2hQmd3IxZLS2REnzUTLE-55ipM-y6mRk");
curl_setopt($ch, CURLOPT_HTTPHEADER, $header);
curl_setopt( $ch,CURLOPT_SSL_VERIFYPEER, false );

$sql = " Select * from akun_driver";
$result = mysqli_query($con,$sql);



while ($row = mysqli_fetch_assoc($result)) {


  $tokens = $row["token"];


  curl_setopt($ch, CURLOPT_POST, 1);
  curl_setopt($ch, CURLOPT_POSTFIELDS, "{ \"notification\": { \"action\": \"dedi\",  \"title\": \"Orderan Baru\",    \"text\": \"$keterangan\"  },    \"to\" : \"$tokens\"}");

}



$ddd = curl_exec($ch);
curl_close($ch);






$date= date("Y-m-d");
$Sql_Query = "INSERT INTO orderan  VALUES('','$date','$id_pelanggan','$keterangan', '$berat_angkutan','$room_chat','1','proses','$total','$langlong','$lokasi','$date')";

if(mysqli_query($con,$Sql_Query)){

  echo 'Orderan Terkirim, Menunggu Konfirmasi';

}
else{

  echo 'Try Again';

}
mysqli_close($con);
?>
