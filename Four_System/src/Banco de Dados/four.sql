-----------------------------------------------
-- Criando o esquema banco
-----------------------------------------------
DROP SCHEMA IF EXISTS banco_four CASCADE;
CREATE SCHEMA banco_four;
SET search_path TO banco_four;

CREATE SEQUENCE seq_sindico START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE seq_condominio START WITH 1000 INCREMENT BY 1;
CREATE SEQUENCE seq_morador START WITH 2 INCREMENT BY 2;
CREATE SEQUENCE seq_proprietario START WITH 1 INCREMENT BY 2;

-- -----------------------------------------------------
-- Tabela PESSOA
-- -----------------------------------------------------
CREATE TABLE pessoa (
  id   INT,
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
  PRIMARY KEY (tipo,id_pessoa),
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
  id_pessoa     INT,
  telefone    	VARCHAR(15),
  -- restrições
  CONSTRAINT pk_telefones_pessoa
  PRIMARY KEY (id_pessoa,telefone),
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
  id_pessoa     INT,
  email    	VARCHAR(50),
  -- restrições
  CONSTRAINT pk_emails_pessoa
  PRIMARY KEY (id_pessoa,email),
  
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
  id	       	INT,
  nome	   	VARCHAR NOT NULL,
  id_sindico   	INT,
  rua	 	VARCHAR(50),
  bairro      	VARCHAR(50),
  cidade	VARCHAR(50),
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
  id_condominio   	INT UNIQUE NOT NULL,
  numero          	INT,
  bloco	          	VARCHAR(5),
  
  id_proprietario 	INT UNIQUE NOT NULL,
  id_morador      	INT UNIQUE NOT NULL,
  diretorio_boleto	VARCHAR(50),
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
