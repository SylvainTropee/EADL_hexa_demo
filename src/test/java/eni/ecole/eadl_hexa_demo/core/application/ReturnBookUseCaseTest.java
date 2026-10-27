package eni.ecole.eadl_hexa_demo.core.application;

import eni.ecole.eadl_hexa_demo.core.application.dto.ReturnBookCommand;
import eni.ecole.eadl_hexa_demo.core.application.usecase.ReturnBookUseCase;
import eni.ecole.eadl_hexa_demo.core.domain.model.Book;
import eni.ecole.eadl_hexa_demo.port.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ReturnBookUseCaseTest {

    private BookRepository fakeBookRepository;
    private ReturnBookUseCase returnBookUseCase;

    @BeforeEach
    void setUp() {
        fakeBookRepository = new InMemoryBookRepository();
        returnBookUseCase = new ReturnBookUseCase(fakeBookRepository);
    }

    @Test
    @DisplayName("Cas d'usage : rendre un livre emprunté")
    void shouldReturnBookSuccessfully() {
        Book book = new Book(1L, "Clean Architecture", false);
        fakeBookRepository.save(book);

        ReturnBookCommand command = new ReturnBookCommand(1L);
        Book returnedBook = returnBookUseCase.execute(command);

        assertTrue(returnedBook.isAvailable());
    }

    @Test
    @DisplayName("Cas d'usage : exception si le livre est déjà disponible")
    void shouldThrowWhenReturningAvailableBook() {
        Book book = new Book(1L, "Clean Architecture", true);
        fakeBookRepository.save(book);

        ReturnBookCommand command = new ReturnBookCommand(1L);
        assertThrows(IllegalStateException.class, () -> returnBookUseCase.execute(command));
    }

    private static class InMemoryBookRepository implements BookRepository {
        private final Map<Long, Book> storage = new HashMap<>();

        @Override
        public Optional<Book> findById(Long id) {
            return Optional.ofNullable(storage.get(id));
        }

        @Override
        public Book save(Book book) {
            if (book.getId() == null) {
                book.setId((long) (storage.size() + 1));
            }
            storage.put(book.getId(), book);
            return book;
        }

        @Override
        public List<Book> findAll() {
            return List.copyOf(storage.values());
        }
    }
}
