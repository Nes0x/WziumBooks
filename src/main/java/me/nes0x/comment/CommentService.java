package me.nes0x.comment;

import me.nes0x.book.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {
    private final CommentRepository repository;
    private final BookRepository bookRepository;

    CommentService(final CommentRepository repository, final BookRepository bookRepository) {
        this.repository = repository;
        this.bookRepository = bookRepository;
    }


    public CommentReadModel save(CommentWriteModel comment, int id) {
        bookRepository.findById(id).ifPresent(comment::setBook);
        Comment result = repository.save(comment.toComment());
        return new CommentReadModel(result);
    }


    public List<CommentReadModel> getAllCommentsOfIdBook(int id) {
        return repository.findByBook_Id(id).stream()
                .map(CommentReadModel::new)
                .collect(Collectors.toList());
    }
}
