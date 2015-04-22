-- phpMyAdmin SQL Dump
-- version 4.2.11
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Erstellungszeit: 22. Apr 2015 um 19:21
-- Server Version: 5.6.21
-- PHP-Version: 5.6.3

--
-- online_shop version 2 
--
--
SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Datenbank: `online_shop`
--
CREATE DATABASE IF NOT EXISTS `online_shop` DEFAULT CHARACTER SET utf8 COLLATE utf8_bin;
USE `online_shop`;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `auftrag`
--

DROP TABLE IF EXISTS `auftrag`;
CREATE TABLE IF NOT EXISTS `auftrag` (
`AuftragNr` int(4) NOT NULL,
  `UserID` int(4) NOT NULL,
  `BestellID` int(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- RELATIONEN DER TABELLE `auftrag`:
--   `UserID`
--       `user` -> `UserID`
--

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `besteht_aus`
--

DROP TABLE IF EXISTS `besteht_aus`;
CREATE TABLE IF NOT EXISTS `besteht_aus` (
  `BestellID` int(4) NOT NULL,
  `ProduktID` int(4) NOT NULL,
  `Menge` varchar(3) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- RELATIONEN DER TABELLE `besteht_aus`:
--   `BestellID`
--       `bestellung` -> `BestellID`
--   `ProduktID`
--       `produkt` -> `ProduktID`
--

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `bestellung`
--

DROP TABLE IF EXISTS `bestellung`;
CREATE TABLE IF NOT EXISTS `bestellung` (
`BestellID` int(4) NOT NULL,
  `Datum` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `produkt`
--

DROP TABLE IF EXISTS `produkt`;
CREATE TABLE IF NOT EXISTS `produkt` (
`ProduktID` int(4) NOT NULL,
  `Bezeichnung` text COLLATE utf8_bin NOT NULL,
  `ProduktInfo` text COLLATE utf8_bin NOT NULL,
  `Kategorie` text COLLATE utf8_bin NOT NULL,
  `Artikelbestand` varchar(3) COLLATE utf8_bin NOT NULL,
  `Dateipfad` text COLLATE utf8_bin NOT NULL,
  `Preis` varchar(6) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=2001 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Daten für Tabelle `produkt`
--

INSERT INTO `produkt` (`ProduktID`, `Bezeichnung`, `ProduktInfo`, `Kategorie`, `Artikelbestand`, `Dateipfad`, `Preis`) VALUES
(1000, 'Super geniales Bett', 'Variante 90/200 cm\r\nMaße ca.: 100/148/188\r\nMassive Nordliche Kiefer', 'betten', '5', '../pics/betten/Futonbett_1tlg.png', '300'),
(1001, 'Super weiches Bett', 'Variante 180/200 cm\r\nMaße ca.: 200/148/220\r\nMassive Nordliche Kiefer', 'betten', '6', '../pics/betten/Futonbett_2tlg.png', '500'),
(2000, '»Frottee KS, Deluxe & Premium Cool Plus«', 'Ca. 25 cm hoch mit 7 Liegezonen \r\nIn den Härtegraden 2 -5', 'Matratze', '10', '../pics/matratzen/canvas.png', '200');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
`UserID` int(4) NOT NULL,
  `Vorname` text COLLATE utf8_bin NOT NULL,
  `Nachname` text COLLATE utf8_bin NOT NULL,
  `UserTyp` text COLLATE utf8_bin NOT NULL,
  `Ort` varchar(20) COLLATE utf8_bin NOT NULL,
  `PLZ` varchar(5) COLLATE utf8_bin NOT NULL,
  `Strasse` text COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Indizes der exportierten Tabellen
--

--
-- Indizes für die Tabelle `auftrag`
--
ALTER TABLE `auftrag`
 ADD PRIMARY KEY (`AuftragNr`), ADD UNIQUE KEY `User_ID` (`UserID`) COMMENT 'user', ADD UNIQUE KEY `Bestell_ID` (`BestellID`) COMMENT 'Bestell';

--
-- Indizes für die Tabelle `besteht_aus`
--
ALTER TABLE `besteht_aus`
 ADD PRIMARY KEY (`BestellID`,`ProduktID`), ADD KEY `ProduktID` (`ProduktID`);

--
-- Indizes für die Tabelle `bestellung`
--
ALTER TABLE `bestellung`
 ADD PRIMARY KEY (`BestellID`);

--
-- Indizes für die Tabelle `produkt`
--
ALTER TABLE `produkt`
 ADD PRIMARY KEY (`ProduktID`);

--
-- Indizes für die Tabelle `user`
--
ALTER TABLE `user`
 ADD PRIMARY KEY (`UserID`);

--
-- AUTO_INCREMENT für exportierte Tabellen
--

--
-- AUTO_INCREMENT für Tabelle `auftrag`
--
ALTER TABLE `auftrag`
MODIFY `AuftragNr` int(4) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT für Tabelle `bestellung`
--
ALTER TABLE `bestellung`
MODIFY `BestellID` int(4) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT für Tabelle `produkt`
--
ALTER TABLE `produkt`
MODIFY `ProduktID` int(4) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=2001;
--
-- AUTO_INCREMENT für Tabelle `user`
--
ALTER TABLE `user`
MODIFY `UserID` int(4) NOT NULL AUTO_INCREMENT;
--
-- Constraints der exportierten Tabellen
--

--
-- Constraints der Tabelle `auftrag`
--
ALTER TABLE `auftrag`
ADD CONSTRAINT `UserID` FOREIGN KEY (`UserID`) REFERENCES `user` (`UserID`);

--
-- Constraints der Tabelle `besteht_aus`
--
ALTER TABLE `besteht_aus`
ADD CONSTRAINT `BestellID` FOREIGN KEY (`BestellID`) REFERENCES `bestellung` (`BestellID`),
ADD CONSTRAINT `ProduktID` FOREIGN KEY (`ProduktID`) REFERENCES `produkt` (`ProduktID`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
