INSERT INTO departamentos (nombre, ubicacion) VALUES ('IT', 'Langreo');
INSERT INTO departamentos (nombre, ubicacion) VALUES ('IOT', 'Oviedo');
INSERT INTO departamentos (nombre, ubicacion) VALUES ('Administracion', 'Gijón');


INSERT INTO empleados (dni, nombre, salario, telefono, cod_departamento) VALUES ('14404467-A', 'Alvaro', 1234.45, 478394321, 3);
INSERT INTO empleados (dni, nombre, salario, telefono, cod_departamento) VALUES  ('26738495-D', 'Raul', 1356.56, 567849352, 2);


INSERT INTO jefes (dni, nombre, salario, telefono, cod_departamento) VALUES ('25537849-J', 'Olegario', 20000, 467839504, 2);
INSERT INTO jefes (dni, nombre, salario, telefono, cod_departamento) VALUES ('12453674-S', 'Amanda', 35000, 564783931, 1);


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