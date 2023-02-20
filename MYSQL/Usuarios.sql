
/* USUARIOS*/

CREATE TABLE `usuarios` (
   `name_adp` varchar(45) NOT NULL,
   `sname_adp1` varchar(45) NOT NULL,
   `sname_adp2` varchar(45) NOT NULL,
   `username` varchar(45) NOT NULL,
   `pass` varchar(45) NOT NULL,
   `dni` varchar(45) NOT NULL,
   PRIMARY KEY (`dni`),
   UNIQUE KEY `username_UNIQUE` (`username`),
   UNIQUE KEY `dni_UNIQUE` (`dni`)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `users`.`usuarios` (`name_adp`, `sname_adp1`, `sname_adp2`, `username`, `pass`, `dni`, `edad`) VALUES ('Lucía', 'Jimenez', 'Lopez', 'Ljilo', 'lucia123', '78455612P', '40');
INSERT INTO `users`.`usuarios` (`name_adp`, `sname_adp1`, `sname_adp2`, `username`, `pass`, `dni`, `edad`) VALUES ('María', 'García', 'Perez', 'MgarPer', 'maria123', '23412345E', '25');
INSERT INTO `users`.`usuarios` (`name_adp`, `sname_adp1`, `sname_adp2`, `username`, `pass`, `dni`, `edad`) VALUES ('Juan', 'Perez', 'Sanchez', 'Jpesa', 'juan123', '51755065O', '18');
INSERT INTO `users`.`usuarios` (`name_adp`, `sname_adp1`, `sname_adp2`, `username`, `pass`, `dni`, `edad`) VALUES ('Jose', 'Ramirez', 'Rodriguez', 'Jrarod', 'jose123', '43566876R', '32');