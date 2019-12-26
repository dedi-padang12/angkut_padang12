<?php

include 'koneksi.php';

 $con = mysqli_connect($HostName,$HostUser,$HostPass,$DatabaseName);
$lokasi_distrik ="-1.829172,109.974507";
 $nama       = $_POST['nama'];
 $no_hp    = $_POST['no_hp'];
 $email      = $_POST['email'];
 $password     = $_POST['pass'];
 $alamat      = $_POST['alamat'];
 $lokasi    =  $_POST['lokasi'];
$tgl_now = date("Y-m-d");


//$longitude1 = ;


//$longitude2 = 109.9627144262194;

$dataJson = file_get_contents("https://maps.googleapis.com/maps/api/distancematrix/json?units=metric&origins=".$lokasi."&destinations=".$lokasi_distrik."&key=AIzaSyCHYF333AldGKP8pkcpTiy9EWnQZnFqsf8");

$data = json_decode($dataJson,true);
$nilaiJarak = $data['rows'][0]['elements'][0]['distance']['text'];
$durasi = $data['rows'][0]['elements'][0]['duration']['text'];
$hrg = $nilaiJarak * 120000;


 $Sql_Query = "INSERT INTO pelanggan  VALUES('','$nama', '$no_hp', '$email', '$password', '$alamat','$lokasi','$tgl_now','$nilaiJarak','$hrg')";

 if(mysqli_query($con,$Sql_Query)){

 echo 'Data Inserted Successfully';

 }
 else{

 echo 'Try Again';

 }
 mysqli_close($con);
?>
