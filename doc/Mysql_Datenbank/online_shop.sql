-- phpMyAdmin SQL Dump
-- version 4.4.3
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Erstellungszeit: 15. Jun 2015 um 17:28
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
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

--
-- Daten für Tabelle `Artikel`
--

INSERT INTO `Artikel` (`ART_ID`, `ART_Bezeichnung`, `ART_Beschreibung`, `ART_Kategorie`, `ART_Typ`, `ART_BildURL`, `ART_Bestand`, `ART_Preis`) VALUES
(1, 'Nettes Futon Bett', 'Modernes Futonbett in Eiche Trüffel<br>\r\nDie Liegefläche beträgt ca. 140 x 200 cm.<br>\r\nLattenrost und Matratze sind im<br>\r\nLieferumfang enthalten.', 'Bett', 1, 'images/Betten/BETT_Futonbett_1tlg.png', 10, 700),
(2, 'Metallbett SQUARE schwarz', 'Modernes Doppelbett aus schwarzem Metall mit<br>\r\n chromfarbigen Absetzungen. Das Bett hat ein<br>\r\n Gesamtmaß von B/H/T ca. 145,9 x 93 x 207,6 cm.<br>\r\n Das Metallbett hat eine Liegefläche von ca. <br>\r\n 140 x 200 cm und ist somit super für ein <br>\r\n Schlafzimmer oder Jugendzimmer geeignet.<br>\r\n Matratze und Lattenrahmen sind im Lieferumfang enthalten.', 'Bett', 1, 'images/Betten/BETT_Metallbett_square_schwarz.jpg', 0, 942),
(3, 'Kompfor Flex Lattenrost', 'mehrfach verleimtes Schichtholz für Außen- und Innenholme;<br>\r\n    Abstand der Federleisten zueinander: ca. 3 cm<br>\r\n    Mittelgurt zur besseren Druckverteilung<br>\r\n    28 Holmübergreifende Federholzleisten, Gesamthöhe: ca. 7,5 cm<br>\r\n    Neues Modell 2012: Jetzt mit GS Zeichen (Geprüfte Sicherheit)<br>\r\n    Flächenbelastung (FB) bis 180kg (zulässige Gesamtbelastung des<br>\r\n    Lattenrostes in Verbindung mit einer Matratze)', 'Lattenrost', 0, 'images/Latenrost/LATT_Kompfort_Flex_Lattenrost.jpeg', 10, 99),
(4, 'Multi Plus Kaltschaum Matratze Schlaraffia 100x210 cm H3', 'Beste Eigenschaften in den Bereichen Atmungsaktivität, Durchlüftung,<br>\r\n    Feuchtigkeitstransport, Handling.<br>\r\n    7-Zonen-Aufbau und verschiedene Raumgewichte sorgen für softe Schulter-<br>\r\n    und stützende Mittelzonen. Unterstützt durch gerade Einschnitte des Schaumes.', 'Matratze', 0, 'images/Matratzen/Multi-Plus-Kaltschaum-Matratze.png', 10, 269),
(5, 'Nettes Futon Bett Gestell', 'Modernes Futonbett in Eiche Trüffel<br>\r\n    Die Liegefläche beträgt ca. 140 x 200 cm.<br>\r\n    Lattenrost und Matratze sind nicht im Lieferumfang enthalten.', 'Matratze', 0, 'images/Betten/BETT_Futonbett_1tlg.png', 12, 430),
(6, 'Metallbett SQUARE schwarz Gestell', 'Modernes Doppelbett aus schwarzem Metall mit<br>\r\n chromfarbigen Absetzungen. Das Bett hat ein<br>\r\n Gesamtmaß von B/H/T ca. 145,9 x 93 x 207,6 cm.<br>\r\n Das Metallbett hat eine Liegefläche von ca. <br>\r\n 140 x 200 cm und ist somit super für ein <br>\r\n Schlafzimmer oder Jugendzimmer geeignet.<br>\r\nDies ist nur ein Gestell, somit sind Matratzen und Lattenrost nicht inbegriffen.', 'Gestell', 0, 'images/Betten/BETT_Metallbett_square_schwarz.jpg', 5, 699),
(7, 'Renforce Bettwäsche 135x200 - 155x220 Baumwolle 2-Teilig', 'Bettwäsche in EXKLUSIV Qualität. Feinfädige Bettwäsche  aus 100% Baumwolle. Die Bettwäsche ist mit einem Reißverschluss ausgestattet und  zertifiziert nach ÖKOTEX Standard 100. Waschbar bei 60° und trocknergeeignet.<br>\r\nGrößen:<br>\r\n1x Kissenbezug 80x80 cm und 1x Bettbezug 135x200 cm oder<br>\r\n1x Kissenbezug 80x80 cm und 1x Bettbezug 155x220 cm', 'Bettwaesche', 0, 'images/Bettwaesche/Renforce_2-teilig_Baumwolle_Bettwaesche_gruen.jpeg', 22, 29),
(8, 'Renforce Landhaus Bettwäsche Karo 135x200 BAUERNKARO Pink', 'Lieferumfang:<br>\r\n    1x Bettbezug 135x200 cm<br>\r\n    1x Kissenbezug 80x80 cm<br>\r\nEigenschaften:<br> \r\n100% Baumwolle<br> \r\nLeinwandbindung<br> \r\nReißverschluss<br> \r\nhautsympathisch & strapazierfähig<br> \r\nfeuchtigkeitsregulierend & atmungsaktiv<br> \r\nzertifiziert nach Öko-Tex Standard 100 ', 'Bettwaesche', 0, 'images/Bettwaesche/Renforce_2-teilig_Baumwolle_Bettwaesche_pink.jpeg', 5, 32),
(9, 'Futon Bett + Renforce Landhaus Bettwäsche', 'Modernes Futonbett in Eiche Trüffel\r\nDie Liegefläche beträgt ca. 140 x 200 cm.\r\n    Zusätzlich Renforce Landhaus Bettwäsche Karo 135x200 BAUERNKARO Pink<br>\r\n    Aus 100% Baumwolle und top Qualität<br>\r\nIm Lieferumfang ist enthalten:<br>\r\n<ul>\r\n    <li>Gestell</li>\r\n    <li>Lattenrost</li>\r\n    <li>Matratze</li>\r\n    <li>und Bettwäsche von Renforce</li>\r\n    </ul>\r\n    Das AllInOne Paket für Singles.\r\n    ', 'AllInOne', 2, 'images/Kombi/KOMBI_Futonbett_Bettw_pink.png', 0, 720);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `Auftrag`
--

