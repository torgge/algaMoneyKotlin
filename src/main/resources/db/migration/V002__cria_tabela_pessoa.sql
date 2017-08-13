CREATE TABLE pessoa (
  codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
  nome VARCHAR(50) NOT NULL,
  ativo BOOLEAN DEFAULT FALSE,
  logradouro VARCHAR(200),
  numero VARCHAR(10),
  complemento VARCHAR(200),
  bairro VARCHAR(50),
  cep VARCHAR(8),
  cidade VARCHAR(50),
  estado VARCHAR(50)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO pessoa (codigo, nome, ativo, logradouro, numero, complemento, bairro, cep, cidade, estado) VALUES (1, 'JUCA BALA', TRUE , 'AV. FORTALEZA', '210', 'SOBRADO 03', 'LARANJEIRAS', '85868110', 'FOZ DO IGUAÇU', 'PARANÁ');
INSERT INTO pessoa (codigo, nome, ativo) VALUES (2, 'MAISTARDE', TRUE);
INSERT INTO pessoa (codigo, nome, ativo, logradouro, numero, complemento, bairro, cep, cidade, estado) VALUES (3, 'NABUCODONOSOR', TRUE , 'SÃO JOSÉ', '30', 'ESQUINA COM ALBUQUERQUE', 'SOMBRERO', '31143444', 'CURITIBA', 'PARANÁ');
INSERT INTO pessoa (codigo, nome, ativo, logradouro, numero, complemento, bairro, cep, cidade, estado) VALUES (4, 'MARIO CACHORRO', TRUE , 'SUCUPIRA', '3440', 'SANTINO BERNABEU', 'MADRID', '32185110', 'GOIÂNIA', 'GOIÁS');