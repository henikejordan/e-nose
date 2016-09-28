CREATE TABLE DADOS
	(id_dados INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
	sensor VARCHAR(20) NOT NULL,
	data_hora DATETIME NOT NULL,
	temperatura REAL NOT NULL,
	luminosidade REAL NOT NULL,
	umidade_solo REAL NOT NULL,
	umidade_ar REAL NOT NULL);

INSERT INTO DADOS (sensor, data_hora, temperatura, luminosidade, umidade_ar, umidade_solo)
	VALUES ('SENSOR 1', '2016-09-27 13:59:34', 25.67, 44.76, 45.77, 100.00);	

SELECT * FROM DADOS;