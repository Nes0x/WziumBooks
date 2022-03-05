create table comments(
    id int primary key,
    title varchar(50) not null,
    content varchar(300) not null,
    book_id int null,
    foreign key (book_id) references books (id)
    );