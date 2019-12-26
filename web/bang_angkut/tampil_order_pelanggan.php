<?php

include 'koneksi.php';
$id = $_GET['id'];
$query = mysqli_query($con,"SELECT * FROM orderan,pelanggan,akun_driver where orderan.id_driver=akun_driver.id and orderan.id_pelanggan=pelanggan.id_pelanggan and orderan.id_pelanggan='$id' order by orderan.id_order DESC");
$data = array();
$qry_array = array();
$i = 0;
$total = mysqli_num_rows($query);
while ($row = mysqli_fetch_array($query)) {
  $data['id'] = $row['id_order'];

  $data['keterangan'] = $row['keterangan'];
  $data['berat'] = $row['berat'];
  $data['tanggal'] = $row['tanggal'];
  $data['status'] = $row['status'];
	$data['room_chat'] = $row['room_chat'];
	$data['nama'] = $row['nama'];
  $data['total_harga'] = $row['total_harga'];
  $data['driver'] = $row['username'];
    $data['lokasi'] = $row['lokasi_jemput'];
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
