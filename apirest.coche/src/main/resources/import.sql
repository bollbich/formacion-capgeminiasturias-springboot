INSERT INTO marcas (nombre) VALUES ('Mazda');
INSERT INTO marcas (nombre) VALUES ('Toyota');
INSERT INTO marcas (nombre) VALUES ('Mercedez');
INSERT INTO marcas (nombre) VALUES ('Seat');

INSERT INTO modelo (nombre, cod_marca) VALUES ('Prius', 2);
INSERT INTO modelo (nombre, cod_marca) VALUES ('Lacteus', 1);
INSERT INTO modelo (nombre, cod_marca) VALUES ('Star', 3);
INSERT INTO modelo (nombre, cod_marca) VALUES ('Cactus', 3);

INSERT INTO coches (cilindrada, color, matricula, velocidad, cod_marca, cod_modelo) VALUES ( 250, 'rojo', '3456erf', 345, 1, 1);
INSERT INTO coches (cilindrada, color, matricula, velocidad, cod_marca, cod_modelo) VALUES ( 150, 'azul', '268fre', 150, 4, 4);
INSERT INTO coches (cilindrada, color, matricula, velocidad, cod_marca, cod_modelo) VALUES ( 250, 'verde', '2344tjo', 350, 2, 4);



INSERT INTO roles (nombre) VALUES ('ROLE_USER');
INSERT INTO roles (nombre) VALUES ('ROLE_ADMIN');

INSERT INTO usuarios (username,password,enabled) VALUES ('ray','$2a$10$0M9WFTdyGEZMvI18ZGY4B.rWCX5mkfUnOiYn4ydDT34bznpjjasEC',1);
INSERT INTO usuarios (username,password,enabled) VALUES ('ray2','$2a$10$dP5/OdP1rEyhs17GiiHTcOmiRdFSxBIx25LP2/6BorX1zRnreWn1y',1);
INSERT INTO usuarios (username,password,enabled) VALUES ('ADMIN','$2a$10$Ggg.duJ6FPfiijKdaYeiruDRltblePXi3uVUCPUFFiAlEPgnVc1VS',1);

INSERT INTO usuarios_roles (usuario_id,role_id) VALUES (1,1);
INSERT INTO usuarios_roles (usuario_id,role_id) VALUES (2,1);
INSERT INTO usuarios_roles (usuario_id,role_id) VALUES (2,2);
INSERT INTO usuarios_roles (usuario_id,role_id) VALUES (3,1);
INSERT INTO usuarios_roles (usuario_id,role_id) VALUES (3,2);

