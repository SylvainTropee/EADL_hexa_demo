package eni.ecole.eadl_hexa_demo.adapter.in.web;

import eni.ecole.eadl_hexa_demo.core.application.dto.BorrowBookCommand;
import eni.ecole.eadl_hexa_demo.core.application.dto.CreateBookCommand;
import eni.ecole.eadl_hexa_demo.core.application.dto.ReturnBookCommand;
import eni.ecole.eadl_hexa_demo.core.application.usecase.BorrowBookUseCase;
import eni.ecole.eadl_hexa_demo.core.application.usecase.CreateBookUseCase;
import eni.ecole.eadl_hexa_demo.core.application.usecase.GetBookUseCase;
import eni.ecole.eadl_hexa_demo.core.application.usecase.ReturnBookUseCase;
import eni.ecole.eadl_hexa_demo.core.domain.model.Book;
import eni.ecole.eadl_hexa_demo.adapter.in.web.dto.BookResponse;
import eni.ecole.eadl_hexa_demo.adapter.in.web.dto.BorrowBookRequest;
import eni.ecole.eadl_hexa_demo.adapter.in.web.dto.CreateBookRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final CreateBookUseCase createBookUseCase;
    private final BorrowBookUseCase borrowBookUseCase;
    private final ReturnBookUseCase returnBookUseCase;
    private final GetBookUseCase getBookUseCase;

    public BookController(CreateBookUseCase createBookUseCase,
                          BorrowBookUseCase borrowBookUseCase,
                          ReturnBookUseCase returnBookUseCase,
                          GetBookUseCase getBookUseCase) {
        this.createBookUseCase = createBookUseCase;
        this.borrowBookUseCase = borrowBookUseCase;
        this.returnBookUseCase = returnBookUseCase;
        this.getBookUseCase = getBookUseCase;
    }

    @PostMapping
    public ResponseEntity<BookResponse> createBook(@Valid @RequestBody CreateBookRequest request) {
        Book book = createBookUseCase.execute(new CreateBookCommand(request.title()));
        return ResponseEntity.status(HttpStatus.CREATED).body(BookResponse.fromDomain(book));
    }

    @GetMapping
    public ResponseEntity<List<BookResponse>> getAllBooks() {
        List<BookResponse> books = getBookUseCase.findAll()
                .stream()
                .map(BookResponse::fromDomain)
                .toList();
        return ResponseEntity.ok(books);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponse> getBookById(@PathVariable Long id) {
        Book book = getBookUseCase.findById(id);
        return ResponseEntity.ok(BookResponse.fromDomain(book));
    }

    @PostMapping("/{id}/borrow")
    public ResponseEntity<BookResponse> borrowBook(@PathVariable Long id,
                                                    @Valid @RequestBody BorrowBookRequest request) {
        Book book = borrowBookUseCase.execute(new BorrowBookCommand(id, request.email()));
        return ResponseEntity.ok(BookResponse.fromDomain(book));
    }

    @PostMapping("/{id}/return")
    public ResponseEntity<BookResponse> returnBook(@PathVariable Long id) {
        Book book = returnBookUseCase.execute(new ReturnBookCommand(id));
        return ResponseEntity.ok(BookResponse.fromDomain(book));
    }

}
