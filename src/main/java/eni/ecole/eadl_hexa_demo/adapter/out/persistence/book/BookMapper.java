package eni.ecole.eadl_hexa_demo.adapter.out.persistence.book;

import eni.ecole.eadl_hexa_demo.core.domain.model.Book;

public class BookMapper {

    public static Book toDomain(BookEntity entity) {

        return new Book(
                entity.getId(),
                entity.getTitle(),
                entity.isAvailable()
        );
    }

    public static BookEntity toEntity(Book book) {

        BookEntity entity = new BookEntity();

        entity.setId(book.getId());
        entity.setTitle(book.getTitle());
        entity.setAvailable(book.isAvailable());

        return entity;
    }
}