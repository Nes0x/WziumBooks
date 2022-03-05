package me.nes0x.author;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
interface SqlAuthorRepository extends AuthorRepository, JpaRepository<Author, Integer> {

}
