-- Scripts para criação do projeto --

CREATE TABLE usuario (
	nome VARCHAR(100) NOT NULL,
   	email VARCHAR(100) NOT NULL,
   senha VARCHAR(100) NOT NULL,
   PRIMARY KEY (email) 
);

CREATE TABLE telefone (
   id INTEGER IDENTITY PRIMARY KEY,
   ddd INT NOT NULL,
   numero VARCHAR(10) NOT NULL,
   tipo VARCHAR(10) NOT NULL,
   email_usuario VARCHAR(100) NOT NULL
);

ALTER TABLE telefone
ADD FOREIGN KEY (EMAIL_USUARIO) 
REFERENCES USUARIO(EMAIL);
