-- phpMyAdmin SQL Dump
-- version 4.4.3
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Erstellungszeit: 13. Mai 2015 um 10:47
-- Server-Version: 5.5.43-0ubuntu0.14.04.1
-- PHP-Version: 5.5.9-1ubuntu4.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Datenbank: `online_shop`
--

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `Artikel`
--

CREATE TABLE IF NOT EXISTS `Artikel` (
  `ART_ID` int(11) NOT NULL,
  `ART_Bezeichnung` text CHARACTER SET latin1 COLLATE latin1_german1_ci NOT NULL,
  `ART_Beschreibung` text CHARACTER SET latin1 COLLATE latin1_german1_ci NOT NULL,
  `ART_Kategorie` text CHARACTER SET latin1 COLLATE latin1_german1_ci NOT NULL,
  `ART_Typ` int(2) NOT NULL,
  `ART_BildURL` text CHARACTER SET latin1 COLLATE latin1_german1_ci NOT NULL,
  `ART_Bestand` int(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `Auftrag`
--

CREATE TABLE IF NOT EXISTS `Auftrag` (
  `A_ID` int(11) NOT NULL,
  `A_UserID` int(11) NOT NULL,
  `A_BestellID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `Bestellliste`
--

CREATE TABLE IF NOT EXISTS `Bestellliste` (
  `BEST_BestellID` int(11) NOT NULL,
  `BEST_ArtikelID` int(11) NOT NULL,
  `BEST_Menge` int(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `Bestellung`
--

CREATE TABLE IF NOT EXISTS `Bestellung` (
  `B_ID` int(11) NOT NULL,
  `B_Datum` text NOT NULL,
  `B_Status` int(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `Unterprodukt`
--

CREATE TABLE IF NOT EXISTS `Unterprodukt` (
  `UPR_OberArtID` int(11) NOT NULL,
  `UPR_UnterArtID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `User`
--

CREATE TABLE IF NOT EXISTS `User` (
  `U_ID` int(11) NOT NULL,
  `U_Vorname` text NOT NULL,
  `U_Nachname` text NOT NULL,
  `U_GebDatum` date NOT NULL,
  `U_Ort` text NOT NULL,
  `U_PLZ` int(10) NOT NULL,
  `U_Strasse` text NOT NULL,
  `U_Hausnummer` int(5) NOT NULL,
  `U_Adresszusatz` text
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indizes der exportierten Tabellen
--

--
-- Indizes für die Tabelle `Artikel`
--
ALTER TABLE `Artikel`
  ADD PRIMARY KEY (`ART_ID`);

--
-- Indizes für die Tabelle `Auftrag`
--
ALTER TABLE `Auftrag`
  ADD PRIMARY KEY (`A_ID`),
  ADD KEY `A_UserID` (`A_UserID`),
  ADD KEY `A_BestellID` (`A_BestellID`);

--
-- Indizes für die Tabelle `Bestellliste`
--
ALTER TABLE `Bestellliste`
  ADD KEY `BEST_BestellID` (`BEST_BestellID`),
  ADD KEY `BEST_ArtikelID` (`BEST_ArtikelID`);

--
-- Indizes für die Tabelle `Bestellung`
--
ALTER TABLE `Bestellung`
  ADD PRIMARY KEY (`B_ID`);

--
-- Indizes für die Tabelle `Unterprodukt`
--
ALTER TABLE `Unterprodukt`
  ADD UNIQUE KEY `UPR_UnterArtID` (`UPR_UnterArtID`),
  ADD KEY `UPR_OberArtID` (`UPR_OberArtID`);

--
-- Indizes für die Tabelle `User`
--
ALTER TABLE `User`
  ADD PRIMARY KEY (`U_ID`);

--
-- AUTO_INCREMENT für exportierte Tabellen
--

--
-- AUTO_INCREMENT für Tabelle `Artikel`
--
ALTER TABLE `Artikel`
  MODIFY `ART_ID` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT für Tabelle `Auftrag`
--
ALTER TABLE `Auftrag`
  MODIFY `A_ID` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT für Tabelle `Bestellung`
--
ALTER TABLE `Bestellung`
  MODIFY `B_ID` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT für Tabelle `User`
--
ALTER TABLE `User`
  MODIFY `U_ID` int(11) NOT NULL AUTO_INCREMENT;
--
-- Constraints der exportierten Tabellen
--

--
-- Constraints der Tabelle `Auftrag`
--
ALTER TABLE `Auftrag`
  ADD CONSTRAINT `Auftrag_ibfk_2` FOREIGN KEY (`A_BestellID`) REFERENCES `Bestellung` (`B_ID`),
  ADD CONSTRAINT `Auftrag_ibfk_1` FOREIGN KEY (`A_UserID`) REFERENCES `User` (`U_ID`);

--
-- Constraints der Tabelle `Bestellliste`
--
ALTER TABLE `Bestellliste`
  ADD CONSTRAINT `Bestellliste_ibfk_2` FOREIGN KEY (`BEST_ArtikelID`) REFERENCES `Artikel` (`ART_ID`),
  ADD CONSTRAINT `Bestellliste_ibfk_1` FOREIGN KEY (`BEST_BestellID`) REFERENCES `Bestellung` (`B_ID`);

--
-- Constraints der Tabelle `Unterprodukt`
--
ALTER TABLE `Unterprodukt`
  ADD CONSTRAINT `Unterprodukt_ibfk_2` FOREIGN KEY (`UPR_UnterArtID`) REFERENCES `Artikel` (`ART_ID`),
  ADD CONSTRAINT `Unterprodukt_ibfk_1` FOREIGN KEY (`UPR_OberArtID`) REFERENCES `Artikel` (`ART_ID`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
