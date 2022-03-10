package me.nes0x.book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
interface SqlBookRepository extends BookRepository, JpaRepository<Book, Integer> {

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query(value = "delete from books where id = ?1", nativeQuery = true)
    @Override
    void deleteById(Integer id);
}
