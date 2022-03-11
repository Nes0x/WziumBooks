package me.nes0x.book;

import me.nes0x.author.AuthorRepository;
import me.nes0x.comment.CommentService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {
    private final BookRepository repository;
    private final AuthorRepository authorRepository;
    private final CommentService commentService;

    BookService(final BookRepository repository, final AuthorRepository authorRepository, final CommentService commentService) {
        this.repository = repository;
        this.authorRepository = authorRepository;
        this.commentService = commentService;
    }

    public BookReadModel save(BookWriteModel book, MultipartFile file, String name) throws IOException {
        book.setAuthor(authorRepository.findByName(name));
        if (!file.isEmpty()) {
            book.setData(file.getBytes());
        }

        Book result = repository.save(book.toBook());
        return new BookReadModel(result);
    }

    public List<BookReadModel> getAllBooks() {
        return repository.findAll().stream()
                .map(BookReadModel::new)
                .collect(Collectors.toList());
    }

    public BookReadModel getBookById(int id) {
        return repository.findById(id).map(BookReadModel::new).get();
    }

    public List<BookReadModel> getBooksByTitle(final String title) {
        return repository.findByTitleContainsIgnoreCase(title)
                .stream()
                .map(BookReadModel::new)
                .collect(Collectors.toList());
    }

    public List<BookReadModel> getBooksByAuthorName(final String name) {
        return repository.findByAuthor_Name(name)
                .stream()
                .map(BookReadModel::new)
                .collect(Collectors.toList());
    }

    public boolean deleteBook(int id, String name) {
        Book book = repository.findById(id).get();
        if (repository.findByAuthor_Name(name).contains(book)) {
            book.getComments().forEach(comment -> commentService.delete(comment.getId()));
            repository.deleteById(id);
            return true;
        }
        return false;
    }

}
