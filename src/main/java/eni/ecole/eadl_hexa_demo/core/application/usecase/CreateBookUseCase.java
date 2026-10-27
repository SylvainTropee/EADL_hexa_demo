package eni.ecole.eadl_hexa_demo.core.application.usecase;

import eni.ecole.eadl_hexa_demo.core.application.dto.CreateBookCommand;
import eni.ecole.eadl_hexa_demo.core.domain.model.Book;
import eni.ecole.eadl_hexa_demo.port.BookRepository;

public class CreateBookUseCase {

    private final BookRepository repository;

    public CreateBookUseCase(BookRepository repository) {
        this.repository = repository;
    }

    public Book execute(CreateBookCommand command) {
        Book book = new Book(command.title());
        return repository.save(book);
    }
}
