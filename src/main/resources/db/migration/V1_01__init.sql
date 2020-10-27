create table pessoa (
       id int8 not null,
        cpf varchar(11) not null,
        data_nascimento date not null,
        email varchar(400) not null,
        nome varchar(150) not null,
        primary key (id)
    );

    alter table if exists pessoa
       add constraint UK_nlwiu48rutiltbnjle59krljo unique (cpf)
