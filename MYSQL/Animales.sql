/* ANIMALES*/

CREATE TABLE `animales` (
   `name_anm` varchar(45) NOT NULL,
   `age` int NOT NULL,
   `specie` varchar(45) NOT NULL,
   `breed` varchar(45) NOT NULL,
   `gender` varchar(45) NOT NULL,
   `birthdate` date NOT NULL,
   `weight` double NOT NULL,
   `height` varchar(45) NOT NULL,
   `Id` int NOT NULL,
   PRIMARY KEY (`Id`)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `users`.`animales` (`name_anm`, `age`, `specie`, `breed`, `gender`, `birthdate`, `weight`, `height`, `Id`) VALUES ('Pipo', '10', 'Gato', 'Protectora2', 'Macho', '13/11/2013', '12', '13', '1234556');