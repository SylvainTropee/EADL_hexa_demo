package eni.ecole.eadl_hexa_demo.port;

import eni.ecole.eadl_hexa_demo.core.domain.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository {

    Optional<Book> findById(Long id);

    Book save(Book book);

    List<Book> findAll();
}
