CREATE TABLE Apartamento (
  Numero INT NOT NULL,
  Bloco CHAR NOT NULL,
  Condominio_Id INTEGER UNSIGNED NOT NULL,
  Pessoa_id INT NOT NULL,
  Diretorio_boleto VARCHAR NULL,
  PRIMARY KEY(Numero, Bloco, Condominio_Id),
  INDEX Apartamento_condominio(Condominio_Id),
  INDEX Apartamento_morador(Pessoa_id),
  INDEX Apartamento_proprietario(Pessoa_id)
);

CREATE TABLE Condominio (
  Id INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  Pessoa_id INT NOT NULL,
  Nome VARCHAR NULL,
  Rua VARCHAR NULL,
  Bairro VARCHAR NULL,
  Ciddade VARCHAR NULL,
  PRIMARY KEY(Id),
  INDEX Condominio_sindico(Pessoa_id)
);

CREATE TABLE Emails_pessoa (
  Endereco_email VARCHAR NOT NULL AUTO_INCREMENT,
  Pessoa_id INT NOT NULL,
  PRIMARY KEY(Endereco_email, Pessoa_id),
  INDEX Emails_pessoa_FKIndex1(Pessoa_id)
);

CREATE TABLE Pessoa (
  id INT NOT NULL AUTO_INCREMENT,
  CPF VARCHAR NULL,
  Nome VARCHAR NULL,
  PRIMARY KEY(id)
);

CREATE TABLE Telefones_pessoa (
  Telefone_pessoa VARCHAR NOT NULL AUTO_INCREMENT,
  Pessoa_id INT NOT NULL,
  PRIMARY KEY(Telefone_pessoa, Pessoa_id),
  INDEX Telefones_pessoa_FKIndex1(Pessoa_id)
);

CREATE TABLE Tipo_pessoa (
  Tipo CHAR NOT NULL AUTO_INCREMENT,
  Pessoa_id INT NOT NULL,
  PRIMARY KEY(Tipo, Pessoa_id),
  INDEX Tipo_pessoa_FKIndex1(Pessoa_id)
);


