-- --------------------------------------------------------
-- Хост:                         127.0.0.1
-- Версия сервера:               5.6.19 - MySQL Community Server (GPL)
-- ОС Сервера:                   Win64
-- HeidiSQL Версия:              8.3.0.4694
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Дамп структуры базы данных hockey_school
CREATE DATABASE IF NOT EXISTS `hockey_school` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `hockey_school`;


-- Дамп структуры для таблица hockey_school.admins
CREATE TABLE IF NOT EXISTS `admins` (
  `login` varchar(32) DEFAULT NULL,
  `password` varchar(32) NOT NULL,
  `admin_id` int(11) DEFAULT NULL,
  `is_owner` bit(1) DEFAULT b'0',
  UNIQUE KEY `unique_login` (`login`),
  KEY `id` (`admin_id`),
  CONSTRAINT `admins_ibfk_1` FOREIGN KEY (`admin_id`) REFERENCES `person` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Дамп данных таблицы hockey_school.admins: ~10 rows (приблизительно)
/*!40000 ALTER TABLE `admins` DISABLE KEYS */;
INSERT INTO `admins` (`login`, `password`, `admin_id`, `is_owner`) VALUES
	('21232f297a57a5a743894a0e4a801fc3', '21232f297a57a5a743894a0e4a801fc3', 96, b'0'),
	('47e2e8c3fdb7739e740b95345a803cac', '47e2e8c3fdb7739e740b95345a803cac', 109, b'0'),
	('99b3b060154898840f0ebdfb46ec78f', '99b3b060154898840f0ebdfb46ec78f', 119, b'1'),
	('7dabf5c198b0bab2eaa42bb03a113e55', '7dabf5c198b0bab2eaa42bb03a113e55', 137, b'0'),
	('4124bc0a9335c27f086f24ba207a4912', 'c4055e3a20b6b3af3d10590ea446ef6c', 180, b'0'),
	('4eae35f1b35977a00ebd8086c259d4c9', '4eae35f1b35977a00ebd8086c259d4c9', 260, b'0'),
	('670da91be64127c92faac35c8300e814', '670da91be64127c92faac35c8300e814', 261, b'0'),
	('9df62e693988eb4e1e1444ece0578579', '9df62e693988eb4e1e1444ece0578579', 262, b'0'),
	('8f8e0260c64418510cefb2b06eee5cd', '8f8e0260c64418510cefb2b06eee5cd', 263, b'0'),
	('c70fd4260c9eb90bc0ba9d047c068eb8', 'c70fd4260c9eb90bc0ba9d047c068eb8', 264, b'0');
/*!40000 ALTER TABLE `admins` ENABLE KEYS */;


-- Дамп структуры для таблица hockey_school.coaches
CREATE TABLE IF NOT EXISTS `coaches` (
  `defence_inc` int(10) unsigned DEFAULT NULL,
  `attack_inc` int(10) unsigned NOT NULL DEFAULT '0',
  `coach_login` varchar(32) NOT NULL,
  `password` varchar(32) NOT NULL,
  `coach_id` int(11) NOT NULL,
  UNIQUE KEY `unique_coach_id` (`coach_id`),
  UNIQUE KEY `unique_login` (`coach_login`),
  CONSTRAINT `coaches_ibfk_1` FOREIGN KEY (`coach_id`) REFERENCES `person` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Дамп данных таблицы hockey_school.coaches: ~5 rows (приблизительно)
/*!40000 ALTER TABLE `coaches` DISABLE KEYS */;
INSERT INTO `coaches` (`defence_inc`, `attack_inc`, `coach_login`, `password`, `coach_id`) VALUES
	(23, 22, '25ed1bcb423b0b7200f485fc5ff71c8e', '25ed1bcb423b0b7200f485fc5ff71c8e', 141),
	(21, 26, 'b3cd915d758008bd19d0f2428fbb354a', '21ad0bd836b90d08f4cf640b4c298e7c', 179),
	(20, 17, 'c4055e3a20b6b3af3d10590ea446ef6c', '514f1b439f404f86f77090fa9edc96ce', 182),
	(6, 6, 'a1931ec126bbad3fa7a3fc64209fd921', 'a1931ec126bbad3fa7a3fc64209fd921', 268),
	(6, 6, 'c4efd5020cb49b9d3257ffa0fbccc0ae', 'c4efd5020cb49b9d3257ffa0fbccc0ae', 269);
/*!40000 ALTER TABLE `coaches` ENABLE KEYS */;


-- Дамп структуры для таблица hockey_school.money_transfers
CREATE TABLE IF NOT EXISTS `money_transfers` (
  `date` date NOT NULL,
  `sum` int(11) NOT NULL,
  `operation` varchar(60) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Дамп данных таблицы hockey_school.money_transfers: ~40 rows (приблизительно)
/*!40000 ALTER TABLE `money_transfers` DISABLE KEYS */;
INSERT INTO `money_transfers` (`date`, `sum`, `operation`) VALUES
	('2015-08-01', 40, 'player sale'),
	('2015-08-06', 98, 'player sale'),
	('2015-08-16', 602, 'player sale'),
	('2015-08-17', 6000, 'money from bosses'),
	('2015-08-17', 5000, 'money from bosses'),
	('2015-08-17', 3000, 'money from bosses'),
	('2015-08-22', 14430, 'player sale'),
	('2015-08-22', 400, 'player sale'),
	('2015-08-23', -6000, 'Усовершенствование тренажерного зала'),
	('2015-08-24', -5000, 'Турнир по хоккею среди ай-ти компаний'),
	('2015-08-24', 5000, 'money from bosses'),
	('2015-08-24', 5000, 'money from bosses'),
	('2015-08-24', -5000, 'Турнир по хоккею среди ай-ти компаний'),
	('2015-08-24', -5000, 'Турнир по хоккею среди ай-ти компаний'),
	('2015-08-24', 6000, 'money from bosses'),
	('2015-08-24', 5000, 'money from bosses'),
	('2015-08-24', 5000, 'money from bosses'),
	('2015-08-24', 5000, 'money from bosses'),
	('2015-08-24', 5000, 'money from bosses'),
	('2015-08-24', 5000, 'money from bosses'),
	('2015-08-24', 2000, 'money from bosses'),
	('2015-08-24', 5000, 'money from bosses'),
	('2015-08-24', 2000, 'money from bosses'),
	('2015-08-24', 5000, 'money from bosses'),
	('2015-08-24', 2000, 'money from bosses'),
	('2015-08-24', -5000, 'Турнир по хоккею среди ай-ти компаний'),
	('2015-08-24', -5000, 'Турнир по хоккею среди ай-ти компаний'),
	('2015-08-24', -5000, 'Турнир по хоккею среди ай-ти компаний'),
	('2015-08-24', -5000, 'Турнир по хоккею среди ай-ти компаний'),
	('2015-08-24', -5000, 'Турнир по хоккею среди ай-ти компаний'),
	('2015-08-24', 6000, 'money from bosses'),
	('2015-08-24', 2000, 'money from bosses'),
	('2015-08-25', -2000, 'Курсы повышения квалификации для физиотерапевтов'),
	('2015-08-25', -2000, 'Курсы повышения квалификации для физиотерапевтов'),
	('2015-08-25', -2000, 'Курсы повышения квалификации для физиотерапевтов'),
	('2015-08-25', -2000, 'Курсы повышения квалификации для физиотерапевтов'),
	('2015-08-25', -2000, 'Курсы повышения квалификации для физиотерапевтов'),
	('2015-08-25', -2000, 'Курсы повышения квалификации для физиотерапевтов'),
	('2015-08-25', -2000, 'Курсы повышения квалификации для физиотерапевтов'),
	('2015-08-25', -2000, 'Курсы повышения квалификации для физиотерапевтов');
/*!40000 ALTER TABLE `money_transfers` ENABLE KEYS */;


-- Дамп структуры для таблица hockey_school.person
CREATE TABLE IF NOT EXISTS `person` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `firstname` varchar(20) NOT NULL,
  `lastname` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=270 DEFAULT CHARSET=utf8;

-- Дамп данных таблицы hockey_school.person: ~115 rows (приблизительно)
/*!40000 ALTER TABLE `person` DISABLE KEYS */;
INSERT INTO `person` (`id`, `firstname`, `lastname`) VALUES
	(4, 'John', 'Johnson'),
	(5, 'John', 'Johnson'),
	(6, 'John', 'Johnson'),
	(7, 'John', 'Johnson'),
	(8, 'John', 'Johnson'),
	(9, 'John', 'Johnson'),
	(18, 'Jim', 'Paulson'),
	(19, 'Jim', 'Paulson'),
	(20, 'Jim', 'Paulson'),
	(21, 'Jim', 'Paulson'),
	(22, 'Paul', 'Jackson'),
	(23, 'Sidney', 'Crosby'),
	(24, 'Mario', 'Lemieux'),
	(25, 'Sidney', 'Crosby'),
	(26, 'Sidney', 'Crosby'),
	(27, 'Sidney', 'Crosby'),
	(28, 'Sidney', 'Crosby'),
	(29, 'Mario', 'Lemieux'),
	(30, 'Sidney', 'Crosby'),
	(31, 'Mario', 'Lemieux'),
	(32, 'Sidney', 'Crosby'),
	(33, 'Mario', 'Lemieux'),
	(34, 'Sidney', 'Crosby'),
	(35, 'Mario', 'Lemieux'),
	(36, 'Sidney', 'Crosby'),
	(37, 'Sidney', 'Crosby'),
	(38, 'Mario', 'Lemieux'),
	(39, 'Sidney', 'Crosby'),
	(40, 'Mario', 'Lemieux'),
	(41, 'Jaromir', 'Jagr'),
	(42, 'Teemu', 'Selanne'),
	(43, 'Jaromir', 'Jagr'),
	(44, 'Teemu', 'Selanne'),
	(45, 'Jaromir', 'Jagr'),
	(46, 'Teemu', 'Selanne'),
	(47, 'Jaromir', 'Jagr'),
	(48, 'Jaromir', 'Jagr'),
	(49, 'Teemu', 'Selanne'),
	(50, 'Jaromir', 'Jagr'),
	(51, 'Teemu', 'Selanne'),
	(58, 'Dmitry', 'Olifer'),
	(59, 'DmitryD', 'Olifer'),
	(62, 'Aaa', 'Aaaaaaa'),
	(63, 'Aaa', 'Aaaaaaa'),
	(64, 'Aaa', 'Aaaaaaa'),
	(79, 'Dfghjksdfghj', 'Dfghjk'),
	(80, 'Pppppppppppppppppppp', 'Pppppppppppppppppppp'),
	(83, 'Dmitry', 'Olifer'),
	(84, 'Sdfgh', 'Sdfgh'),
	(89, 'Wwww', 'Wwww'),
	(96, 'Igor', 'Blinov'),
	(109, 'Eugene', 'Matsiuk'),
	(111, 'Sydney', 'Crosby'),
	(112, 'Sydney', 'Crosby'),
	(113, 'Sydney', 'Crosby'),
	(114, 'Sydney', 'Crosby'),
	(115, 'Patrick', 'Kane'),
	(116, 'Patrick', 'Kane'),
	(117, 'Patrick', 'Kane'),
	(119, 'Dmitry', 'Olifer'),
	(123, 'Gerald', 'Dobson'),
	(126, 'Kyle', 'Ronson'),
	(130, 'Nicklas', 'Lidstroem'),
	(136, 'Федор', 'Костоломов'),
	(137, 'Сергей', 'Завирухин'),
	(139, 'Зинетулла', 'Билялетдинов'),
	(141, 'Любомир', 'Покович'),
	(144, 'testName', 'testSurname'),
	(145, 'testName', 'testSurname'),
	(146, 'testName', 'testSurname'),
	(147, 'testName', 'testSurname'),
	(148, 'testName', 'testSurname'),
	(149, 'testFirstName', 'testLastName'),
	(150, 'testFirstName', 'testLastName'),
	(151, 'testFirstName', 'testLastName'),
	(152, 'testFirstName', 'testLastName'),
	(153, 'testFirstName', 'testLastName'),
	(154, 'testFirstName', 'testLastName'),
	(155, 'testFirstName', 'testLastName'),
	(156, 'testFirstName', 'testLastName'),
	(157, 'testFirstName', 'testLastName'),
	(158, 'testFirstName', 'testLastName'),
	(159, 'testFirstName', 'testLastName'),
	(161, 'testFirstName', 'testLastName'),
	(179, 'Майкл', 'Бэбкок'),
	(180, 'Александр', 'Введенский'),
	(182, 'Владимир', 'Ружичка'),
	(194, 'Михаил', 'Грабовский'),
	(206, 'Wertyu', 'Wsdfghj'),
	(207, 'Qqqq', 'Qqqqq'),
	(211, 'Dfghjk', 'Dfghjk'),
	(215, 'Дмитрий', 'Олифер'),
	(217, 'Дмитрий', 'Олифер'),
	(225, 'Джефф', 'Платт'),
	(229, 'Алекс', 'Овечкин'),
	(233, 'Мигель', 'Моуриньо'),
	(247, 'Jjjjj', 'Jjjjj'),
	(249, 'Dfghj', 'Dfghjk'),
	(250, 'Ccccc', 'Cccc'),
	(251, 'Aaaa', 'Aaaa'),
	(254, 'Yyyyyyyyyy', 'Yyyyyyyyy'),
	(256, 'Федор', 'Костоломов'),
	(257, 'Иван', 'Смирнов'),
	(258, 'Fff', 'Ffffff'),
	(259, 'Gggg', 'Gggg'),
	(260, 'Www', 'Www'),
	(261, 'Eeee', 'Eeee'),
	(262, 'Ccc', 'Ccc'),
	(263, 'Bbb', 'Bbb'),
	(264, 'Uuu', 'Uuu'),
	(265, 'Hhh', 'Hhh'),
	(266, 'Fff', 'Fff'),
	(267, 'Ttt', 'Ttt'),
	(268, 'Nnn', 'Nnn'),
	(269, 'Mmm', 'Mmm');
/*!40000 ALTER TABLE `person` ENABLE KEYS */;


-- Дамп структуры для таблица hockey_school.physios
CREATE TABLE IF NOT EXISTS `physios` (
  `speed_inc` int(10) unsigned NOT NULL DEFAULT '0',
  `strength_inc` int(10) unsigned NOT NULL DEFAULT '0',
  `physio_id` int(11) DEFAULT NULL,
  `physio_login` varchar(32) NOT NULL,
  `password` varchar(32) NOT NULL,
  KEY `id` (`physio_id`),
  CONSTRAINT `physios_ibfk_1` FOREIGN KEY (`physio_id`) REFERENCES `person` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- Дамп данных таблицы hockey_school.physios: ~4 rows (приблизительно)
/*!40000 ALTER TABLE `physios` DISABLE KEYS */;
INSERT INTO `physios` (`speed_inc`, `strength_inc`, `physio_id`, `physio_login`, `password`) VALUES
	(20, 15, 256, 'ef1cb6e72d149b184cc241037203f60b', 'ef1cb6e72d149b184cc241037203f60b'),
	(6, 6, 265, 'a3aca2964e72000eea4c56cb341002a4', 'a3aca2964e72000eea4c56cb341002a4'),
	(6, 6, 266, '343d9040a671c45832ee5381860e2996', '343d9040a671c45832ee5381860e2996'),
	(6, 6, 267, '9990775155c3518a0d7917f7780b24aa', '9990775155c3518a0d7917f7780b24aa');
/*!40000 ALTER TABLE `physios` ENABLE KEYS */;


-- Дамп структуры для таблица hockey_school.players
CREATE TABLE IF NOT EXISTS `players` (
  `defence` int(10) unsigned DEFAULT NULL,
  `attack` int(10) unsigned NOT NULL DEFAULT '0',
  `speed` int(10) unsigned NOT NULL DEFAULT '0',
  `strength` int(10) unsigned NOT NULL DEFAULT '0',
  `location` varchar(50) NOT NULL DEFAULT '0',
  `player_id` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`player_id`),
  KEY `id` (`player_id`),
  CONSTRAINT `players_ibfk_1` FOREIGN KEY (`player_id`) REFERENCES `person` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Дамп данных таблицы hockey_school.players: ~15 rows (приблизительно)
/*!40000 ALTER TABLE `players` DISABLE KEYS */;
INSERT INTO `players` (`defence`, `attack`, `speed`, `strength`, `location`, `player_id`) VALUES
	(111, 112, 113, 114, 'team', 50),
	(211, 212, 213, 214, 'sold', 51),
	(138, 100, 8, 8, 'team', 117),
	(230, 156, 63, 86, 'school', 130),
	(197, 229, 40, 82, 'school', 194),
	(198, 108, 71, 63, 'school', 217),
	(164, 111, 58, 69, 'school', 225),
	(153, 96, 74, 70, 'school', 229),
	(173, 94, 53, 68, 'school', 233),
	(6, 6, 6, 6, 'sold', 247),
	(6, 6, 6, 6, 'sold', 249),
	(10, 10, 10, 10, 'sold', 250),
	(6, 6, 6, 6, 'sold', 251),
	(6, 6, 6, 6, 'sold', 257),
	(8, 6, 8, 7, 'team', 258),
	(48, 39, 45, 36, 'school', 259);
/*!40000 ALTER TABLE `players` ENABLE KEYS */;


-- Дамп структуры для таблица hockey_school.players_prices
CREATE TABLE IF NOT EXISTS `players_prices` (
  `player_id` int(11) NOT NULL,
  `price` int(11) NOT NULL,
  UNIQUE KEY `unique_player_id` (`player_id`),
  CONSTRAINT `players_prices_ibfk_1` FOREIGN KEY (`player_id`) REFERENCES `players` (`player_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Дамп данных таблицы hockey_school.players_prices: ~6 rows (приблизительно)
/*!40000 ALTER TABLE `players_prices` DISABLE KEYS */;
INSERT INTO `players_prices` (`player_id`, `price`) VALUES
	(51, 14430),
	(117, 9579),
	(247, 40),
	(249, 98),
	(250, 1536),
	(251, 602),
	(257, 400),
	(258, 490);
/*!40000 ALTER TABLE `players_prices` ENABLE KEYS */;


-- Дамп структуры для таблица hockey_school.player_questionnaire
CREATE TABLE IF NOT EXISTS `player_questionnaire` (
  `birth_year` int(11) DEFAULT NULL,
  `birth_place` varchar(25) DEFAULT NULL,
  `nationality` varchar(25) DEFAULT NULL,
  `preferable_side` varchar(5) DEFAULT NULL,
  `game_role` varchar(30) DEFAULT NULL,
  `height` int(11) DEFAULT NULL,
  `weight` int(11) DEFAULT NULL,
  `interests` varchar(200) DEFAULT NULL,
  `jersey` int(11) DEFAULT NULL,
  `quest_id` int(11) DEFAULT NULL,
  KEY `player_id` (`quest_id`),
  CONSTRAINT `player_questionnaire_ibfk_1` FOREIGN KEY (`quest_id`) REFERENCES `players` (`player_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Дамп данных таблицы hockey_school.player_questionnaire: ~9 rows (приблизительно)
/*!40000 ALTER TABLE `player_questionnaire` DISABLE KEYS */;
INSERT INTO `player_questionnaire` (`birth_year`, `birth_place`, `nationality`, `preferable_side`, `game_role`, `height`, `weight`, `interests`, `jersey`, `quest_id`) VALUES
	(1970, 'Крилбо', 'sweden', 'левая', 'defence', 185, 87, 'лыжи, книги', 5, 130),
	(1988, 'Баффало', 'usa', 'левая', 'speedforward', 178, 81, 'компьютерные игры', 88, 117),
	(1984, 'Минск   ', 'belarus', 'левая', 'forward', 180, 83, 'велосипед', 84, 194),
	(1982, 'Минск', 'belarus', 'left', 'defence', 180, 69, 'таэквондо, бокс', 88, 217),
	(1985, 'Торонто', 'canada', 'left', 'defence', 176, 82, 'финские девушки', 16, 225),
	(1985, 'Москва', 'russia', 'right', 'powerforward', 190, 100, 'вечеринки, машины', 8, 229),
	(1970, 'Хельсинки', 'finland', 'right', 'speedforward', 182, 93, 'рыбалка', 8, 51),
	(1972, 'Кладно', 'czech', 'left', 'forward', 191, 110, 'плавание', 68, 50),
	(1988, 'Овьедо', 'spain', 'right', 'powerdefence', 169, 79, '', 16, 233),
	(1960, 'fgh', 'swiss', 'left', 'defence', 222, 111, '', 55, 249);
/*!40000 ALTER TABLE `player_questionnaire` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
