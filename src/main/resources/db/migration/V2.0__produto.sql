create table produto (
	id bigint not null auto_increment,
	descricao varchar(255),
	valor decimal(19,2),
	estoque_id bigint,
	primary key (id),
	foreign key (estoque_id) references estoque (id)
);