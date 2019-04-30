-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 30, 2019 at 07:52 PM
-- Server version: 10.1.29-MariaDB
-- PHP Version: 7.1.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `billing`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `id` int(2) NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`id`, `username`, `password`) VALUES
(1, 'afsar', 'afsar123'),
(2, 'pranita', 'pranita123'),
(3, 'raj', 'raj123'),
(4, 'aa', 'aa');

-- --------------------------------------------------------

--
-- Table structure for table `bill`
--

CREATE TABLE `bill` (
  `bill_id` int(5) NOT NULL,
  `date` date NOT NULL,
  `customer_name` varchar(20) NOT NULL,
  `emp_id` varchar(50) NOT NULL,
  `amount` decimal(10,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `bill`
--

INSERT INTO `bill` (`bill_id`, `date`, `customer_name`, `emp_id`, `amount`) VALUES
(1, '2019-04-24', 'afsar', 'eb39ea9f-4e4d-4c2c-bdcc-b62f8d54f031', '110.00'),
(2, '2019-04-24', 'raj', '344cde7f-a429-4415-a4b9-b57f5a223fb5', '55.00'),
(3, '2019-04-24', 'pranu', '344cde7f-a429-4415-a4b9-b57f5a223fb5', '100.00'),
(4, '2019-04-24', 'shweta', '344cde7f-a429-4415-a4b9-b57f5a223fb5', '55.00'),
(5, '2019-04-24', 'vishu', '344cde7f-a429-4415-a4b9-b57f5a223fb5', '10.00'),
(6, '2019-04-24', 'harsh', '344cde7f-a429-4415-a4b9-b57f5a223fb5', '45.00'),
(7, '2019-04-24', 'asd', '344cde7f-a429-4415-a4b9-b57f5a223fb5', '10.00'),
(8, '2019-04-24', 'dig', '344cde7f-a429-4415-a4b9-b57f5a223fb5', '55.00'),
(9, '2019-04-24', 'jeff', '344cde7f-a429-4415-a4b9-b57f5a223fb5', '55.00'),
(10, '2019-04-24', 'anish', '344cde7f-a429-4415-a4b9-b57f5a223fb5', '10.00'),
(11, '2019-04-24', 'craig', '344cde7f-a429-4415-a4b9-b57f5a223fb5', '135.00');

-- --------------------------------------------------------

--
-- Table structure for table `bill_prod`
--

CREATE TABLE `bill_prod` (
  `bill_id` int(3) NOT NULL,
  `prod_id` varchar(50) NOT NULL,
  `quantity` int(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `bill_prod`
--

INSERT INTO `bill_prod` (`bill_id`, `prod_id`, `quantity`) VALUES
(1, '1d9ae3a6-fc46-46ea-b69c-9e2838df2c0b', 11),
(2, '19399221-cb34-475b-96a6-bb6e19ec67e9', 1),
(2, '1d9ae3a6-fc46-46ea-b69c-9e2838df2c0b', 1),
(3, '19399221-cb34-475b-96a6-bb6e19ec67e9', 2),
(3, '1d9ae3a6-fc46-46ea-b69c-9e2838df2c0b', 1),
(4, '19399221-cb34-475b-96a6-bb6e19ec67e9', 1),
(4, '1d9ae3a6-fc46-46ea-b69c-9e2838df2c0b', 1),
(5, '1d9ae3a6-fc46-46ea-b69c-9e2838df2c0b', 1),
(6, '19399221-cb34-475b-96a6-bb6e19ec67e9', 1),
(7, '1d9ae3a6-fc46-46ea-b69c-9e2838df2c0b', 1),
(8, '19399221-cb34-475b-96a6-bb6e19ec67e9', 1),
(8, '1d9ae3a6-fc46-46ea-b69c-9e2838df2c0b', 1),
(9, '19399221-cb34-475b-96a6-bb6e19ec67e9', 1),
(9, '1d9ae3a6-fc46-46ea-b69c-9e2838df2c0b', 1),
(10, '1d9ae3a6-fc46-46ea-b69c-9e2838df2c0b', 1),
(11, '19399221-cb34-475b-96a6-bb6e19ec67e9', 3);

-- --------------------------------------------------------

--
-- Table structure for table `employee`
--

CREATE TABLE `employee` (
  `id` varchar(50) NOT NULL,
  `username` varchar(5) NOT NULL,
  `password` varchar(20) NOT NULL,
  `firstname` varchar(20) NOT NULL,
  `lastname` varchar(20) NOT NULL,
  `phone` varchar(10) NOT NULL,
  `email` varchar(50) NOT NULL,
  `address` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `employee`
--

INSERT INTO `employee` (`id`, `username`, `password`, `firstname`, `lastname`, `phone`, `email`, `address`) VALUES
('344cde7f-a429-4415-a4b9-b57f5a223fb5', 'aa', 'aa', 'aa', 'aa', 'bb', 'aa', 'bb'),
('4d6d2914-17d8-4b46-aebd-548cd3d62973', 'ee', 'ee', 'ee', 'ee', 'ee', 'ee', 'ee'),
('7f54027d-1586-4c98-b567-9b5a7389db0b', 'ff', 'ff', 'dd', 'dd', 'ff', 'ff', 'dd'),
('eb39ea9f-4e4d-4c2c-bdcc-b62f8d54f031', 'dd', 'dd', 'dd', 'dd', 'dd', 'dd', 'dd');

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE `product` (
  `id` varchar(50) NOT NULL,
  `name` varchar(20) NOT NULL,
  `barcode` varchar(13) NOT NULL,
  `quantity` int(3) NOT NULL,
  `price` decimal(10,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`id`, `name`, `barcode`, `quantity`, `price`) VALUES
('19399221-cb34-475b-96a6-bb6e19ec67e9', 'Classmate Note Book', '8902519003300', 17, '45.00'),
('1d9ae3a6-fc46-46ea-b69c-9e2838df2c0b', 'Cello Maxwriter Pen', '8904075213995', 20, '10.00');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`),
  ADD UNIQUE KEY `paswword` (`password`);

--
-- Indexes for table `bill`
--
ALTER TABLE `bill`
  ADD PRIMARY KEY (`bill_id`),
  ADD KEY `emp_id` (`emp_id`);

--
-- Indexes for table `bill_prod`
--
ALTER TABLE `bill_prod`
  ADD PRIMARY KEY (`bill_id`,`prod_id`),
  ADD KEY `prod_id` (`prod_id`);

--
-- Indexes for table `employee`
--
ALTER TABLE `employee`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `password` (`password`),
  ADD UNIQUE KEY `phone` (`phone`),
  ADD UNIQUE KEY `email` (`email`),
  ADD UNIQUE KEY `username` (`username`);

--
-- Indexes for table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`id`,`barcode`),
  ADD UNIQUE KEY `barcode` (`barcode`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admin`
--
ALTER TABLE `admin`
  MODIFY `id` int(2) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `bill`
--
ALTER TABLE `bill`
  MODIFY `bill_id` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `bill`
--
ALTER TABLE `bill`
  ADD CONSTRAINT `bill_ibfk_1` FOREIGN KEY (`emp_id`) REFERENCES `employee` (`id`);

--
-- Constraints for table `bill_prod`
--
ALTER TABLE `bill_prod`
  ADD CONSTRAINT `bill_prod_ibfk_1` FOREIGN KEY (`bill_id`) REFERENCES `bill` (`bill_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `bill_prod_ibfk_2` FOREIGN KEY (`prod_id`) REFERENCES `product` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
