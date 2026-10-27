package eni.ecole.eadl_hexa_demo.adapter.out.persistence.book;

import eni.ecole.eadl_hexa_demo.core.domain.model.Book;
import eni.ecole.eadl_hexa_demo.port.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Repository
public class SqlBookRepository implements BookRepository {

    private final JpaBookRepository jpa;

    @Override
    public Book save(Book book) {
        BookEntity entity = BookMapper.toEntity(book);
        BookEntity saved = jpa.save(entity);
        return BookMapper.toDomain(saved);
    }

    @Override
    public Optional<Book> findById(Long id) {
        return jpa.findById(id)
                .map(BookMapper::toDomain);
    }

    @Override
    public List<Book> findAll() {
        return jpa.findAll()
                .stream()
                .map(BookMapper::toDomain)
                .toList();
    }
}