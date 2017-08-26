CREATE TABLE pessoa (
  codigo      BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
  nome        VARCHAR(50) NOT NULL,
  logradouro  VARCHAR(30),
  numero      VARCHAR(30),
  complemento VARCHAR(30),
  bairro      VARCHAR(30),
  cep         VARCHAR(30),
  cidade      VARCHAR(30),
  estado      VARCHAR(30),
  ativo       BOOLEAN     NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO pessoa (nome, logradouro, numero, complemento, bairro, cep, cidade, estado, ativo)
VALUES ('João Silva', 'Rua do Abacaxi', '10', NULL, 'Brasil', '38.400-12', 'Uberlândia', 'MG', TRUE);
INSERT INTO pessoa (nome, logradouro, numero, complemento, bairro, cep, cidade, estado, ativo)
VALUES ('Maria Rita', 'Rua do Sabiá', '110', 'Apto 101', 'Colina', '11.400-12', 'Ribeirão Preto', 'SP', TRUE);
INSERT INTO pessoa (nome, logradouro, numero, complemento, bairro, cep, cidade, estado, ativo)
VALUES ('Pedro Santos', 'Rua da Bateria', '23', NULL, 'Morumbi', '54.212-12', 'Goiânia', 'GO', TRUE);
INSERT INTO pessoa (nome, logradouro, numero, complemento, bairro, cep, cidade, estado, ativo)
VALUES ('Ricardo Pereira', 'Rua do Motorista', '123', 'Apto 302', 'Aparecida', '38.400-12', 'Salvador', 'BA', TRUE);
INSERT INTO pessoa (nome, logradouro, numero, complemento, bairro, cep, cidade, estado, ativo)
VALUES ('Josué Mariano', 'Av Rio Branco', '321', NULL, 'Jardins', '56.400-12', 'Natal', 'RN', TRUE);
INSERT INTO pessoa (nome, logradouro, numero, complemento, bairro, cep, cidade, estado, ativo)
VALUES ('Pedro Barbosa', 'Av Brasil', '100', NULL, 'Tubalina', '77.400-12', 'Porto Alegre', 'RS', TRUE);
INSERT INTO pessoa (nome, logradouro, numero, complemento, bairro, cep, cidade, estado, ativo)
VALUES ('Henrique Medeiros', 'Rua do Sapo', '1120', 'Apto 201', 'Centro', '12.400-12', 'Rio de Janeiro', 'RJ', TRUE);
INSERT INTO pessoa (nome, logradouro, numero, complemento, bairro, cep, cidade, estado, ativo)
VALUES ('Carlos Santana', 'Rua da Manga', '433', NULL, 'Centro', '31.400-12', 'Belo Horizonte', 'MG', TRUE);
INSERT INTO pessoa (nome, logradouro, numero, complemento, bairro, cep, cidade, estado, ativo)
VALUES ('Leonardo Oliveira', 'Rua do Músico', '566', NULL, 'Segismundo Pereira', '38.400-00', 'Uberlândia', 'MG', TRUE);
INSERT INTO pessoa (nome, logradouro, numero, complemento, bairro, cep, cidade, estado, ativo)
VALUES ('Isabela Martins', 'Rua da Terra', '1233', 'Apto 10', 'Vigilato', '99.400-12', 'Manaus', 'AM', TRUE);