-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : dim. 30 avr. 2023 à 19:54
-- Version du serveur : 10.4.27-MariaDB
-- Version de PHP : 8.1.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `ace`
--

-- --------------------------------------------------------

--
-- Structure de la table `categories_sponsor`
--

CREATE TABLE `categories_sponsor` (
  `id_cat` int(11) NOT NULL,
  `id_sponsor` int(11) DEFAULT NULL,
  `categories` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `categories_sponsor`
--

INSERT INTO `categories_sponsor` (`id_cat`, `id_sponsor`, `categories`) VALUES
(5, 7, 'clothes'),
(6, 6, 'food'),
(7, 7, 'food'),
(9, 6, 'food'),
(10, 6, 'food');

-- --------------------------------------------------------

--
-- Structure de la table `destination`
--

CREATE TABLE `destination` (
  `id` int(11) NOT NULL,
  `pays` varchar(25) NOT NULL,
  `ville` varchar(25) NOT NULL,
  `id_weather` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `destination`
--

INSERT INTO `destination` (`id`, `pays`, `ville`, `id_weather`) VALUES
(23, 'Ariana', 'Ariana Ville', NULL),
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
-- Structure de la table `doctrine_migration_versions`
--

CREATE TABLE `doctrine_migration_versions` (
  `version` varchar(191) NOT NULL,
  `executed_at` datetime DEFAULT NULL,
  `execution_time` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `doctrine_migration_versions`
--

INSERT INTO `doctrine_migration_versions` (`version`, `executed_at`, `execution_time`) VALUES
('DoctrineMigrations\\Version20230416125120', '2023-04-28 23:14:35', 1786);

-- --------------------------------------------------------

--
-- Structure de la table `evenement`
--

CREATE TABLE `evenement` (
  `id_event` int(11) NOT NULL,
  `destin_id` int(11) DEFAULT NULL,
  `duree` double NOT NULL,
  `prix` double NOT NULL,
  `date_deb` date NOT NULL,
  `date_fin` date NOT NULL,
  `nom_event` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `evenement`
--

INSERT INTO `evenement` (`id_event`, `destin_id`, `duree`, `prix`, `date_deb`, `date_fin`, `nom_event`) VALUES
(60, 28, 1, 122, '2018-01-01', '2018-01-19', 'jci'),
(61, 29, 55, 5, '2018-01-01', '2018-01-20', 'tt'),
(68, 30, 2, 2, '2018-01-17', '2018-01-19', 'esprit'),
(69, 23, 7, 900, '2023-05-18', '2023-05-24', 'Fete');

-- --------------------------------------------------------

--
-- Structure de la table `hotel`
--

CREATE TABLE `hotel` (
  `id` int(11) NOT NULL,
  `place_id` int(11) DEFAULT NULL,
  `nom` varchar(255) NOT NULL,
  `etoile` varchar(255) NOT NULL,
  `type` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `hotel`
--

INSERT INTO `hotel` (`id`, `place_id`, `nom`, `etoile`, `type`) VALUES
(15, 23, 'Carmela', '3', 'voyage'),
(16, 27, 'Golden Tulip', '5', 'voyage'),
(17, 39, 'Nour Congress And Resort', '4', 'Business'),
(18, 52, 'Pearl Marriott Resort And Spa', '5', 'voyage'),
(19, 53, 'Movenpick Resort', '2', 'Business'),
(20, 58, 'BUSINESS HOTEL', '4', 'Business'),
(27, 23, 'azra', '1', 'azr');

-- --------------------------------------------------------

--
-- Structure de la table `message`
--

CREATE TABLE `message` (
  `id_message` int(11) NOT NULL,
  `id_post` int(11) DEFAULT NULL,
  `contenu` varchar(1000) NOT NULL,
  `note` bigint(20) NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  `likes` int(11) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `message`
--

INSERT INTO `message` (`id_message`, `id_post`, `contenu`, `note`, `created_at`, `updated_at`, `likes`) VALUES
(8, NULL, 'good word', 5, '2023-04-26 23:54:43', '2023-04-26 23:54:43', 0),
(18, 1, 'Sousse est l\'une des meilleures!!!!', 3, '2023-04-29 04:07:06', '2023-04-29 10:34:34', 0),
(19, 1, 'je confirme personnellement..', 5, '2023-04-29 04:07:31', '2023-04-29 04:07:31', 0),
(20, 2, 'Adam Parc en Monastir', 5, '2023-04-29 04:07:57', '2023-04-29 04:07:57', 0),
(21, 2, 'DahDah??', 1, '2023-04-29 04:08:54', '2023-04-29 04:08:54', 0),
(22, 6, 'c\'est très possible', 4, '2023-04-29 04:09:37', '2023-04-29 04:09:37', 0),
(23, 6, 'je ne pense pas personnellement', 3, '2023-04-29 04:12:43', '2023-04-29 04:12:43', 0),
(24, 1, 'Un commentaire', 2, '2023-04-29 10:37:29', '2023-04-29 10:37:29', 0),
(25, 3, 'un autre commentaire', 5, '2023-04-29 10:38:33', '2023-04-29 10:38:53', 0),
(26, 9, 'Très agréable', 3, '2023-04-30 19:41:06', '2023-04-30 19:41:06', 0);

-- --------------------------------------------------------

--
-- Structure de la table `messenger_messages`
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
-- Structure de la table `post`
--

CREATE TABLE `post` (
  `id_post` int(11) NOT NULL,
  `sujet` varchar(256) NOT NULL,
  `signaled` tinyint(1) NOT NULL DEFAULT 0,
  `view` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `post`
--

INSERT INTO `post` (`id_post`, `sujet`, `signaled`, `view`) VALUES
(1, 'Meilleure ville pour les touristes?', 0, 14),
(2, 'Parc d\'attractions en Tunisie?', 1, 4),
(3, 'Montée statistique du taux des touristes', 1, 4),
(4, 'C\'est quoi le pays le plus paisible en Afrique du nord?', 0, 0),
(5, 'Vais-je pouvoir laisser la sécurité de l\'aéoroport vérifier mes poches?', 0, 0),
(6, 'OPINION : la libye va resurgir', 0, 0),
(7, 'Ou vais-je pouvoir me procurer du couscous en Tunisie?', 0, 0),
(8, 'Meilleur endroit à visiter en Tunisie?', 0, 0),
(9, 'Avis sur Hammem Zriba', 0, 1);

-- --------------------------------------------------------

--
-- Structure de la table `publicite`
--

CREATE TABLE `publicite` (
  `id_pub` int(11) NOT NULL,
  `id_event` int(11) DEFAULT NULL,
  `type` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `publicite`
--

INSERT INTO `publicite` (`id_pub`, `id_event`, `type`) VALUES
(1, 68, 'Food');

-- --------------------------------------------------------

--
-- Structure de la table `reservation`
--

CREATE TABLE `reservation` (
  `id` int(11) NOT NULL,
  `id_res` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `id_event` int(11) NOT NULL,
  `qte` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `reset_password_request`
--

CREATE TABLE `reset_password_request` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `selector` varchar(20) NOT NULL,
  `hashed_token` varchar(100) NOT NULL,
  `requested_at` datetime NOT NULL COMMENT '(DC2Type:datetime_immutable)',
  `expires_at` datetime NOT NULL COMMENT '(DC2Type:datetime_immutable)'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `reset_password_request`
--

INSERT INTO `reset_password_request` (`id`, `user_id`, `selector`, `hashed_token`, `requested_at`, `expires_at`) VALUES
(4, 7, 'knkDP0xcuL1lCslE8L2S', 'qSbyEMbBu33O+W23QOzJmT9T/V/e68EU8IoRoyjrzMg=', '2023-04-28 22:43:35', '2023-04-28 23:43:35');

-- --------------------------------------------------------

--
-- Structure de la table `sponsor`
--

CREATE TABLE `sponsor` (
  `id_sponsor` int(11) NOT NULL,
  `intitule` varchar(20) NOT NULL,
  `duree_contrat` int(11) NOT NULL,
  `date_debc` date NOT NULL,
  `date_finc` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `sponsor`
--

INSERT INTO `sponsor` (`id_sponsor`, `intitule`, `duree_contrat`, `date_debc`, `date_finc`) VALUES
(6, 'esprit', 2, '2018-01-01', '2024-01-01'),
(7, 'ace', 5, '2019-01-03', '2025-02-01'),
(8, 'eya', 4, '2018-01-03', '2021-01-01'),
(11, 'farah', 5, '2018-01-01', '2020-01-01'),
(12, 'farahhhhh', 5, '2018-01-01', '2020-01-01');

-- --------------------------------------------------------

--
-- Structure de la table `user`
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
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`id`, `email`, `roles`, `password`, `address`, `telephone`, `login`, `nom`, `is_verified`) VALUES
(1, 'molka.malouka@gmail.com', '[\"BLOCKED\"]', '$2y$13$9Wn9qt1ENs46ANJa1Q1XYuh592z66/mbAJeobW0HhTufBy6s.pYMG', 'Ariana Soghra', '50789635', 'Malouka__', 'Molka', 1),
(3, 'asma.foulen@esprit.tn', '[\"ROLE_ADMIN\"]', '$2y$13$zm.wCK3cdDpv8GdjQ6kj6ep6MIk84Kbe/5j50eAVMX4I/cP3aMHja', 'Nabeul', '22314895', 'Asma_A', 'Asma', 1),
(4, 'nidhal.nar@esprit.tn', '[\"ROLE_USER\"]', '$2y$13$Ap0cePz88AGzVgcEwKu9geonZZ6M5FKg3GFvBzZD.kX7gaYYXKUZ.', 'Bizerte', '20336981', 'Nidhal_', 'Nidhal', 1),
(5, 'sarra.safer@esprit.tn', '[\"ROLE_USER\"]', '$2y$13$KCq0A9LClQGQt4EjN1BMEOehOZWbYbKeFus7KNgO/UsqhHZ7z7PP2', 'Ariana', '95874134', 'Saroura', 'Sara', 1),
(6, 'fares.benahmed@yahoo.com', '[\"ROLE_USER\"]', '$2y$13$naAvgnh5B/qIEjWnogdQ5eSQmmA45kwG6j0v/DgJ94JP1jeX4ArjC', 'Sousse', '50146987', 'Fares__', 'Fares', 0),
(7, 'yasmine.benahmed@yahoo.com', '[\"ROLE_USER\"]', '$2y$13$7HSsjk9g3SP5iR2Zx.72h..99zU6/LXULkogi6ZZRcNlBlKhL5PA2', 'Hammamet', '23698710', 'yasmina__', 'Yasmine', 1),
(8, 'ala.selmi@esorit.tn', '[\"ROLE_USER\"]', '$2y$13$IFUgKblVyjpLCEKJmmoEk.RHGRxtENm/v0QgYuHKR4PIArFRVA4Cm', 'Ariana', '29871035', 'Aloulou_', 'Ala', 1);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `categories_sponsor`
--
ALTER TABLE `categories_sponsor`
  ADD PRIMARY KEY (`id_cat`),
  ADD KEY `IDX_A98618745F1160A4` (`id_sponsor`);

--
-- Index pour la table `destination`
--
ALTER TABLE `destination`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `doctrine_migration_versions`
--
ALTER TABLE `doctrine_migration_versions`
  ADD PRIMARY KEY (`version`);

--
-- Index pour la table `evenement`
--
ALTER TABLE `evenement`
  ADD PRIMARY KEY (`id_event`),
  ADD KEY `IDX_B26681EE4656696` (`destin_id`);

--
-- Index pour la table `hotel`
--
ALTER TABLE `hotel`
  ADD PRIMARY KEY (`id`),
  ADD KEY `IDX_3535ED9DA6A219` (`place_id`);

--
-- Index pour la table `message`
--
ALTER TABLE `message`
  ADD PRIMARY KEY (`id_message`),
  ADD KEY `IDX_B6BD307FD1AA708F` (`id_post`);

--
-- Index pour la table `messenger_messages`
--
ALTER TABLE `messenger_messages`
  ADD PRIMARY KEY (`id`),
  ADD KEY `IDX_75EA56E0FB7336F0` (`queue_name`),
  ADD KEY `IDX_75EA56E0E3BD61CE` (`available_at`),
  ADD KEY `IDX_75EA56E016BA31DB` (`delivered_at`);

--
-- Index pour la table `post`
--
ALTER TABLE `post`
  ADD PRIMARY KEY (`id_post`);

--
-- Index pour la table `publicite`
--
ALTER TABLE `publicite`
  ADD PRIMARY KEY (`id_pub`),
  ADD KEY `IDX_1D394E39D52B4B97` (`id_event`);

--
-- Index pour la table `reservation`
--
ALTER TABLE `reservation`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `reset_password_request`
--
ALTER TABLE `reset_password_request`
  ADD PRIMARY KEY (`id`),
  ADD KEY `IDX_7CE748AA76ED395` (`user_id`);

--
-- Index pour la table `sponsor`
--
ALTER TABLE `sponsor`
  ADD PRIMARY KEY (`id_sponsor`);

--
-- Index pour la table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UNIQ_8D93D649E7927C74` (`email`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `categories_sponsor`
--
ALTER TABLE `categories_sponsor`
  MODIFY `id_cat` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT pour la table `destination`
--
ALTER TABLE `destination`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=67;

--
-- AUTO_INCREMENT pour la table `evenement`
--
ALTER TABLE `evenement`
  MODIFY `id_event` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=70;

--
-- AUTO_INCREMENT pour la table `hotel`
--
ALTER TABLE `hotel`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;

--
-- AUTO_INCREMENT pour la table `message`
--
ALTER TABLE `message`
  MODIFY `id_message` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;

--
-- AUTO_INCREMENT pour la table `messenger_messages`
--
ALTER TABLE `messenger_messages`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `post`
--
ALTER TABLE `post`
  MODIFY `id_post` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT pour la table `publicite`
--
ALTER TABLE `publicite`
  MODIFY `id_pub` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT pour la table `reservation`
--
ALTER TABLE `reservation`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `reset_password_request`
--
ALTER TABLE `reset_password_request`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT pour la table `sponsor`
--
ALTER TABLE `sponsor`
  MODIFY `id_sponsor` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT pour la table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `categories_sponsor`
--
ALTER TABLE `categories_sponsor`
  ADD CONSTRAINT `FK_A98618745F1160A4` FOREIGN KEY (`id_sponsor`) REFERENCES `sponsor` (`id_sponsor`);

--
-- Contraintes pour la table `evenement`
--
ALTER TABLE `evenement`
  ADD CONSTRAINT `FK_B26681EE4656696` FOREIGN KEY (`destin_id`) REFERENCES `destination` (`id`);

--
-- Contraintes pour la table `hotel`
--
ALTER TABLE `hotel`
  ADD CONSTRAINT `FK_3535ED9DA6A219` FOREIGN KEY (`place_id`) REFERENCES `destination` (`id`);

--
-- Contraintes pour la table `message`
--
ALTER TABLE `message`
  ADD CONSTRAINT `FK_B6BD307FD1AA708F` FOREIGN KEY (`id_post`) REFERENCES `post` (`id_post`);

--
-- Contraintes pour la table `publicite`
--
ALTER TABLE `publicite`
  ADD CONSTRAINT `FK_1D394E39D52B4B97` FOREIGN KEY (`id_event`) REFERENCES `evenement` (`id_event`);

--
-- Contraintes pour la table `reset_password_request`
--
ALTER TABLE `reset_password_request`
  ADD CONSTRAINT `FK_7CE748AA76ED395` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
