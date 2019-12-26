<?php

include 'koneksi.php';
$id = $_GET['id'];

 $hasil         = mysqli_query($con,"SELECT * FROM pelanggan where id_pelanggan='$id' ") or die(mysql_error());
 $json_response = array();

if(mysqli_num_rows($hasil)> 0){
 while ($row = mysqli_fetch_array($hasil)) {
     $json_response[] = $row;
 }
 echo json_encode(array('tampil_pelanggan' => $json_response));
}
?>
