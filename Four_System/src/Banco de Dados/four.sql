-----------------------------------------------
-- Criando o esquema banco
-----------------------------------------------
DROP SCHEMA IF EXISTS banco_four CASCADE;
CREATE SCHEMA banco_four;
SET search_path TO banco_four;
/*
CREATE SEQUENCE seq_empr START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE seq_num_funcional START WITH 10 INCREMENT BY 10;
CREATE SEQUENCE seq_id_contaC START WITH 2 INCREMENT BY 2;
CREATE SEQUENCE seq_id_contaP START WITH 1 INCREMENT BY 2;
CREATE SEQUENCE seq_id_op START WITH 100 INCREMENT BY 100;
CREATE SEQUENCE seq_numero START WITH 1001 INCREMENT BY 1;
*/
-- -----------------------------------------------------
-- Tabela CONDOMINIO
-- -----------------------------------------------------
CREATE TABLE condominio (
  nome		  VARCHAR(50),
  -- restrições
  CONSTRAINT pk_condominio PRIMARY KEY (nome)
);

-- -----------------------------------------------------
-- Tabela MORADOR
-- -----------------------------------------------------
CREATE TABLE morador (
  apartamento	  VARCHAR(10),
  condominio    VARCHAR(50),
  paga	        BOOLEAN NOT NULL,
  -- restrições
  CONSTRAINT pk_morador PRIMARY KEY (apartamento),
  CONSTRAINT fk_morador_condominio FOREIGN KEY (condominio) REFERENCES condominio (nome) ON DELETE NO ACTION ON UPDATE CASCADE
);

-- -----------------------------------------------------
-- Tabela PROPRIETARIO
-- -----------------------------------------------------
CREATE TABLE proprietario (
  condominio    VARCHAR(50),
  apartamento	  VARCHAR(10),
  morador	      VARCHAR(50),
  -- restrições
  CONSTRAINT pk_proprietario PRIMARY KEY (condominio, apartamento),
  CONSTRAINT fk_proprietario_condominio FOREIGN KEY (condominio) REFERENCES morador (condominio) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT fk_proprietario_apartamento FOREIGN KEY (apartamento) REFERENCES morador (apartamento) ON DELETE NO ACTION ON UPDATE CASCADE
);

-- -----------------------------------------------------
-- Tabela EMAIL_BOLETO
-- -----------------------------------------------------
CREATE TABLE email_boleto (
  condominio     VARCHAR(50),
  apartamento	   VARCHAR(10),
  email	         VARCHAR(100)	NOT NULL,
  -- restrições
  CONSTRAINT pk_email_boleto PRIMARY KEY (condominio, apartamento, email),
  CONSTRAINT fk_email_boleto_condominio FOREIGN KEY (condominio) REFERENCES condominio (nome) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT fk_email_boleto_apartamento FOREIGN KEY (apartamento) REFERENCES morador (apartamento) ON DELETE NO ACTION ON UPDATE CASCADE
);


-- Povoamento (Alimentação Inicial do Banco de Dados):


INSERT INTO condominio VALUES ('Condominio_1'),
			                        ('Condominio_2'),
                      			  ('Condominio_3'),
                      			  ('Condominio_4'),
                      			  ('Condominio_5');

INSERT INTO morador VALUES ('Condominio_1', '101', TRUE),
                			     ('Condominio_2', '103', TRUE),
                			     ('Condominio_2', '104', TRUE),
                			     ('Condominio_1', '202', FLASE),
                			     ('Condominio_3', '101', TRUE),
                           ('Condominio_4', '301', TRUE),
                			     ('Condominio_5', '101A', FLASE),
                			     ('Condominio_5', '101B', TRUE);

INSERT INTO email_boleto VALUES ('Condominio_1', '101', 'kimgames1@gmail.com'),
                                ('Condominio_1', '101', 'contato.kimgames@gmail.com'),
                      			    ('Condominio_2', '103', 'kim-ruan@hotmail.com'),
                      			    ('Condominio_2', '104', 'fourassociados@gmai.com'),
                      			    ('Condominio_1', '202', 'kimgames1@gmail.com'),
                      			    ('Condominio_3', '101', 'contato.kimgames@gmail.com'),
                                ('Condominio_4', '301', 'fourassociados@gmail.com'),
                      			    ('Condominio_5', '101A', 'contato.kimgames@gmail.com'),
                      			    ('Condominio_5', '101B', 'kim-ruan@hotmail.com');

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