CREATE TABLE IF NOT EXISTS `Auftrag` (
  `A_ID` int(11) NOT NULL,
  `A_UserID` int(11) NOT NULL,
  `A_BestellID` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=latin1;

--
-- Daten für Tabelle `Auftrag`
--

INSERT INTO `Auftrag` (`A_ID`, `A_UserID`, `A_BestellID`) VALUES
(1, 2, 6),
(2, 2, 7),
(3, 2, 9),
(4, 2, 13),
(5, 2, 14),
(6, 1, 15),
(7, 1, 16),
(8, 2, 17),
(9, 2, 18),
(10, 2, 19),
(11, 2, 20),
(12, 2, 21),
(13, 1, 22),
(14, 2, 23),
(15, 2, 24),
(16, 1, 25);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `Bestellliste`
--

CREATE TABLE IF NOT EXISTS `Bestellliste` (
  `BEST_BestellID` int(11) NOT NULL,
  `BEST_ArtikelID` int(11) NOT NULL,
  `BEST_Menge` int(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Daten für Tabelle `Bestellliste`
--

INSERT INTO `Bestellliste` (`BEST_BestellID`, `BEST_ArtikelID`, `BEST_Menge`) VALUES
(13, 1, 2),
(14, 1, 3),
(14, 4, 4),
(14, 8, 2),
(15, 3, 1),
(16, 5, 2),
(17, 1, 1),
(18, 7, 1),
(19, 1, 10),
(20, 7, 22),
(21, 5, 10),
(21, 8, 5),
(22, 1, 2),
(23, 1, 1),
(24, 1, 1),
(24, 3, 1),
(25, 1, 1),
(25, 3, 1);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `Bestellung`
--

CREATE TABLE IF NOT EXISTS `Bestellung` (
  `B_ID` int(11) NOT NULL,
  `B_Datum` text NOT NULL,
  `B_Status` int(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=latin1;

--
-- Daten für Tabelle `Bestellung`
--

INSERT INTO `Bestellung` (`B_ID`, `B_Datum`, `B_Status`) VALUES
(6, '02.05.15', 2),
(7, '02.05.15', 2),
(8, '02.05.15', 2),
(9, '02.05.15', 2),
(10, '02.05.15', 2),
(11, '02.05.15', 2),
(12, '02.05.15', 2),
(13, '02.05.15', 2),
(14, 'Tue Jun 02 19:16:49 CEST 2015', 0),
(15, 'Tue Jun 02 19:17:53 CEST 2015', 0),
(16, 'Tue Jun 02 19:19:46 CEST 2015', 0),
(17, 'Wed Jun 03 08:32:59 CEST 2015', 0),
(18, 'Wed Jun 03 09:16:21 CEST 2015', 0),
(19, 'Wed Jun 03 09:35:04 CEST 2015', 0),
(20, 'Wed Jun 03 10:00:05 CEST 2015', 0),
(21, 'Wed Jun 03 10:01:09 CEST 2015', 0),
(22, 'Wed Jun 03 11:17:57 CEST 2015', 0),
(23, 'Fri Jun 05 13:03:34 CEST 2015', 0),
(24, 'Thu Jun 11 07:48:12 CEST 2015', 0),
(25, 'Thu Jun 11 07:48:36 CEST 2015', 0);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `Unterprodukt`
--

CREATE TABLE IF NOT EXISTS `Unterprodukt` (
  `UPR_OberArtID` int(11) NOT NULL,
  `UPR_UnterArtID` int(11) NOT NULL,
  `UPR_Menge` int(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Daten für Tabelle `Unterprodukt`
--

INSERT INTO `Unterprodukt` (`UPR_OberArtID`, `UPR_UnterArtID`, `UPR_Menge`) VALUES
(1, 3, 1),
(1, 4, 1),
(1, 5, 1),
(2, 3, 2),
(2, 4, 2),
(2, 6, 1),
(9, 1, 1),
(9, 8, 1);

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
  `U_GebDatum` varchar(10) NOT NULL,
  `U_Ort` text NOT NULL,
  `U_PLZ` int(10) NOT NULL,
  `U_Strasse` text NOT NULL,
  `U_Hausnummer` int(5) NOT NULL,
  `U_Adresszusatz` text,
  `U_Typ` int(2) NOT NULL DEFAULT '0'
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Daten für Tabelle `User`
--

INSERT INTO `User` (`U_ID`, `U_Email`, `U_Passwort`, `U_Vorname`, `U_Nachname`, `U_GebDatum`, `U_Ort`, `U_PLZ`, `U_Strasse`, `U_Hausnummer`, `U_Adresszusatz`, `U_Typ`) VALUES
(1, 'test@test.de', '1234', 'testVorname', 'testNachname', '10.10.2001', 'TestOrt', 21444, 'TestStr', 11, '', 0),
(2, 'admin@admin.de', '1234', 'Hans', 'Peter', '01.01.1989', 'AdminStadt', 1234, 'AdminStrasse', 1, '', 1),
(3, 'lol@gg.de', '123', 'Dr. Octopus', 'Achtarmig', '01.01.1588', 'Unterwasser', 1000, 'Bluberblase', 918841122, 'Neben dem Steinchen', 0),
(4, 'marc@gmail.com', '123456', 'Marc', 'Ka', '11.10.1992', 'H', 23, 'hmbutg', 23, '', 0);

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
  ADD PRIMARY KEY (`BEST_BestellID`,`BEST_ArtikelID`),
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
  ADD PRIMARY KEY (`UPR_OberArtID`,`UPR_UnterArtID`),
  ADD KEY `UPR_OberArtID` (`UPR_OberArtID`),
  ADD KEY `UPR_UnterArtID` (`UPR_UnterArtID`) USING BTREE;

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
  MODIFY `ART_ID` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=10;
--
-- AUTO_INCREMENT für Tabelle `Auftrag`
--
ALTER TABLE `Auftrag`
  MODIFY `A_ID` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=17;
--
-- AUTO_INCREMENT für Tabelle `Bestellung`
--
ALTER TABLE `Bestellung`
  MODIFY `B_ID` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=26;
--
-- AUTO_INCREMENT für Tabelle `User`
--
ALTER TABLE `User`
  MODIFY `U_ID` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=5;
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
