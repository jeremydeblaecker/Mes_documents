-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  mer. 11 déc. 2019 à 11:56
-- Version du serveur :  5.7.26
-- Version de PHP :  7.2.18

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `article`
--

-- --------------------------------------------------------

--
-- Structure de la table `articles`
--

DROP TABLE IF EXISTS `articles`;
CREATE TABLE IF NOT EXISTS `articles` (
  `NOM_ARTICLES` varchar(15) NOT NULL,
  `PRIX` int(15) NOT NULL,
  `REFERENCE` int(15) NOT NULL AUTO_INCREMENT,
  `TVA` varchar(10) NOT NULL,
  `NBR_ARTICLE_STOCK` int(10) NOT NULL,
  `NBR_ARTICLE_VENDU` int(10) NOT NULL,
  `CODE_BARRE` int(10) NOT NULL,
  PRIMARY KEY (`REFERENCE`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `articles`
--

INSERT INTO `articles` (`NOM_ARTICLES`, `PRIX`, `REFERENCE`, `TVA`, `NBR_ARTICLE_STOCK`, `NBR_ARTICLE_VENDU`, `CODE_BARRE`) VALUES
('Pomme', 20, 2, '20%', 50, 10, 205151198),
('Poire', 5, 4, '20%', 25353, 5353, 5335);

-- --------------------------------------------------------

--
-- Structure de la table `tva`
--

DROP TABLE IF EXISTS `tva`;
CREATE TABLE IF NOT EXISTS `tva` (
  `TVA` varchar(10) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `tva`
--

INSERT INTO `tva` (`TVA`) VALUES
('5.5%'),
('20%');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
