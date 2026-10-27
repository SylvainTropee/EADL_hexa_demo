package eni.ecole.eadl_hexa_demo.config;

import eni.ecole.eadl_hexa_demo.core.application.usecase.BorrowBookUseCase;
import eni.ecole.eadl_hexa_demo.core.application.usecase.CreateBookUseCase;
import eni.ecole.eadl_hexa_demo.core.application.usecase.GetBookUseCase;
import eni.ecole.eadl_hexa_demo.core.application.usecase.ReturnBookUseCase;
import eni.ecole.eadl_hexa_demo.port.BookRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public BorrowBookUseCase borrowBookUseCase(BookRepository bookRepository) {
        return new BorrowBookUseCase(bookRepository);
    }

    @Bean
    public ReturnBookUseCase returnBookUseCase(BookRepository bookRepository) {
        return new ReturnBookUseCase(bookRepository);
    }

    @Bean
    public CreateBookUseCase createBookUseCase(BookRepository bookRepository) {
        return new CreateBookUseCase(bookRepository);
    }

    @Bean
    public GetBookUseCase getBookUseCase(BookRepository bookRepository) {
        return new GetBookUseCase(bookRepository);
    }

}
