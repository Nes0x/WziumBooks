create table authors(
    id int primary key,
    name varchar(50) not null,
    password varchar(255) null,
    date date null
    );
alter table books add column author_id int null;
alter table books add foreign key (author_id) references authors (id);