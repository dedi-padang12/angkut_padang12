<?php
include 'koneksi.php';

$id = $_POST['id'];

$query = mysqli_query($con, "UPDATE  orderan SET status='cancel' WHERE id_order = '$id' ");

if($query){
  $response['success'] = 'true';
  $response['message'] = 'Cancel pesanan sukses';
}else{
  $response['success'] = 'false';
  $response['message'] = 'Cancel pesanan gagal';
}

echo json_encode($response);
?>
