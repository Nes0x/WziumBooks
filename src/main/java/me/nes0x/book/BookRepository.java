package me.nes0x.book;


import java.util.List;
import java.util.Optional;

public interface BookRepository {
    Book save(Book entity);
    List<Book> findAll();
    Optional<Book> findById(Integer id);
    List<Book> findByTitleContainsIgnoreCase(String title);
}
