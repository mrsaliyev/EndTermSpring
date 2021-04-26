package spring.boot.endterm.service.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import spring.boot.endterm.dto.request.BookRequest;
import spring.boot.endterm.dto.request.EditBookRequest;
import spring.boot.endterm.entity.Book;
import spring.boot.endterm.exceptions.CustomConflictException;
import spring.boot.endterm.exceptions.CustomNotFoundException;
import spring.boot.endterm.repository.BookRepository;
import spring.boot.endterm.service.user.UserService;

@Service
public class BookServiceImpl implements BookService{

    private final BookRepository  repository;
    private final UserService userService;

    @Autowired
    public BookServiceImpl(BookRepository repository, UserService userService) {
        this.repository = repository;
        this.userService = userService;
    }

    @Override
    public ResponseEntity<?> booksList() {
        return new ResponseEntity<>(repository.findAll() , HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> addBook(BookRequest request) {
        boolean exists = repository.existsByName(request.getName());
        if (exists){
            throw new CustomConflictException("This book already exists");
        }
        Book book = new Book(request.getName() , request.getDescription() , userService.getAuthentication());
        save(book);
        return new ResponseEntity<>("Book added" ,  HttpStatus.OK);
    }

    @Override
    public void save(Book book) {
        repository.save(book);
    }

    @Override
    public ResponseEntity<?> editBook(EditBookRequest request) {
        Book book = findById(request.getId());
        book.setName(request.getName());
        book.setDescription(request.getDescription());
        save(book);
        return new ResponseEntity<>("book edited" , HttpStatus.OK);
    }

    @Override
    public Book findById(Long id) {
        return repository.findById(id).orElseThrow(()->new CustomNotFoundException(
                String.format("Book with id : %s not found" , id)
        ));
    }

    @Override
    public ResponseEntity<?> deleteBook(Long id) {
        Book book = findById(id);
        book.setUser(null);
        save(book);
        repository.delete(book);
        return new ResponseEntity<>("Book deleted" , HttpStatus.OK);
    }
}
