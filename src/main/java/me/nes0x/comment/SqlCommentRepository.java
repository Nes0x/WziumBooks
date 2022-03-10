package me.nes0x.comment;

import me.nes0x.book.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
interface SqlCommentRepository extends CommentRepository, JpaRepository<Comment, Integer> {
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query(value = "delete from comments where id = ?1", nativeQuery = true)
    @Override
    void deleteById(Integer id);
}
