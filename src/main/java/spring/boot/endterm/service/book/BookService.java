package spring.boot.endterm.service.book;

import org.springframework.http.ResponseEntity;
import spring.boot.endterm.dto.request.BookRequest;
import spring.boot.endterm.dto.request.EditBookRequest;
import spring.boot.endterm.entity.Book;

public interface BookService {
    ResponseEntity<?> booksList();
    ResponseEntity<?> addBook(BookRequest request);
    void save(Book book);
    ResponseEntity<?> editBook(EditBookRequest request);
    Book findById(Long id);
    ResponseEntity<?> deleteBook(Long id);
}
