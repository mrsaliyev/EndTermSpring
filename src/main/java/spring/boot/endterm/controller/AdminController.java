package spring.boot.endterm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import spring.boot.endterm.dto.request.EditOperatorRequest;
import spring.boot.endterm.dto.request.OperatorRequest;
import spring.boot.endterm.service.book.BookService;
import spring.boot.endterm.service.user.UserService;

@RestController
@RequestMapping("/admin")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdminController {

    private final UserService userService;
    private final BookService bookService;

    @Autowired
    public AdminController(UserService userService, BookService bookService) {
        this.userService = userService;
        this.bookService = bookService;
    }

    @PutMapping(value = "/block/user/{id}")
    public ResponseEntity<?> blockUser(@PathVariable Long id){
        return userService.blockUser(id);
    }

    @GetMapping(value = "/books/list")
    public ResponseEntity<?> booksList(){
        return bookService.booksList();
    }

    @GetMapping(value = "/operators/list")
    public ResponseEntity<?> operatorsList(){
        return userService.libraryOperatorsList();
    }

    @PostMapping(value = "/add/operator")
    public ResponseEntity<?> addOperator(@RequestBody OperatorRequest request){
        return userService.addOperator(request);
    }

    @DeleteMapping(value = "/delete/operator/{id}")
    public ResponseEntity<?> deleteOperator(@PathVariable Long id){
        return userService.deleteOperator(id);
    }

    @PutMapping(value = "/edit/operator")
    public ResponseEntity<?> editOperator(@RequestBody EditOperatorRequest request){
        return userService.editOperator(request);
    }
}
