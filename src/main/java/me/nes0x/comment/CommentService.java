package me.nes0x.comment;

import me.nes0x.author.AuthorService;
import me.nes0x.book.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {
    private final CommentRepository repository;
    private final BookRepository bookRepository;
    private final AuthorService authorService;

    CommentService(final CommentRepository repository, final BookRepository bookRepository, final AuthorService authorService) {
        this.repository = repository;
        this.bookRepository = bookRepository;
        this.authorService = authorService;
    }


    public CommentReadModel save(CommentWriteModel comment, int id, String name) {
        bookRepository.findById(id).ifPresent(comment::setBook);
        comment.setAuthor(authorService.getAuthorByName(name));
        Comment result = repository.save(comment.toComment());
        return new CommentReadModel(result);
    }

    public boolean checkComment(String name, int id) {
        return repository.findByAuthor_Name(name).contains(repository.findById(id).get());
    }

    public void delete(int id) {
        repository.deleteById(id);
    }


    public List<CommentReadModel> getAllCommentsOfIdBook(int id) {
        return repository.findByBook_Id(id).stream()
                .map(CommentReadModel::new)
                .collect(Collectors.toList());
    }
}
