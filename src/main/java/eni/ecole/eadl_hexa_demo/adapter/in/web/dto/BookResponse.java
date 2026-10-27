package eni.ecole.eadl_hexa_demo.adapter.in.web.dto;

import eni.ecole.eadl_hexa_demo.core.domain.model.Book;

public record BookResponse(
        Long id,
        String title,
        boolean available
) {
    public static BookResponse fromDomain(Book book) {
        return new BookResponse(book.getId(), book.getTitle(), book.isAvailable());
    }
}
