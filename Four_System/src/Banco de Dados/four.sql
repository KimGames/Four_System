﻿-----------------------------------------------
-- Criando o esquema banco
-----------------------------------------------
DROP SCHEMA IF EXISTS banco_four CASCADE;
CREATE SCHEMA banco_four;
SET search_path TO banco_four;

CREATE SEQUENCE seq_pessoa START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE seq_condominio START WITH 1000 INCREMENT BY 1;
--CREATE SEQUENCE seq_morador START WITH 2 INCREMENT BY 2;
--CREATE SEQUENCE seq_proprietario START WITH 1 INCREMENT BY 2;

-- -----------------------------------------------------
-- Tabela PESSOA
-- -----------------------------------------------------
CREATE TABLE pessoa (
  id   INT DEFAULT nextval('seq_pessoa'),
  CPF  CHAR(15) UNIQUE NOT NULL,
  Nome VARCHAR(50) NOT NULL,
  -- restrições
  CONSTRAINT pk_pessoa
  PRIMARY KEY(id)
);


-- -----------------------------------------------------
-- Tabela TIPO_PESSOA
-- -----------------------------------------------------
CREATE TABLE tipo_pessoa (
  tipo VARCHAR(15) NOT NULL,
  id_pessoa INT NOT NULL,
  -- restrições
  CONSTRAINT pk_tipo_pessoa
  PRIMARY KEY (tipo, id_pessoa),

  CONSTRAINT fk_tipo_pessoa
  FOREIGN KEY (id_pessoa)
  REFERENCES pessoa (id)
  ON DELETE NO ACTION
  ON UPDATE CASCADE
);


-- -----------------------------------------------------
-- Tabela TELEFONES_PESSOA
-- -----------------------------------------------------
CREATE TABLE telefones_pessoa (
  id_pessoa     INT NOT NULL,
  telefone    	VARCHAR(20) UNIQUE NOT NULL,
  -- restrições
  CONSTRAINT pk_telefones_pessoa
  PRIMARY KEY (id_pessoa, telefone),

  CONSTRAINT fk_telefones_pessoa
  FOREIGN KEY (id_pessoa)
  REFERENCES pessoa (id)
  ON DELETE NO ACTION
  ON UPDATE CASCADE
);

-- -----------------------------------------------------
-- Tabela EMAILS_PESSOA
-- -----------------------------------------------------
CREATE TABLE emails_pessoa (
  id_pessoa     INT NOT NULL,
  email    	VARCHAR(60) UNIQUE NOT NULL,
  -- restrições
  CONSTRAINT pk_emails_pessoa
  PRIMARY KEY (id_pessoa, email),

  CONSTRAINT fk_emails_pessoa
  FOREIGN KEY (id_pessoa)
  REFERENCES pessoa (id)
  ON DELETE NO ACTION
  ON UPDATE CASCADE
);

-- -----------------------------------------------------
-- Tabela CONDOMINIO
-- -----------------------------------------------------
CREATE TABLE condominio (
  id	       	INT DEFAULT nextval('seq_condominio'),
  nome	   	VARCHAR UNIQUE NOT NULL,
  id_sindico   	INT NOT NULL,
  rua	 	VARCHAR(50) NOT NULL,
  bairro      	VARCHAR(50) NOT NULL,
  cidade	VARCHAR(50) NOT NULL,
  -- restrições
  CONSTRAINT pk_condominio
  PRIMARY KEY (id),

  CONSTRAINT fk_condominio_pessoa
  FOREIGN KEY (id_sindico)
  REFERENCES pessoa (id)
  ON DELETE NO ACTION
  ON UPDATE CASCADE
);

-- -----------------------------------------------------
-- Tabela APARTAMENTO
-- -----------------------------------------------------
CREATE TABLE apartamento (
  numero          	INT NOT NULL,
  bloco	          	VARCHAR(5) NOT NULL,
  id_condominio   	INT NOT NULL,

  id_proprietario 	INT NOT NULL,
  id_morador      	INT UNIQUE NOT NULL,
  diretorio_boleto	VARCHAR(200) UNIQUE,
  -- restrições
  CONSTRAINT pk_apartamento
  PRIMARY KEY (numero, bloco, id_condominio),

  CONSTRAINT fk_apartamento_condominio
  FOREIGN KEY (id_condominio)
  REFERENCES condominio (id)
  ON DELETE NO ACTION
  ON UPDATE CASCADE,

  CONSTRAINT fk_apartamento_proprietario
  FOREIGN KEY (id_proprietario)
  REFERENCES pessoa (id)
  ON DELETE NO ACTION
  ON UPDATE CASCADE,

  CONSTRAINT fk_apartamento_morador
  FOREIGN KEY (id_morador)
  REFERENCES pessoa (id)
  ON DELETE NO ACTION
  ON UPDATE CASCADE
);

