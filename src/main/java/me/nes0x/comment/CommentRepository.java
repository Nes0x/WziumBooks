package me.nes0x.comment;

import java.util.List;
import java.util.Optional;

interface CommentRepository {
    Comment save(Comment entity);
    Optional<Comment> findById(Integer id);
    void deleteById(Integer id);
    List<Comment> findByAuthor_Name(String name);
    List<Comment> findByBook_Id(Integer id);
}
