drop database if exists projetoFinalCoti;
create database projetoFinalCoti;
use projetoFinalCoti;

create table EMPRESA(
		IDEMPRESA	  integer			auto_increment,
        NOMEFANTASIA  varchar(150)	not null,
        RAZAOSOCIAL	  varchar(100)	not null unique,
        CNPJ		varchar(50)		not null unique,
        primary key(IDEMPRESA));
        
create table FUNCIONARIO(
		IDFUNCIONARIO		integer			auto_increment,
        NOME		varchar(150)	not null,
        CPF varchar(15) unique,
        DATAADMISSAO date not null,
        SALARIO double not null,
        IDEMPRESA integer not null,
        
        
        primary key(IDFUNCIONARIO),
        foreign key(IDEMPRESA) references EMPRESA(IDEMPRESA));