-- Povoamento (Alimentação Inicial do Banco de Dados):

-- Pessoa
INSERT INTO pessoa VALUES (DEFAULT, '111.111.111-10', 'Pessoa1'),
			                    (DEFAULT, '111.111.111-20', 'Pessoa2'),
              			      (DEFAULT, '111.111.111-30', 'Pessoa3'),
              			      (DEFAULT, '111.111.111-40', 'Pessoa4'),
              			      (DEFAULT, '111.111.111-50', 'Pessoa5'),
              			      (0, 'Admin', 'Administradora');

-- Tipo_Pessoa
INSERT INTO tipo_pessoa VALUES ('Proprietario', 1),
			                         ('Morador', 2),
                    			     ('Proprietario', 3),
                    			     ('Proprietario', 4),
                    			     ('Morador', 5);

-- Telefones_Pessoa
INSERT INTO telefones_pessoa VALUES (1, '(34)9 9999-9999'),
                                    (1, '(34)9 8888-9999'),
                                    (2, '(64)9 8231-1010'),
                                    (3, '(34)9 7777-9988'),
                                    (3, '(34)3226-6547'),
                                    (3, '(11)9 7896-9299'),
                                    (3, '(62)9 8345-9981'),
                                    (4, '(34)3333-3333'),
                                    (5, '(34)3232-3232'),
                                    (5, '(34)9 0547-6666');

-- Emails_Pessoa
INSERT INTO emails_pessoa VALUES (1, 'pessoa1@email.com'),
                                 (1, 'pessoa1@email.com.br'),
                                 (2, 'pessoa2@email.com'),
                                 (3, 'pessoa3@email.com'),
                                 (3, 'pessoa3@email.com.br'),
                                 (3, 'pessoa3.outro@email.com'),
                                 (3, 'pessoa3.outro@email.com.br'),
                                 (4, 'pessoa4@email.com'),
                                 (5, 'pessoa5@email.com'),
                                 (5, 'pessoa5@email.com.br');

-- Condominio
INSERT INTO condominio VALUES (DEFAULT, 'Condominio1', 1, 'Rua 1', 'Santa Monica', 'Uberlândia'),
                              (DEFAULT, 'Condominio2', 0, 'Rua 2', 'Martins', 'Uberlândia'),
                              (DEFAULT, 'Condominio3', 0, 'Rua 3', 'Santa Monica', 'Uberlândia'),
                              (DEFAULT, 'Condominio4', 3, 'Rua 4', 'Santa Monica', 'Uberlândia'),
                              (DEFAULT, 'Condominio5', 4, 'Rua 5', 'Umuarama', 'Uberlândia');

-- Condominio
INSERT INTO apartamento VALUES (101, '1B', 1000, 1, 2, 'C:\\Pasta_Condominio\\Pasta_Boleto_Ano\\Pasta_Mes\\101.pdf'),
                               (102, '1B', 1000, 3, 3, 'C:\\Pasta_Condominio\\Pasta_Boleto_Ano\\Pasta_Mes\\102.pdf'),
                               (103, '1B', 1000, 3, 5, 'C:\\Pasta_Condominio\\Pasta_Boleto_Ano\\Pasta_Mes\\103.pdf'),
                               (104, '1B', 1000, 1, 1, 'C:\\Pasta_Condominio\\Pasta_Boleto_Ano\\Pasta_Mes\\104.pdf');


