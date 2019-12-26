<?php

include 'koneksi.php';
$id = $_GET['id'];

 $hasil         = mysqli_query($con,"SELECT * FROM orderan,pelanggan where orderan.id_pelanggan=pelanggan.id_pelanggan and orderan.id_driver='$id'  order by id_order DESC") or die(mysql_error());
 $json_response = array();

if(mysqli_num_rows($hasil)> 0){
 while ($row = mysqli_fetch_array($hasil)) {
     $json_response[] = $row;
 }
 echo json_encode(array('orderan_aktif' => $json_response));
}
?>
