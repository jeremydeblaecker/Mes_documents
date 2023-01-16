-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  ven. 03 déc. 2021 à 16:30
-- Version du serveur :  5.7.26
-- Version de PHP :  7.3.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `abc`
--

-- --------------------------------------------------------

--
-- Structure de la table `historique_commande`
--

DROP TABLE IF EXISTS `historique_commande`;
CREATE TABLE IF NOT EXISTS `historique_commande` (
  `id` int(25) NOT NULL AUTO_INCREMENT,
  `label` varchar(25) NOT NULL,
  `prix` double NOT NULL,
  `nom` varchar(25) NOT NULL,
  `prenom` varchar(25) NOT NULL,
  `adresse` varchar(25) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `historique_commande`
--

INSERT INTO `historique_commande` (`id`, `label`, `prix`, `nom`, `prenom`, `adresse`) VALUES
(1, 'Cras', 69, 'toto', 'toto', 'toto'),
(2, 'Cras', 69, 'Deblaecker', 'Jeremy', 'Aix en Provence'),
(3, 'odio', 129, 'Personne', 'Test', 'Marseille'),
(4, 'dictum', 93, 'toto', 'toto', 'toto');

-- --------------------------------------------------------

--
-- Structure de la table `produit`
--

DROP TABLE IF EXISTS `produit`;
CREATE TABLE IF NOT EXISTS `produit` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `label` varchar(50) NOT NULL,
  `description` varchar(150) NOT NULL,
  `img` varchar(200) NOT NULL,
  `price` decimal(20,0) NOT NULL,
  `tags` varchar(20) NOT NULL,
  `stock` int(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `produit`
--

INSERT INTO `produit` (`id`, `label`, `description`, `img`, `price`, `tags`, `stock`) VALUES
(1, 'Cras', 'Curabitur ut odio vel est tempor bibendum. Donec felis orci', '1', '69', 'support_tv', 15),
(2, 'amet', 'fames ac turpis egestas. Fusce aliquet magna a neque. Nullam', '2', '135', 'commode', 12),
(3, 'sociis', 'a, enim. Suspendisse aliquet, sem ut cursus luctus, ipsum leo', '3', '140', 'commode', 32),
(4, 'dictum', 'mattis. Integer eu lacus. Quisque imperdiet, erat nonummy ultricies ornare', '4', '93', 'commode', 52),
(5, 'nulla.', 'sit amet orci. Ut sagittis lobortis mauris. Suspendisse aliquet molestie', '5', '129', 'etagere', 6),
(6, 'magna', 'dictum cursus. Nunc mauris elit, dictum eu, eleifend nec, malesuada', '6', '113', 'support_tv', 8),
(7, 'eu', 'urna. Nullam lobortis quam a felis ullamcorper viverra. Maecenas iaculis', '7', '140', 'miroir', 10),
(8, 'odio', 'eu nibh vulputate mauris sagittis placerat. Cras dictum ultricies ligula', '8', '129', 'support_tv', 0),
(9, 'vitae', 'eu arcu. Morbi sit amet massa. Quisque porttitor eros nec', '9', '80', 'support_tv', 22),
(10, 'semper.', 'mollis lectus pede et risus. Quisque libero lacus, varius et', '10', '120', 'etagere', 444),
(11, 'eleifend,', 'montes, nascetur ridiculus mus. Proin vel arcu eu odio tristique', '11', '123', 'support_tv', 3),
(12, 'ultricies', 'sem. Pellentesque ut ipsum ac mi eleifend egestas. Sed pharetra', '12', '195', 'tv', 20);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
