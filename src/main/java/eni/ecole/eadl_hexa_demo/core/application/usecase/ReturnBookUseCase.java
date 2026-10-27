package eni.ecole.eadl_hexa_demo.core.application.usecase;

import eni.ecole.eadl_hexa_demo.core.application.dto.ReturnBookCommand;
import eni.ecole.eadl_hexa_demo.core.domain.exception.BookNotFoundException;
import eni.ecole.eadl_hexa_demo.core.domain.model.Book;
import eni.ecole.eadl_hexa_demo.port.BookRepository;

public class ReturnBookUseCase {

    private final BookRepository repository;

    public ReturnBookUseCase(BookRepository repository) {
        this.repository = repository;
    }

    public Book execute(ReturnBookCommand command) {
        Book book = repository.findById(command.bookId())
                .orElseThrow(() -> new BookNotFoundException("Livre introuvable avec l'ID : " + command.bookId()));

        book.returnBook();

        return repository.save(book);
    }
}
