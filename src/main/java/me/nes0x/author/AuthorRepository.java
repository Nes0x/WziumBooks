package me.nes0x.author;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository {
    Author save(Author source);
    Author findByName(String name);
    List<Author> findAll();
    List<Author> findByNameContainsIgnoreCase(String name);
    Optional<Author> findById(Integer id);

}
