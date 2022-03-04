INSERT INTO clientes (nombre, apellido, email, telefono, created_At) VALUES ('Jose', 'Perez', 'JP@gemail.com', 657483928, '2021-01-01');
INSERT INTO clientes (nombre, apellido, email, telefono, created_At) VALUES ('Alberto', 'Lorez', 'AL@gemail.com', 678494940, '2020-02-01');
INSERT INTO clientes (nombre, apellido, email, telefono, created_At) VALUES ('Raul', 'Santo', 'RS@gemail.com', 695493026, '2015-03-01');
INSERT INTO clientes (nombre, apellido, email, telefono, created_At) VALUES ('Norton', 'Fresno', 'NF@gemail.com', 687485348, '2016-04-01');
INSERT INTO clientes (nombre, apellido, email, telefono, created_At) VALUES ('Ana', 'Montes', 'AM@gemail.com', 693462960, '2017-05-01');
INSERT INTO clientes (nombre, apellido, email, telefono, created_At) VALUES ('Josefina', 'Cardina', 'JC@gemail.com', 604385946, '2018-03-01');
INSERT INTO clientes (nombre, apellido, email, telefono, created_At) VALUES ('Olegario', 'Guerra', 'OG@gemail.com', 684382938, '2019-06-01');
INSERT INTO clientes (nombre, apellido, email, telefono, created_At) VALUES ('Saul', 'Llanos', 'SL@gemail.com', 612432142, '2020-07-01');
INSERT INTO clientes (nombre, apellido, email, telefono, created_At) VALUES ('Marta', 'Absur', 'MA@gemail.com', 600374758, '2021-08-01');
INSERT INTO clientes (nombre, apellido, email, telefono, created_At) VALUES ('Ester', 'Lendo', 'EL@gemail.com', 655484545, '2022-09-01');

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

