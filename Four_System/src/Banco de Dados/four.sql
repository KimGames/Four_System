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
-- Tabela SINDICO
-- -----------------------------------------------------
CREATE TABLE sindico (
  id_sindico INT,
  cpf		     CHAR(15),
  tipo       CHAR(1),
  -- restrições
  CONSTRAINT pk_sindico PRIMARY KEY (id_sindico)
);

-- -----------------------------------------------------
-- Tabela PESSOA
-- -----------------------------------------------------
CREATE TABLE pessoa (
  cpf  CHAR(15),
  nome VARCHAR(100),
  -- restrições
  CONSTRAINT pk_pessoa PRIMARY KEY (cpf)
);

-- -----------------------------------------------------
-- Tabela CONDOMINIO
-- -----------------------------------------------------
CREATE TABLE condominio (
  id_condominio INT,
  nome		      VARCHAR(50),
  sindico       INT,
  -- restrições
  CONSTRAINT pk_condominio PRIMARY KEY (id_condominio),
  CONSTRAINT fk_sindico_condominio FOREIGN KEY (sindico) REFERENCES sindico (id_sindico) ON DELETE NO ACTION ON UPDATE CASCADE
);

-- -----------------------------------------------------
-- Tabela MORADOR
-- -----------------------------------------------------
CREATE TABLE morador (
  id_morador  INT,
  pessoa_cpf  CHAR(15),
  pessoa_nome VARCHAR(100),
  -- restrições
  CONSTRAINT pk_morador PRIMARY KEY (id_morador, pessoa_cpf),
  CONSTRAINT fk_morador_cpf FOREIGN KEY (pessoa_cpf) REFERENCES pessoa (cpf) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT fk_morador_nome FOREIGN KEY (pessoa_nome) REFERENCES pessoa (nome) ON DELETE NO ACTION ON UPDATE CASCADE
);

-- -----------------------------------------------------
-- Tabela PROPRIETARIO
-- -----------------------------------------------------
CREATE TABLE proprietario (
  id_proprietario INT,
  pessoa_cpf      CHAR(15),
  pessoa_nome     VARCHAR(100),
  -- restrições
  CONSTRAINT pk_proprietario PRIMARY KEY (id_proprietario, pessoa_cpf),
  CONSTRAINT fk_proprietario_cpf FOREIGN KEY (pessoa_cpf) REFERENCES pessoa (cpf) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT fk_proprietario_nome FOREIGN KEY (pessoa_nome) REFERENCES pessoa (nome) ON DELETE NO ACTION ON UPDATE CASCADE
);

-- -----------------------------------------------------
-- Tabela APARTAMENTO
-- -----------------------------------------------------
CREATE TABLE apartamento (
  numero          INT,
  bloco	          CHAR(10),
  condominio      INT,
  id_proprietario INT,
  proprietario    CHAR(15),
  id_morador      INT,
  morador         CHAR(15),
  -- restrições
  CONSTRAINT pk_apartamento PRIMARY KEY (numero, bloco, condominio),
  CONSTRAINT fk_apartamento_condominio FOREIGN KEY (condominio) REFERENCES condominio (id_condominio) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT fk_apartamento_proprietario FOREIGN KEY (id_proprietario, proprietario) REFERENCES proprietario (id_proprietario, pessoa_cpf) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT fk_apartamento_morador FOREIGN KEY (id_morador, morador) REFERENCES morador (id_morador, pessoa_cpf) ON DELETE NO ACTION ON UPDATE CASCADE
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
