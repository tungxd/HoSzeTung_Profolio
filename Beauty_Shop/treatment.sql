-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- 主機： 127.0.0.1
-- 產生時間： 2024-11-25 09:46:50
-- 伺服器版本： 10.4.32-MariaDB
-- PHP 版本： 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- 資料庫： `treatment`
--

-- --------------------------------------------------------

--
-- 資料表結構 `client_order`
--

CREATE TABLE `client_order` (
  `order_number` varchar(20) NOT NULL,
  `name` varchar(40) NOT NULL,
  `telephone` int(8) NOT NULL,
  `address` text DEFAULT NULL,
  `payment_method` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- 傾印資料表的資料 `client_order`
--

INSERT INTO `client_order` (`order_number`, `name`, `telephone`, `address`, `payment_method`) VALUES
('ORD17325212221981', 'Tung', 12345678, 'hoszetung2@gmail.com', 'PayMe'),
('ORD17325212411573', 'Tung', 12345678, 'hoszetung2@gmail.com', 'PayMe'),
('ORD17325213998555', 'Tung', 12345678, 'hoszetung2@gmail.com', 'PayMe'),
('ORD17325214343899', 'Tung', 12345678, 'hoszetung2@gmail.com', 'PayMe'),
('ORD17325214623175', 'Tung', 12345678, 'hoszetung2@gmail.com', 'credit_card'),
('ORD17325218435010', 'Tung', 12345678, 'hoszetung2@gmail.com', 'credit_card'),
('ORD17325219033613', 'Tung', 12345678, 'hoszetung2@gmail.com', 'credit_card'),
('ORD17325221383507', 'Tung', 12345678, 'hoszetung2@gmail.com', 'credit_card'),
('ORD17325229324584', 'Tung', 12345678, 'hoszetung2@gmail.com', 'credit_card'),
('ORD17325231212068', 'Tung', 12345678, 'hoszetung2@gmail.com', 'credit_card'),
('ORD17325231942041', 'Tung', 12345678, 'hoszetung2@gmail.com', 'credit_card');

-- --------------------------------------------------------

--
-- 資料表結構 `client_subscription`
--

CREATE TABLE `client_subscription` (
  `email` varchar(40) NOT NULL,
  `reg_date` datetime NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- 資料表結構 `order_detail`
--

CREATE TABLE `order_detail` (
  `order_code` varchar(6) NOT NULL,
  `product_id` varchar(6) DEFAULT NULL,
  `product_qty` int(3) DEFAULT NULL,
  `subtotal` int(5) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- 傾印資料表的資料 `order_detail`
--

INSERT INTO `order_detail` (`order_code`, `product_id`, `product_qty`, `subtotal`) VALUES
('ORD173', 'ORD173', 2, 760),
('ORD674', 'PROD67', 2, 580);

-- --------------------------------------------------------

--
-- 資料表結構 `treatment_record`
--

CREATE TABLE `treatment_record` (
  `record_id` varchar(10) NOT NULL,
  `treatment` varchar(40) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `phone` int(8) DEFAULT NULL,
  `email` varchar(40) DEFAULT NULL,
  `age` int(3) DEFAULT NULL,
  `gender` varchar(8) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `timeslot` time DEFAULT NULL,
  `remark` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- 已傾印資料表的索引
--

--
-- 資料表索引 `client_order`
--
ALTER TABLE `client_order`
  ADD PRIMARY KEY (`order_number`),
  ADD UNIQUE KEY `order_number` (`order_number`),
  ADD UNIQUE KEY `order_number_2` (`order_number`);

--
-- 資料表索引 `client_subscription`
--
ALTER TABLE `client_subscription`
  ADD PRIMARY KEY (`email`);

--
-- 資料表索引 `order_detail`
--
ALTER TABLE `order_detail`
  ADD PRIMARY KEY (`order_code`),
  ADD UNIQUE KEY `product_id` (`product_id`);

--
-- 資料表索引 `treatment_record`
--
ALTER TABLE `treatment_record`
  ADD PRIMARY KEY (`record_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
