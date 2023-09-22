create table agenda
(
	id serial primary key,
	descricao varchar(200),
	data_hora timestamp,
	data_criacao timestamp,
	paciente_id integer not null,
	CONSTRAINT FK_PACIENTE_AGENDA foreign key (paciente_id) references paciente(id)
);