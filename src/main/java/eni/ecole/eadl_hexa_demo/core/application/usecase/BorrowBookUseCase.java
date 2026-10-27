package eni.ecole.eadl_hexa_demo.core.application.usecase;

import eni.ecole.eadl_hexa_demo.core.application.dto.BorrowBookCommand;
import eni.ecole.eadl_hexa_demo.core.domain.exception.BookNotFoundException;
import eni.ecole.eadl_hexa_demo.core.domain.model.Book;
import eni.ecole.eadl_hexa_demo.port.BookRepository;

public class BorrowBookUseCase {

    private final BookRepository repository;

    public BorrowBookUseCase(BookRepository repository) {
        this.repository = repository;
    }

    public Book execute(BorrowBookCommand command) {
        Book book = repository.findById(command.bookId())
                .orElseThrow(() -> new BookNotFoundException("Livre introuvable avec l'ID : " + command.bookId()));

        book.borrow();

        return repository.save(book);
    }
}
