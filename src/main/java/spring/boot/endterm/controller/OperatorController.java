package spring.boot.endterm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import spring.boot.endterm.dto.request.BookRequest;
import spring.boot.endterm.dto.request.ChangePasswordRequest;
import spring.boot.endterm.dto.request.EditBookRequest;
import spring.boot.endterm.service.book.BookService;
import spring.boot.endterm.service.user.UserService;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/system")
@PreAuthorize("hasAnyRole('ROLE_OPERATOR')")
public class OperatorController {

    private final UserService userService;
    private final BookService bookService;

    @Autowired
    public OperatorController(UserService userService, BookService bookService) {
        this.userService = userService;
        this.bookService = bookService;
    }


    @PutMapping(value = "/edit/profile")
    public ResponseEntity<?> editProfile(@RequestBody Map<String  , String > fullName){
        return userService.editProfile(fullName.get("fullName"));
    }

    @PutMapping(value = "/change/password")
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordRequest request){
        return userService.changePassword(request);
    }

    @PostMapping(value = "/add/book")
    public ResponseEntity<?> addBook(@RequestBody BookRequest request){
        return bookService.addBook(request);
    }

    @GetMapping(value = "/books/list")
    public ResponseEntity<?> booksList(){
        return bookService.booksList();
    }

    @PutMapping(value = "/edit/book")
    public ResponseEntity<?> editBook(@RequestBody EditBookRequest request){
        return bookService.editBook(request);
    }

    @DeleteMapping(value = "/delete/book/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable Long id){
        return userService.deleteOperator(id);
    }
}
