CREATE TABLE DADOS
	(id_dados INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
	data_hora DATETIME NOT NULL,
	temperatura REAL NOT NULL,
	luminosidade REAL NOT NULL,
	umidade_solo REAL NOT NULL,
	umidade_ar REAL NOT NULL);

INSERT INTO DADOS (data_hora,temperatura,luminosidade,umidade_ar,umidade_solo)
	VALUES ('2016-09-27 13:59:34', 25.67, 44.76, 45.77, 100.00);	

SELECT * FROM DADOS;