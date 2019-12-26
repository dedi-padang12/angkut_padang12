<?php
include 'connection.php';
$query = mysqli_query($con, "SELECT * FROM pelanggan");
$data = array();
$qry_array = array();
$i = 0;
$total = mysqli_num_rows($query);
while ($row = mysqli_fetch_array($query)) {
  $data['id_pelanggan'] = $row['id_pelanggan'];
  $data['nama'] = $row['nama'];
  $data['no_hp'] = $row['no_hp'];
  $data['email'] = $row['email'];
  $data['alamat'] = $row['alamat'];
  $qry_array[$i] = $data;
  $i++;
}

if($query){
  $response['success'] = 'true';
  $response['message'] = 'Data Loaded Successfully';
  $response['total'] = $total;
  $response['data'] = $qry_array;
}else{
  $response['success'] = 'false';
  $response['message'] = 'Data Loading Failed';
}

echo json_encode($response);
?>
