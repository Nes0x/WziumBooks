create table comments(
    id int primary key,
    content varchar(300) not null,
    book_id int null,
    stars int null,
    author_id int null,
    foreign key (book_id) references books (id),
    foreign key (author_id) references authors (id)
    );