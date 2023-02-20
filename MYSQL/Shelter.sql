/* SHELTER*/

CREATE TABLE `shelter` (
   `id_prt` int NOT NULL,
   `name_prt` varchar(45) NOT NULL,
   `ocation` varchar(45) NOT NULL,
   `licencia` varchar(45) NOT NULL,
   `tlf` int NOT NULL,
   `eMail` varchar(45) NOT NULL,
   `social1` varchar(45) NOT NULL,
   `social2` varchar(45) NOT NULL,
   `social3` varchar(45) NOT NULL,
   `webPrt` varchar(45) NOT NULL,
   PRIMARY KEY (`id_prt`)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


INSERT INTO `users`.`shelter` (`id_prt`, `name_prt`, `ocation`, `licencia`, `tlf`, `eMail`, `social1`, `social2`, `social3`, `webPrt`) VALUES ('78695743', 'FamilyProtect', 'Madrid', '675847483', '914875945', 'familyprotect@gmail.com', 'FamilyProtect', 'FamilyProtect', 'FamilyProtect', 'FamilyProtect.com');
