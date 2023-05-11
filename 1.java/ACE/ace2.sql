-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 11, 2023 at 04:23 AM
-- Server version: 10.4.27-MariaDB
-- PHP Version: 8.1.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `ace2`
--

-- --------------------------------------------------------

--
-- Table structure for table `categories_sponsor`
--

CREATE TABLE `categories_sponsor` (
  `id_cat` int(11) NOT NULL,
  `categories` varchar(20) NOT NULL,
  `id_sponsor` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `categories_sponsor`
--

INSERT INTO `categories_sponsor` (`id_cat`, `categories`, `id_sponsor`) VALUES
(6, 'food', 6),
(7, 'food', 7),
(9, 'food', 6),
(10, 'food', 6);

-- --------------------------------------------------------

--
-- Table structure for table `destination`
--

CREATE TABLE `destination` (
  `id` int(11) NOT NULL,
  `pays` varchar(25) NOT NULL,
  `ville` varchar(25) NOT NULL,
  `id_weather` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `destination`
--

INSERT INTO `destination` (`id`, `pays`, `ville`, `id_weather`) VALUES
(23, 'Arianaa', 'Ariana Ville', NULL),
(24, 'Ariana', 'Borj El Baccouch', NULL),
(25, 'Ariana', 'Borj Louzir', NULL),
(26, 'Ariana', 'Borj Touil', NULL),
(27, 'Ariana', 'Cebelet Ben Ammar', NULL),
(28, 'Ariana', 'Charguia II', NULL),
(29, 'Ariana', 'Ennasr 1', NULL),
(30, 'Ariana', 'Cite El Ghazela', NULL),
(31, 'Ariana', 'Ennasr 2', NULL),
(32, 'Ariana', 'Ettadhamen', NULL),
(33, 'Ariana', 'Kalaat Landlous', NULL),
(34, 'Ariana', 'La Soukra', NULL),
(35, 'Ariana', 'menzah 1', NULL),
(36, 'Ariana', 'Menzah 6', NULL),
(37, 'Ariana', 'Raoued', NULL),
(38, 'Ariana', 'Mnihla', NULL),
(39, 'Bizerte', 'Bizerte Nord', NULL),
(40, 'Bizerte', 'Bizerte Sud', NULL),
(41, 'Bizerte', 'El Alia', NULL),
(42, 'Bizerte', 'Ghar El Melh', NULL),
(43, 'Bizerte', 'Ghezala', NULL),
(44, 'Bizerte', 'Joumine', NULL),
(45, 'Bizerte', 'Menzel Bourguiba', NULL),
(46, 'Bizerte', 'Mateur', NULL),
(47, 'Bizerte', 'Ras Jebel', NULL),
(48, 'Bizerte', 'Utique', NULL),
(49, 'Sousse', 'Akouda', NULL),
(50, 'Sousse', 'Bou Ficha', NULL),
(51, 'Sousse', 'Enfidha', NULL),
(52, 'Sousse', 'Hammam Sousse', NULL),
(53, 'Sousse', 'Sousse Ville', NULL),
(54, 'Sousse', 'Sidi El Heni', NULL),
(55, 'Sousse', 'Hergla', NULL),
(56, 'Sfax', 'Sfax Est', NULL),
(57, 'Sfax', 'Sfax Sud', NULL),
(58, 'Sfax', 'Sfax Ville', NULL),
(59, 'Sfax', 'Mahras', NULL),
(60, 'Ben Arous', 'Mourouj 1', NULL),
(61, 'Ben Arous', 'Mourouj 3', NULL),
(62, 'Ben Arous', 'Hammam Chatt', NULL),
(63, 'Ben Arous', 'Ezzahra', NULL),
(64, 'Ben Arous', 'El Mourouj', NULL),
(65, 'Ben Arous', 'Borj Cedria', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `doctrine_migration_versions`
--

CREATE TABLE `doctrine_migration_versions` (
  `version` varchar(191) NOT NULL,
  `executed_at` datetime DEFAULT NULL,
  `execution_time` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `doctrine_migration_versions`
--

INSERT INTO `doctrine_migration_versions` (`version`, `executed_at`, `execution_time`) VALUES
('DoctrineMigrations\\Version20230416125120', '2023-04-28 23:14:35', 1786);

-- --------------------------------------------------------

--
-- Table structure for table `evenement`
--

CREATE TABLE `evenement` (
  `id_event` int(11) NOT NULL,
  `duree` double NOT NULL,
  `prix` double NOT NULL,
  `date_deb` date NOT NULL,
  `date_fin` date NOT NULL,
  `nom_event` varchar(25) NOT NULL,
  `destin_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `evenement`
--

INSERT INTO `evenement` (`id_event`, `duree`, `prix`, `date_deb`, `date_fin`, `nom_event`, `destin_id`) VALUES
(60, 1, 122, '2018-01-01', '2018-01-19', 'jci', 28),
(61, 55, 5, '2018-01-01', '2018-01-20', 'tt', 29),
(68, 2, 2, '2018-01-17', '2018-01-19', 'esprit', 30),
(69, 7, 900, '2023-05-18', '2023-05-24', 'Fete', 23),
(70, 797, 77, '2018-01-01', '2019-01-01', 'ALA', 41),
(72, 9, 690, '2018-01-01', '2019-01-01', 'aa', 23);

-- --------------------------------------------------------

--
-- Table structure for table `hotel`
--

CREATE TABLE `hotel` (
  `id` int(11) NOT NULL,
  `place_id` int(11) DEFAULT NULL,
  `nom` varchar(255) NOT NULL,
  `etoile` varchar(255) NOT NULL,
  `type` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `hotel`
--

INSERT INTO `hotel` (`id`, `place_id`, `nom`, `etoile`, `type`) VALUES
(16, 27, 'Golden Tulip', '5', 'voyage'),
(17, 39, 'Nour Congress And Resort', '4', 'Business'),
(18, 52, 'Pearl Marriott Resort And Spa', '5', 'voyage'),
(19, 53, 'Movenpick Resort', '2', 'Business'),
(20, 58, 'BUSINESS HOTEL', '4', 'Business'),
(27, 23, 'azra', '1', 'azr'),
(29, 23, 'helloks', '5', 'esoritazraz'),
(30, NULL, 'zrazaetze', '1', 'azraza'),
(31, 23, 'zarazrazr', '5', 'rrrrrrrrr');

-- --------------------------------------------------------

--
-- Table structure for table `message`
--

CREATE TABLE `message` (
  `id_message` int(11) NOT NULL,
  `id_post` int(11) DEFAULT NULL,
  `contenu` varchar(1000) NOT NULL,
  `note` bigint(20) NOT NULL DEFAULT 1,
  `created_at` datetime NOT NULL DEFAULT current_timestamp(),
  `updated_at` datetime NOT NULL DEFAULT current_timestamp(),
  `likes` int(11) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `message`
--

INSERT INTO `message` (`id_message`, `id_post`, `contenu`, `note`, `created_at`, `updated_at`, `likes`) VALUES
(8, NULL, 'good word', 5, '2023-04-26 23:54:43', '2023-04-26 23:54:43', 0),
(18, 1, 'Sousse est l\'une des meilleures!!!!', 3, '2023-04-29 04:07:06', '2023-04-29 10:34:34', 0),
(19, 1, 'je confirme personnellement..', 5, '2023-04-29 04:07:31', '2023-04-29 04:07:31', 0),
(20, 2, 'Adam Parc en Monastir', 5, '2023-04-29 04:07:57', '2023-04-29 04:07:57', 0),
(21, 2, 'DahDah??', 1, '2023-04-29 04:08:54', '2023-04-29 04:08:54', 0),
(24, 1, 'Un commentaire', 2, '2023-04-29 10:37:29', '2023-04-29 10:37:29', 0),
(25, 3, 'un autre commentaire', 5, '2023-04-29 10:38:33', '2023-04-29 10:38:53', 0),
(26, 9, 'Très agréable', 3, '2023-04-30 19:41:06', '2023-04-30 19:41:06', 0),
(27, 1, 'premier commentaire', 3, '2023-05-11 01:34:35', '2023-05-11 01:34:35', 0),
(28, 1, 'commenttttt', 4, '2023-05-11 02:55:52', '2023-05-11 02:55:52', 0),
(31, NULL, 'we posting', 4, '2023-05-11 03:14:09', '2023-05-11 03:14:09', 0),
(33, 14, 'asasasasa', 4, '2023-05-11 03:20:13', '2023-05-11 03:20:13', 0);

-- --------------------------------------------------------

--
-- Table structure for table `messenger_messages`
--

CREATE TABLE `messenger_messages` (
  `id` bigint(20) NOT NULL,
  `body` longtext NOT NULL,
  `headers` longtext NOT NULL,
  `queue_name` varchar(190) NOT NULL,
  `created_at` datetime NOT NULL,
  `available_at` datetime NOT NULL,
  `delivered_at` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `post`
--

CREATE TABLE `post` (
  `id_post` int(11) NOT NULL,
  `sujet` varchar(256) NOT NULL,
  `signaled` tinyint(1) NOT NULL DEFAULT 0,
  `view` int(11) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `post`
--

INSERT INTO `post` (`id_post`, `sujet`, `signaled`, `view`) VALUES
(1, 'Meilleure ville pour les touristes?', 0, 14),
(2, 'Parc d\'attractions en Tunisie?', 1, 4),
(3, 'Montée statistique du taux des touristes', 1, 4),
(4, 'C\'est quoi le pays le plus paisible en Afrique du nord?', 0, 0),
(5, 'Vais-je pouvoir laisser la sécurité de l\'aéoroport vérifier mes poches?', 0, 0),
(8, 'Meilleur endroit à visiter en Tunisie?', 0, 0),
(9, 'Avis sur Hammem Zriba', 0, 1),
(14, 'posteeeeee', 0, 1);

-- --------------------------------------------------------

--
-- Table structure for table `promotion`
--

CREATE TABLE `promotion` (
  `id_client` int(11) NOT NULL,
  `id_prom` int(11) NOT NULL,
  `remise` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `promotion`
--

INSERT INTO `promotion` (`id_client`, `id_prom`, `remise`) VALUES
(1, 1, 50);

-- --------------------------------------------------------

--
-- Table structure for table `publicite`
--

CREATE TABLE `publicite` (
  `id_pub` int(11) NOT NULL,
  `type` varchar(25) NOT NULL,
  `id_event` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `publicite`
--

INSERT INTO `publicite` (`id_pub`, `type`, `id_event`) VALUES
(1, 'food', 69);

-- --------------------------------------------------------

--
-- Table structure for table `reservation`
--

CREATE TABLE `reservation` (
  `id` int(11) NOT NULL,
  `id_event` int(11) DEFAULT NULL,
  `id_user` int(11) NOT NULL,
  `qte` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `reservation`
--

INSERT INTO `reservation` (`id`, `id_event`, `id_user`, `qte`) VALUES
(1, 69, 1, 100),
(2, 68, 10, 100),
(3, 60, 20, 9),
(4, 60, 20, 8),
(5, 68, 10, 9),
(6, 61, 1, 7),
(7, 61, 22, 9),
(8, 69, 10, 90),
(9, 60, 4, 1),
(10, 68, 12, 1),
(11, 60, 12, 1),
(12, NULL, 1, 888),
(13, NULL, 1, 2444),
(14, NULL, 1, 24);

-- --------------------------------------------------------

--
-- Table structure for table `reset_password_request`
--

CREATE TABLE `reset_password_request` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `selector` varchar(20) NOT NULL,
  `hashed_token` varchar(100) NOT NULL,
  `requested_at` datetime NOT NULL COMMENT '(DC2Type:datetime_immutable)',
  `expires_at` datetime NOT NULL COMMENT '(DC2Type:datetime_immutable)'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `sponsor`
--

CREATE TABLE `sponsor` (
  `id_sponsor` int(11) NOT NULL,
  `intitule` varchar(20) NOT NULL,
  `duree_contrat` int(11) NOT NULL,
  `date_debc` date NOT NULL,
  `date_finc` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `sponsor`
--

INSERT INTO `sponsor` (`id_sponsor`, `intitule`, `duree_contrat`, `date_debc`, `date_finc`) VALUES
(6, 'esprit', 2, '2018-01-01', '2024-01-01'),
(7, 'ace', 5, '2019-01-03', '2025-02-01'),
(8, 'eya', 4, '2018-01-03', '2021-01-01'),
(11, 'farah', 5, '2018-01-01', '2020-01-01'),
(12, 'farahhhhh', 5, '2018-01-01', '2020-01-01'),
(14, 'nidhal', 1, '2022-02-02', '2022-02-02'),
(16, 'azre', 453, '2018-01-01', '2028-01-01'),
(17, 'aaaaa', 888888, '2018-01-01', '2018-01-01'),
(18, 'wa', 33, '2023-05-03', '2023-05-24');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `email` varchar(180) NOT NULL,
  `roles` longtext NOT NULL COMMENT '(DC2Type:json)',
  `password` varchar(255) NOT NULL,
  `address` varchar(255) NOT NULL,
  `telephone` varchar(255) DEFAULT NULL,
  `login` varchar(255) NOT NULL,
  `nom` varchar(255) NOT NULL,
  `is_verified` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `email`, `roles`, `password`, `address`, `telephone`, `login`, `nom`, `is_verified`) VALUES
(4, 'nidhal.nar@esprit.tn', '[\"ROLE_USER\"]', '$2y$13$sxMFIjbvAbk/gom6Y2N03ufnQx3DrtTgdVfAox3.qxeGN5rUqKxTy', 'Bizerte', '20336986', 'Nidhal_', 'Nidhal', 1),
(5, 'sarra.safer@esprit.tn', '[\"ROLE_USER\"]', '$2y$13$u4ize8.8Vy9FMAIGKbQ1oOKt6pl3VtU46q.tRwyg7nVgtOUpt6jg2', 'Ariana', '95874135', 'Saroura', 'Sara', 1),
(6, 'achref.werchfeni@esprit.tn', '[\"ROLE_USER\"]', '$2y$13$IFUgKblVyjpLCEKJmmoEk.RHGRxtENm/v0QgYuHKR4PIArFRVA4Cm', 'Sousse', '50146987', 'Achref__', 'Achref', 1),
(9, 'eya.berhouma@esprit.tn', '[\"ROLE_ADMIN\"]', '$2y$13$YsU2n1Sko7xUd6VkBUj/oeke0LLKnw4/G3i59tDElYfl.4CFRjKE.', 'Nabeul', '22314894', 'Eya_Ber', 'Eya', 1),
(10, 'mohamedkhalil.boubaker@esprit.tn', '[\"ROLE_USER\"]', '$2y$13$IFUgKblVyjpLCEKJmmoEk.RHGRxtENm/v0QgYuHKR4PIArFRVA4Cm', 'Ariana', '23978511', 'Khalil_', 'Boubaker', 1),
(13, 'abdo@esprit.tn', '[\"ROLE_USER\"]', '$2y$13$aNaey6.ONET598LOPEPMV.ucTHtyMcGraugxdNEDZVyg1MNO9UYea', 'arian', '25623658', 'Abderahmen', 'abdo', 1),
(14, 'ali@esprit.tn', '[\"ROLE_USER\"]', '$2y$10$ZXR42yF2xHr4DLRITHQiYeeZmotXJKqiMOk6u21MjtcNau/hYDgfy', 'Ariana', '22223222', 'ali', 'ali', 1),
(15, 'eya.mosbeh@esprit.tn', '[\"ROLE_USER\"]', '$2y$13$clO1TIkXTlg44qU29ZEhzuB7dhX7ojCqEl8.TdaGqGIMyi7jacELq', 'Tunis', '90879875', 'Eya_', 'eya', 1),
(16, 'aziz@gmail.com', '[\"ROLE_USER\"]', '$2y$13$tuDRJYhBzXkjDWBwDT/oX.GPPCFZ2/O.XW3f2t6SthYomfmdkAHoC', 'Tunis', '46987123', 'Azizou', 'aziz', 1),
(17, 'eya_eyouta@gmail.com', '[\"ROLE_USER\"]', '$2y$13$P5UarUvSO0X3AIeIZhTwEuA/REN7psRLpGmpHBHzJqZe/nBNe4hkG', 'Tunis', '90879877', 'Eya_', 'eyouta', 1),
(18, 'sahar@esprit.tn', '[\"ROLE_USER\"]', '$2y$13$QgLeCo0HbR/59XkeEP32re0h1pycqobZmkenVZwWFUnIMTmodipLK', 'Bizerte', '50321745', 'Sahar', 'sahar', 1),
(19, 'khalil@esprit.tn', '[\"ROLE_USER\"]', '$2y$13$ichWvf7smq5lh2A0Em5H7egCIxEMBEmk850MplQC97Mml0MIg6KrK', 'Tunis', '23657897', 'ACEe', 'khalil', 1),
(20, 'faarah@esprit.tn', '[\"ROLE_USER\"]', '$2y$13$gnmpQfWMBrEcHQXQKm.3neO.S2IdMtSY6t.Yg/I3WDWZMq.CxqLr6', 'Tunis', '23159789', 'farah', 'farah', 1),
(23, 'mahdibenahmed@gmail.com', '[\"ROLE_USER\"]', '2747b72e2dd9f5701fce342774063febb3fbc422f6ba3486e0e00cf43b517869', 'Tunis', '23654789', 'Mahdi', 'mahdi', 0),
(24, 'mara.v@gmail.com', '[\"ROLE_USER\"]', '7d0a12cbce9c836caa776e03d02de2911f08e68265e5e04363fe813b06e216d5', 'maraa', '23654789', 'mara', 'maraaaaaaaaaaaaa', 0);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `categories_sponsor`
--
ALTER TABLE `categories_sponsor`
  ADD PRIMARY KEY (`id_cat`),
  ADD KEY `IDX_A98618745F1160A4` (`id_sponsor`);

--
-- Indexes for table `destination`
--
ALTER TABLE `destination`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `doctrine_migration_versions`
--
ALTER TABLE `doctrine_migration_versions`
  ADD PRIMARY KEY (`version`);

--
-- Indexes for table `evenement`
--
ALTER TABLE `evenement`
  ADD PRIMARY KEY (`id_event`),
  ADD KEY `IDX_B26681EE4656696` (`destin_id`);

--
-- Indexes for table `hotel`
--
ALTER TABLE `hotel`
  ADD PRIMARY KEY (`id`),
  ADD KEY `IDX_3535ED9DA6A219` (`place_id`);

--
-- Indexes for table `message`
--
ALTER TABLE `message`
  ADD PRIMARY KEY (`id_message`),
  ADD KEY `IDX_B6BD307FD1AA708F` (`id_post`);

--
-- Indexes for table `messenger_messages`
--
ALTER TABLE `messenger_messages`
  ADD PRIMARY KEY (`id`),
  ADD KEY `IDX_75EA56E0FB7336F0` (`queue_name`),
  ADD KEY `IDX_75EA56E0E3BD61CE` (`available_at`),
  ADD KEY `IDX_75EA56E016BA31DB` (`delivered_at`);

--
-- Indexes for table `post`
--
ALTER TABLE `post`
  ADD PRIMARY KEY (`id_post`);

--
-- Indexes for table `promotion`
--
ALTER TABLE `promotion`
  ADD PRIMARY KEY (`id_prom`);

--
-- Indexes for table `publicite`
--
ALTER TABLE `publicite`
  ADD PRIMARY KEY (`id_pub`),
  ADD KEY `id_event` (`id_event`);

--
-- Indexes for table `reservation`
--
ALTER TABLE `reservation`
  ADD PRIMARY KEY (`id`),
  ADD KEY `IDX_42C84955D52B4B97` (`id_event`);

--
-- Indexes for table `reset_password_request`
--
ALTER TABLE `reset_password_request`
  ADD PRIMARY KEY (`id`),
  ADD KEY `IDX_7CE748AA76ED395` (`user_id`);

--
-- Indexes for table `sponsor`
--
ALTER TABLE `sponsor`
  ADD PRIMARY KEY (`id_sponsor`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UNIQ_8D93D649E7927C74` (`email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `categories_sponsor`
--
ALTER TABLE `categories_sponsor`
  MODIFY `id_cat` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `destination`
--
ALTER TABLE `destination`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=69;

--
-- AUTO_INCREMENT for table `evenement`
--
ALTER TABLE `evenement`
  MODIFY `id_event` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=73;

--
-- AUTO_INCREMENT for table `hotel`
--
ALTER TABLE `hotel`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=32;

--
-- AUTO_INCREMENT for table `message`
--
ALTER TABLE `message`
  MODIFY `id_message` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=34;

--
-- AUTO_INCREMENT for table `messenger_messages`
--
ALTER TABLE `messenger_messages`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `post`
--
ALTER TABLE `post`
  MODIFY `id_post` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `promotion`
--
ALTER TABLE `promotion`
  MODIFY `id_prom` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `publicite`
--
ALTER TABLE `publicite`
  MODIFY `id_pub` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `reservation`
--
ALTER TABLE `reservation`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `reset_password_request`
--
ALTER TABLE `reset_password_request`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `sponsor`
--
ALTER TABLE `sponsor`
  MODIFY `id_sponsor` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `categories_sponsor`
--
ALTER TABLE `categories_sponsor`
  ADD CONSTRAINT `FK_A98618745F1160A4` FOREIGN KEY (`id_sponsor`) REFERENCES `sponsor` (`id_sponsor`);

--
-- Constraints for table `evenement`
--
ALTER TABLE `evenement`
  ADD CONSTRAINT `FK_B26681EE4656696` FOREIGN KEY (`destin_id`) REFERENCES `destination` (`id`);

--
-- Constraints for table `hotel`
--
ALTER TABLE `hotel`
  ADD CONSTRAINT `FK_3535ED9DA6A219` FOREIGN KEY (`place_id`) REFERENCES `destination` (`id`);

--
-- Constraints for table `message`
--
ALTER TABLE `message`
  ADD CONSTRAINT `FK_B6BD307FD1AA708F` FOREIGN KEY (`id_post`) REFERENCES `post` (`id_post`) ON DELETE CASCADE;

--
-- Constraints for table `reservation`
--
ALTER TABLE `reservation`
  ADD CONSTRAINT `FK_42C84955D52B4B97` FOREIGN KEY (`id_event`) REFERENCES `evenement` (`id_event`);

--
-- Constraints for table `reset_password_request`
--
ALTER TABLE `reset_password_request`
  ADD CONSTRAINT `FK_7CE748AA76ED395` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
