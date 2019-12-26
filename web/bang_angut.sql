-- phpMyAdmin SQL Dump
-- version 4.4.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: 26 Des 2019 pada 15.33
-- Versi Server: 5.6.26
-- PHP Version: 5.6.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `bang_angut`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `akun_driver`
--

CREATE TABLE IF NOT EXISTS `akun_driver` (
  `id` int(11) NOT NULL,
  `username` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `token` text NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `akun_driver`
--

INSERT INTO `akun_driver` (`id`, `username`, `password`, `token`) VALUES
(1, 'dd', 'dd', 'fS12-ZzbrOo:APA91bHCUNJIJTsQu0_nsCKQATrC8h8l2wKZXyqw2_NOjhILNOcrHGK9ElYWdkJOk8qYMdJ4_rZA0KcuKkDvZLB5MnlBYjC1AuF2kSEmkLi5s3GGdMPqAzS-UjryFyHOEASLpaRT_Rl0');

-- --------------------------------------------------------

--
-- Struktur dari tabel `orderan`
--

CREATE TABLE IF NOT EXISTS `orderan` (
  `id_order` int(11) NOT NULL,
  `no` int(11) NOT NULL,
  `id_pelanggan` int(11) NOT NULL,
  `keterangan` varchar(50) NOT NULL,
  `berat` varchar(50) NOT NULL,
  `room_chat` varchar(100) NOT NULL,
  `id_driver` int(11) NOT NULL,
  `status` varchar(20) NOT NULL,
  `total_harga` varchar(50) NOT NULL,
  `langlong` varchar(100) NOT NULL,
  `lokasi_jemput` varchar(100) NOT NULL,
  `tanggal` date NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `orderan`
--

INSERT INTO `orderan` (`id_order`, `no`, `id_pelanggan`, `keterangan`, `berat`, `room_chat`, `id_driver`, `status`, `total_harga`, `langlong`, `lokasi_jemput`, `tanggal`) VALUES
(19, 2019, 13, 'Sampah baru', '5-10 kg', '1577361042', 1, 'proses', '12000.0', '-1.8421733711019468,109.9705223366618', 'Jl. H. Murni No.54, Tengah, Delta Pawan, Kabupaten Ketapang, Kalimantan Barat 78821, Indonesia, Indo', '2019-12-26'),
(20, 2019, 13, 'Ketapang ', '5-10 kg', '1577361564', 1, 'proses', '12000.0', '-1.8421733711019468,109.9705223366618', 'Jl. H. Murni No.54, Tengah, Delta Pawan, Kabupaten Ketapang, Kalimantan Barat 78821, Indonesia, Indo', '2019-12-26'),
(21, 2019, 13, 'Ketapang ', '5-10 kg', '1577361564', 1, 'proses', '12000.0', '-1.8421733711019468,109.9705223366618', 'Jl. H. Murni No.54, Tengah, Delta Pawan, Kabupaten Ketapang, Kalimantan Barat 78821, Indonesia, Indo', '2019-12-26'),
(22, 2019, 13, 'Tes ab', '5-10 kg', '1577365447', 1, 'proses', '12000.0', '-1.7933622979804449,109.95234567672016', 'Jl. Hayam Wuruk, Suka Bangun, Delta Pawan, Kabupaten Ketapang, Kalimantan Barat 78813, Indonesia, In', '2019-12-26');

-- --------------------------------------------------------

--
-- Struktur dari tabel `pelanggan`
--

CREATE TABLE IF NOT EXISTS `pelanggan` (
  `id_pelanggan` int(11) NOT NULL,
  `nama` varchar(100) NOT NULL,
  `no_hp` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `alamat` text NOT NULL,
  `lokasi` varchar(100) NOT NULL,
  `tgl_daftar` date NOT NULL,
  `jarak` varchar(11) NOT NULL,
  `harga_jarak` varchar(20) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `pelanggan`
--

INSERT INTO `pelanggan` (`id_pelanggan`, `nama`, `no_hp`, `email`, `password`, `alamat`, `lokasi`, `tgl_daftar`, `jarak`, `harga_jarak`) VALUES
(13, 'Dedi ', '+6281254079379', 'akun.dediwahyudi@gmail.com', '1', 'Gg. Jeruk No.19, Tengah, Delta Pawan, Kabupaten Ketapang, Kalimantan Barat 78812, Indonesia, Indonesia', '-1.8420688190109689,109.9705407768488', '2019-12-25', '2.0 km', '240000');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `akun_driver`
--
ALTER TABLE `akun_driver`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `orderan`
--
ALTER TABLE `orderan`
  ADD PRIMARY KEY (`id_order`);

--
-- Indexes for table `pelanggan`
--
ALTER TABLE `pelanggan`
  ADD PRIMARY KEY (`id_pelanggan`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `akun_driver`
--
ALTER TABLE `akun_driver`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `orderan`
--
ALTER TABLE `orderan`
  MODIFY `id_order` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=23;
--
-- AUTO_INCREMENT for table `pelanggan`
--
ALTER TABLE `pelanggan`
  MODIFY `id_pelanggan` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=14;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
