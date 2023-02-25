/* MENSAJES*/

CREATE TABLE `messages` (
   `content` varchar(45) NOT NULL,
   `id` varchar(45) NOT NULL,
   `messagescol` varchar(45) NOT NULL,
   PRIMARY KEY (`content`,`id`)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `users`.`messages` (`content`, `id`, `messagescol`) VALUES ('Quiero adoptar', '234223454', '4536453');