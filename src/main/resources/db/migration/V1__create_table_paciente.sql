create table paciente
(
	id serial primary key not null,
	nome varchar(60),
	sobrenome varchar(100),
	cpf varchar(15) not null,
	email varchar(100)
);