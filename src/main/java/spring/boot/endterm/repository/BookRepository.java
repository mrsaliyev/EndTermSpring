package spring.boot.endterm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.boot.endterm.entity.Book;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    boolean existsByName(String name);
    Optional<Book> findById(Long id);
}
