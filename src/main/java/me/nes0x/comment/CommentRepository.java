package me.nes0x.comment;

import java.util.List;

interface CommentRepository {
    Comment save(Comment entity);
    List<Comment> findByBook_Id(Integer id);
}
