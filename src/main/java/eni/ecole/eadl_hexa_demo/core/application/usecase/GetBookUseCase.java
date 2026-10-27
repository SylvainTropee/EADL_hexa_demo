package eni.ecole.eadl_hexa_demo.core.application.usecase;

import eni.ecole.eadl_hexa_demo.core.domain.exception.BookNotFoundException;
import eni.ecole.eadl_hexa_demo.core.domain.model.Book;
import eni.ecole.eadl_hexa_demo.port.BookRepository;

import java.util.List;

public class GetBookUseCase {

    private final BookRepository repository;

    public GetBookUseCase(BookRepository repository) {
        this.repository = repository;
    }

    public Book findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Livre introuvable avec l'ID : " + id));
    }

    public List<Book> findAll() {
        return repository.findAll();
    }
}
