# stefanini-servico
Este documento tem como finalidade auxiliar na instalação e uso do sistema de cadastro de pessoas.

========= Os Projetos ===========================================
O sistema é composto por dois projetos são eles: 

Interface - PessoaSystem
git clone https://github.com/mmss07/stefanini-pessoa-system.git

Serviço REST = stefanini-servico
https://github.com/mmss07/stefanini-servico.git
O serviço esta hospedado em:
https://dashboard.heroku.com/apps/mmss20200712
Para entra é só acessar:
https://id.heroku.com/login
marcelomauricio@hotmail.com	
@Mariza07 


==================================================================================
Banco de dados usado PostgreeSQL 
usuario = postgres
senha = root

usuario para login no sistema
login = **basic**
senha = **basic**

======INICIO DOS END POINT DO SERVIÇO==============

GET - Consulta de Pessoas  Remoto = https://mmss20200712.herokuapp.com/pessoas
GET - Consulta de pessoas local = http://localhost:8090/pessoas
GET - Pesquisa Pessoas Local Cpf = http://localhost:8090/pessoas/cpf/88200442420
GET - Pesquisa Pessoas Remoto Cpf =  https://mmss20200712.herokuapp.com/pessoas/cpf/88200442420
POST - Inserir Pessoa Local = http://localhost:8090/pessoas
POST - Inserir Pessoa Remoto =  https://mmss20200712.herokuapp.com/pessoas
PUT - Alterar Pessoa Local = http://localhost:8090/pessoas/8
PUT - Alterar Pessoa Remoto =  https://mmss20200712.herokuapp.com/pessoas
DELETE - Delete Pessoa Local = http://localhost:8090/3
DELETE - Delete Pessoa Remote =  https://mmss20200712.herokuapp.com/pessoas/2

======FIM DOS END POINT DO SERVIÇO===================

Instale o banco de dados postgres e com o aberto efetue os seguites comandos:

CREATE DATABASE stefanini
  WITH OWNER = postgres
       ENCODING = 'UTF8'
       TABLESPACE = pg_default
       LC_COLLATE = 'Portuguese_Brazil.1252'
       LC_CTYPE = 'Portuguese_Brazil.1252'
       CONNECTION LIMIT = -1;
Aguarde 5 segundos

-- Table: pessoa

-- DROP TABLE pessoa;

CREATE TABLE pessoa
(
  id bigint NOT NULL,
  cpf character varying(255),
  dataatualizacao date,
  datacadastro date,
  datanascimento date,
  email character varying(255),
  nacionalidade character varying(255),
  naturalidade character varying(255),
  nome character varying(255),
  CONSTRAINT pessoa_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE pessoa
  OWNER TO postgres;
  
  -- Table: usuario

-- DROP TABLE usuario;

CREATE TABLE usuario
(
  idusuario bigint NOT NULL,
  email character varying(120),
  login character varying(20) NOT NULL,
  nome character varying(60) NOT NULL,
  senha character varying(125) NOT NULL,
  fidelidade character varying(5),
  CONSTRAINT usuario_pkey PRIMARY KEY (idusuario)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE usuario
  OWNER TO postgres;

-- Insert do usuário defualt  
INSERT INTO usuario(
            idusuario, email, login, nome, senha)
    VALUES (2, 'stefanini@stefanini.com', '**basic**', 'Stefanini', 'c2a103f5284e8ed5f951f46f57df0903b3256689b6e1692dfc87d295107909b0');  
  
  
==============================================FIM DO CRIPT DE BANCO ======================================  
  --Pronto banco instalado
  
  Segue JSON para insert via Postman
  
  [ 
   { 
      "id":1,
      "nome":"Marcelo Mauricio",
      "email":"mm@hotmail.com",
      "cpf":"88200442420",
      "nacionalidade":"Brasileiro",
      "naturalidade":"Recifense",
      "datacadastro":1581908400000,
      "dataatualizacao":1581908400000,
      "datanascimento":1581908400000
   },
   { 
      "id":2,
      "nome":"Malke Mauricio",
      "email":"malke@hotmail.com",
      "cpf":"88200442420",
      "nacionalidade":"Brasileiro",
      "naturalidade":"Paulista",
      "datacadastro":1581908400000,
      "dataatualizacao":1581908400000,
      "datanascimento":1581908400000
   },
   { 
      "id":3,
      "nome":"Marcio Mauricio",
      "email":"malke@hotmail.com",
      "cpf":"88200442420",
      "nacionalidade":"Brasileiro",
      "naturalidade":"Paulista",
      "datacadastro":1581908400000,
      "dataatualizacao":1581908400000,
      "datanascimento":1581908400000
   }
]
  
  

