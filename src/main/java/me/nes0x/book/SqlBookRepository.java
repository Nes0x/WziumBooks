package me.nes0x.book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
interface SqlBookRepository extends BookRepository, JpaRepository<Book, Integer> {
}
