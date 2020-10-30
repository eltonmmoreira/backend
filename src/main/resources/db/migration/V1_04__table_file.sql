create table FILE (
        ID SERIAL not null,
        ID_PESSOA integer not null,
        EXTENSAO varchar(5) not null,
        NOME_ORIGINAL varchar(150) not null,
        NOME varchar(100) not null,
        primary key (ID)
    );

ALTER TABLE FILE
ADD CONSTRAINT FK_PESSOA FOREIGN KEY (ID_PESSOA) REFERENCES PESSOA (ID);
