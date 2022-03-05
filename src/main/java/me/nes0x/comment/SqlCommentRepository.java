package me.nes0x.comment;

import me.nes0x.book.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
interface SqlCommentRepository extends CommentRepository, JpaRepository<Comment, Integer> {

}
