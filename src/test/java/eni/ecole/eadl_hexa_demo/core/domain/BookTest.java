package eni.ecole.eadl_hexa_demo.core.domain;

import eni.ecole.eadl_hexa_demo.core.domain.exception.BookUnavailableException;
import eni.ecole.eadl_hexa_demo.core.domain.model.Book;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookTest {

    @Test
    @DisplayName("Un livre disponible peut être emprunté")
    void shouldBorrowAvailableBook() {
        Book book = new Book(1L, "Clean Code", true);

        book.borrow();

        assertFalse(book.isAvailable());
    }

    @Test
    @DisplayName("Emprunter un livre indisponible lève une exception domaine")
    void shouldThrowExceptionWhenBorrowingUnavailableBook() {
        Book book = new Book(1L, "Domain-Driven Design", false);

        BookUnavailableException exception = assertThrows(
                BookUnavailableException.class,
                book::borrow
        );

        assertTrue(exception.getMessage().contains("n'est pas disponible"));
    }

}
