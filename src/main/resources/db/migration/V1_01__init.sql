create table PESSOA (
       ID SERIAL not null,
        CPF varchar(11) not null,
        DATA_NASCIMENTO date not null,
        EMAIL varchar(400) not null,
        NOME varchar(150) not null,
        primary key (ID)
    );

    alter table if exists PESSOA
       add constraint UK_CPF unique (CPF)