/*
-- Todos os condominios
SELECT * FROM condominio

--Condominio especifico pelo nome
SELECT * FROM condominio WHERE nome LIKE '<nome>'

--Condominios especificos pelo começo de uma letra especifica

SELECT * FROM condominio WHERE nome LIKE '<letra>%'

--Condominios especificos pelo nome da rua

SELECT * FROM condominio WHERE rua LIKE '<rua>'

--Condominios especificos pelo nome do bairro

SELECT * FROM condominio WHERE bairro LIKE '<bairro>'

--Condominio que o morador especificado na consulta mora

SELECT * FROM condominio c, apartamento a, morador m
WHERE p.nome = '<nome>' AND  m.id = a.id_morador
AND c.id = a.id_condominio

--Condominio que o proprietario especificado na colsuta possui apartamento(s)

SELECT * FROM condominio c,apartamento a, proprietario p WHERE p.nome = '<nome>'
AND p.id = a.id_proprietario AND a.id_condominio = c.id

--Todos os proprietarios

SELECT * FROM proprietario

--Proprietario especifico pelo nome (permutação de todos os nomes q compoem o nome do prop)
SELECT * FROM proprietario WHERE nome LIKE '%<nome>%'

--Proprietario especifico pelo CPF

SELECT * FROM proprietario WHERE cpf = '<cpf>'

--Proprietario especifico pelos apartamentos que possui

SELECT * FROM condominio c, apartamento a, proprietario p
WHERE c.nome = '<nome>' AND c.id = a.id_condominio
AND a.id_proprietario = p.id

--Todos os moradores

SELECT * FROM morador


-- Morador especifico pelo nome (permutação de todos os nomes q compoem o nome do prop)

SELECT * FROM morador WHERE nome = '%<nome>%'

--Morador especifico pelo CPF

SELECT * FROM morador WHERE cpf = '<cpf>'

--Morador especifico pelo apartamento que mora

SELECT * FROM condominio c, apartamento a, morador m
WHERE c.nome = '<nome>' AND c.id = a.id_condominio
AND a.id_morador = m.id



-------------------------------
-- Stored Procedure e Tiggers:

-- Stored Proceddure:

CREATE OR REPLACE FUNCTION atualiza_data_acesso (idConta INT)
RETURNS VOID AS
$$
BEGIN

IF idConta % 2 = 0 THEN
-- quer dizer que ele é conta corrente
UPDATE conta_corrente SET ultimo_acesso = localtimestamp WHERE id_conta = idConta;

ELSE
-- quer dizer que ele é uma conta poupanca
UPDATE conta_poupanca SET ultimo_acesso = localtimestamp WHERE id_conta = idConta;

END IF;

END;
$$
language plpgsql;

-- Triggers:

CREATE OR REPLACE FUNCTION operacao()
RETURNS trigger AS
$$
DECLARE saldoNovo REAL;
DECLARE quantiaSaque REAL;
DECLARE quantiaDeposito REAL;
DECLARE tipoOp VARCHAR(10);
BEGIN

IF EXISTS(SELECT * FROM conta_corrente WHERE id_conta = NEW.id_conta) THEN
RAISE NOTICE 'EXISTE!';

SELECT NEW.tipo INTO tipoOp;

IF tipoOp = 'saque' THEN

SELECT saldo FROM conta_corrente WHERE id_conta = NEW.id_conta INTO saldoNovo;
SELECT NEW.valor INTO quantiaSaque;

IF saldoNovo >= quantiaSaque THEN
RAISE NOTICE 'TEM SALDO: %',saldoNovo;

saldoNovo = saldoNovo - quantiaSaque;

UPDATE conta_corrente SET saldo = saldoNovo WHERE id_conta = NEW.id_conta;
RAISE NOTICE 'NOVO SALDO: %',saldoNovo;

ELSE

RAISE EXCEPTION 'SALDO INSUFICIENTE!';

END IF;

ELSIF tipoOp = 'deposito' THEN

SELECT saldo FROM conta_corrente WHERE id_conta = NEW.id_conta INTO saldoNovo;

SELECT NEW.valor INTO quantiaDeposito;

saldoNovo = saldoNovo + quantiaDeposito;

UPDATE conta_corrente SET saldo = saldoNovo WHERE id_conta = NEW.id_conta;

RAISE NOTICE 'NOVO SALDO: %', saldoNovo;

ELSE

RAISE EXCEPTION 'OPERACAO INVALIDA';

END IF;

ELSE
RAISE EXCEPTION 'CONTA NAO EXISTE';

END IF;

RETURN NULL;
END;
$$
language plpgsql;

CREATE TRIGGER operacao_trigger
AFTER INSERT ON operacao_bancaria FOR EACH ROW EXECUTE PROCEDURE operacao();


CREATE FUNCTION gera_cupom()
RETURNS trigger AS $$
DECLARE valorOp REAL;
BEGIN

SELECT NEW.valor INTO valorOp;

IF valorOp >= 5000 THEN

RAISE NOTICE 'CUPOM FOI GERADO COM EXITO';
INSERT INTO cupom VALUES(DEFAULT, localtimestamp + interval '30 days', NEW.descricao, NEW.id_conta, NEW.id_op, NEW.nome_agencia);

END IF;

RETURN NULL;
END;
$$
language plpgsql;



CREATE TRIGGER cupom_trigger
AFTER INSERT ON operacao_bancaria FOR EACH ROW EXECUTE PROCEDURE gera_cupom();

*/
