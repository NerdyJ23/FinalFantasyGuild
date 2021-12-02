-- phpMyAdmin SQL Dump
-- version 4.0.9
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1:3306
-- Generation Time: Nov 25, 2021 at 10:34 PM
-- Server version: 5.5.34
-- PHP Version: 5.4.21

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `finalfantasy`
--
CREATE DATABASE IF NOT EXISTS `finalfantasy` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `finalfantasy`;

-- --------------------------------------------------------

--
-- Table structure for table `guild`
--

DROP TABLE IF EXISTS `guild`;
CREATE TABLE IF NOT EXISTS `guild` (
  `guildID` int(11) NOT NULL,
  `name` varchar(25) NOT NULL,
  `pvp` tinyint(1) NOT NULL,
  `region` varchar(15) NOT NULL,
  `datacenter` varchar(10) NOT NULL,
  `server` varchar(15) NOT NULL,
  `founded` date NOT NULL,
  PRIMARY KEY (`guildID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `members`
--

DROP TABLE IF EXISTS `members`;
CREATE TABLE IF NOT EXISTS `members` (
  `memberID` int(11) NOT NULL,
  `guildID` int(11) NOT NULL,
  `firstName` varchar(20) NOT NULL,
  `lastName` varchar(30) NOT NULL,
  `characterName` varchar(50) DEFAULT NULL,
  `characterRole` varchar(6) DEFAULT NULL,
  `class` varchar(27) DEFAULT NULL COMMENT 'amount of jobs and roles available',
  `region` varchar(15) NOT NULL,
  `datacenter` varchar(10) NOT NULL,
  `server` varchar(15) NOT NULL,
  `joined` date NOT NULL,
  `playerRole` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`memberID`),
  KEY `guildID` (`guildID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
