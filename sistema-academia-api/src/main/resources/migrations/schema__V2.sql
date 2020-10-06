
-- DROP DATABASE sistemaacademia;

CREATE DATABASE sistemaacademia;

CREATE SCHEMA academias;

CREATE TABLE academias.usuario
(
  id bigserial NOT NULL PRIMARY KEY,
  nome character varying(150),
  email character varying(100),
  senha character varying(20),
  data_cadastro date default now(),
  tipo_usuario integer
);

CREATE TABLE academias.aluno
(
  id bigserial NOT NULL PRIMARY KEY ,
  id_usuario bigint REFERENCES academias.usuario (id),
  nome character varying(255) NOT NULL,
  email character varying(100),
  cpf character varying(11),
  rg character varying(7),
  data_nascimento date,
  endereco character varying(100),
  bairro character varying(50),
  cep character varying(8),
  cidade character varying(100),
  uf character varying(2),
  objetivo character varying(50),
  matriculado boolean,
  estado_civil character varying(25),
  profissao character varying(100),
  idade integer,
  debito numeric(16,2),
  data_cadastro date default now()
);

CREATE TABLE academias.frequencia
(
  id bigserial NOT NULL PRIMARY KEY ,
  id_aluno bigint REFERENCES academias.aluno (id),
  data_entrada date default now(),
  data_saida date default now()
);

CREATE TABLE academias.desempenho
(
  id bigserial NOT NULL PRIMARY KEY,
  altura numeric(16,2),
  gordura_corporal numeric(16,2),
  panturrilha numeric(16,2),
  abdomen numeric(16,2),
  torax numeric(16,2),
  quadril numeric(16,2),
  pressao numeric(16,2),
  peso numeric(16,2),
  frequencia_cardiaca character varying(30),
  data_desempenho date default now()
);

CREATE TABLE academias.avaliacao_fisica
(
  id bigserial NOT NULL PRIMARY KEY ,  
  id_aluno bigint REFERENCES academias.aluno (id),
  id_desempenho bigint REFERENCES academias.desempenho (id),
  avaliador character varying(150),
  data_avaliacao date
);

CREATE TABLE academias.instrutor
(
  id bigserial NOT NULL PRIMARY KEY,
  id_usuario bigint REFERENCES academias.usuario (id),
  nome character varying(150),
  telefone character varying(150),
  data_admissao date
);

CREATE TABLE academias.modalidade
(
  id bigserial NOT NULL PRIMARY KEY,
  nome character varying(150),
  descricao character varying(150)
);

CREATE TABLE academias.treino
(
  id bigserial NOT NULL PRIMARY KEY,
  id_aluno bigint REFERENCES academias.aluno (id),
  tipo_treino character varying(150),
  nome character varying(150)
);

CREATE TABLE academias.exercicio
(
  id bigserial NOT NULL PRIMARY KEY,
  id_treino bigint REFERENCES academias.treino (id),
  nome character varying(150),
  dica character varying(150),
  grupo_muscular character varying(150),
  repeticoes integer,
  carga integer,
  series integer
);

CREATE TABLE academias.matricula
(
  id bigserial NOT NULL PRIMARY KEY ,
  id_aluno bigint REFERENCES academias.aluno (id),
  id_modalidade bigint REFERENCES academias.modalidade (id),
  data_matricula date default now(),
  desconto numeric(16,2),
  data_vencimento date,
  valor_final numeric(16,2),
  data_fim date
);

CREATE TABLE academias.pagamento
(
  id bigserial NOT NULL PRIMARY KEY,
  id_matricula bigint REFERENCES academias.matricula (id),
  valor_total numeric(16,2),
  valor_pago numeric(16,2),
  data_pagamento date,
  forma_pagamento character varying(150)
);
