package eni.ecole.eadl_hexa_demo.core.application;

import eni.ecole.eadl_hexa_demo.core.application.dto.BorrowBookCommand;
import eni.ecole.eadl_hexa_demo.core.application.usecase.BorrowBookUseCase;
import eni.ecole.eadl_hexa_demo.core.domain.exception.BookNotFoundException;
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

class BorrowBookUseCaseTest {

    private BookRepository fakeBookRepository;
    private BorrowBookUseCase borrowBookUseCase;

    @BeforeEach
    void setUp() {
        fakeBookRepository = new InMemoryBookRepository();
        borrowBookUseCase = new BorrowBookUseCase(fakeBookRepository);
    }

    @Test
    @DisplayName("Cas d'usage : emprunter un livre existant et disponible")
    void shouldBorrowBookSuccessfully() {
        Book book = new Book(100L, "Architecture Hexagonale", true);
        fakeBookRepository.save(book);

        BorrowBookCommand command = new BorrowBookCommand(100L, "user@eni.fr");
        Book borrowedBook = borrowBookUseCase.execute(command);

        assertFalse(borrowedBook.isAvailable());
    }

    @Test
    @DisplayName("Cas d'usage : erreur si le livre n'existe pas")
    void shouldThrowWhenBookNotFound() {
        BorrowBookCommand command = new BorrowBookCommand(999L, "user@eni.fr");

        assertThrows(BookNotFoundException.class, () -> borrowBookUseCase.execute(command));
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
