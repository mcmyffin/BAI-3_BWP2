-- phpMyAdmin SQL Dump
-- version 4.4.3
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Erstellungszeit: 21. Mai 2015 um 13:54
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
  `ART_Bestand` int(5) NOT NULL,
  `ART_Preis` int(20) NOT NULL DEFAULT '0'
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Daten für Tabelle `Artikel`
--

INSERT INTO `Artikel` (`ART_ID`, `ART_Bezeichnung`, `ART_Beschreibung`, `ART_Kategorie`, `ART_Typ`, `ART_BildURL`, `ART_Bestand`, `ART_Preis`) VALUES
(1, 'Nettes Futon Bett', 'Modernes Futonbett in Eiche Trüffel<br>\r\nDie Liegefläche beträgt ca. 140 x 200 cm.<br>\r\nLattenrost und Matratze sind im<br>\r\nLieferumfang enthalten.', 'Bett', 1, 'images/Betten/BETT_Futonbett_1tlg.png', 10, 700),
(2, 'Metallbett SQUARE schwarz', 'Modernes Doppelbett aus schwarzem Metall mit<br>\r\n chromfarbigen Absetzungen. Das Bett hat ein<br>\r\n Gesamtmaß von B/H/T ca. 145,9 x 93 x 207,6 cm.<br>\r\n Das Metallbett hat eine Liegefläche von ca. <br>\r\n 140 x 200 cm und ist somit super für ein <br>\r\n Schlafzimmer oder Jugendzimmer geeignet.<br>\r\n Matratze und Lattenrahmen sind im Lieferumfang enthalten.', 'Bett', 1, 'images/Betten/BETT_Metallbett_square_schwarz.jpg', 0, 942),
(3, 'Kompfor Flex Lattenrost', 'mehrfach verleimtes Schichtholz für Außen- und Innenholme;<br>\r\n    Abstand der Federleisten zueinander: ca. 3 cm<br>\r\n    Mittelgurt zur besseren Druckverteilung<br>\r\n    28 Holmübergreifende Federholzleisten, Gesamthöhe: ca. 7,5 cm<br>\r\n    Neues Modell 2012: Jetzt mit GS Zeichen (Geprüfte Sicherheit)<br>\r\n    Flächenbelastung (FB) bis 180kg (zulässige Gesamtbelastung des<br>\r\n    Lattenrostes in Verbindung mit einer Matratze)', 'Lattenrost', 0, 'images/Latenrost/LATT_Kompfort_Flex_Lattenrost.jpeg', 10, 99),
(4, 'Multi Plus Kaltschaum Matratze Schlaraffia 100x210 cm H3', 'Beste Eigenschaften in den Bereichen Atmungsaktivität, Durchlüftung,<br>\r\n    Feuchtigkeitstransport, Handling.<br>\r\n    7-Zonen-Aufbau und verschiedene Raumgewichte sorgen für softe Schulter-<br>\r\n    und stützende Mittelzonen. Unterstützt durch gerade Einschnitte des Schaumes.', 'Matratze', 0, 'images/Matratzen/Multi-Plus-Kaltschaum-Matratze.png', 10, 269),
(5, 'Nettes Futon Bett Gestell', 'Modernes Futonbett in Eiche Trüffel<br>\r\n    Die Liegefläche beträgt ca. 140 x 200 cm.<br>\r\n    Lattenrost und Matratze sind nicht im Lieferumfang enthalten.', 'Matratze', 0, 'images/Betten/BETT_Futonbett_1tlg.png', 12, 430);

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
  `UPR_UnterArtID` int(11) NOT NULL,
  `UPR_Menge` int(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `User`
--

CREATE TABLE IF NOT EXISTS `User` (
  `U_ID` int(11) NOT NULL,
  `U_Email` text NOT NULL,
  `U_Passwort` char(32) NOT NULL,
  `U_Vorname` text NOT NULL,
  `U_Nachname` text NOT NULL,
  `U_GebDatum` date NOT NULL,
  `U_Ort` text NOT NULL,
  `U_PLZ` int(10) NOT NULL,
  `U_Strasse` text NOT NULL,
  `U_Hausnummer` int(5) NOT NULL,
  `U_Adresszusatz` text
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Daten für Tabelle `User`
--

INSERT INTO `User` (`U_ID`, `U_Email`, `U_Passwort`, `U_Vorname`, `U_Nachname`, `U_GebDatum`, `U_Ort`, `U_PLZ`, `U_Strasse`, `U_Hausnummer`, `U_Adresszusatz`) VALUES
(1, 'test@test.de', '1234', 'testVorname', 'testNachname', '0000-00-00', 'TestOrt', 21444, 'TestStr', 11, '');

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
  MODIFY `ART_ID` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=6;
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
  MODIFY `U_ID` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=2;
--
-- Constraints der exportierten Tabellen
--

--
-- Constraints der Tabelle `Auftrag`
--
ALTER TABLE `Auftrag`
  ADD CONSTRAINT `Auftrag_ibfk_1` FOREIGN KEY (`A_UserID`) REFERENCES `User` (`U_ID`),
  ADD CONSTRAINT `Auftrag_ibfk_2` FOREIGN KEY (`A_BestellID`) REFERENCES `Bestellung` (`B_ID`);

--
-- Constraints der Tabelle `Bestellliste`
--
ALTER TABLE `Bestellliste`
  ADD CONSTRAINT `Bestellliste_ibfk_1` FOREIGN KEY (`BEST_BestellID`) REFERENCES `Bestellung` (`B_ID`),
  ADD CONSTRAINT `Bestellliste_ibfk_2` FOREIGN KEY (`BEST_ArtikelID`) REFERENCES `Artikel` (`ART_ID`);

--
-- Constraints der Tabelle `Unterprodukt`
--
ALTER TABLE `Unterprodukt`
  ADD CONSTRAINT `Unterprodukt_ibfk_1` FOREIGN KEY (`UPR_OberArtID`) REFERENCES `Artikel` (`ART_ID`),
  ADD CONSTRAINT `Unterprodukt_ibfk_2` FOREIGN KEY (`UPR_UnterArtID`) REFERENCES `Artikel` (`ART_ID`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
