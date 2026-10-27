package eni.ecole.eadl_hexa_demo.adapter.out.persistence.book;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaBookRepository
        extends JpaRepository<BookEntity, Long> {
}
