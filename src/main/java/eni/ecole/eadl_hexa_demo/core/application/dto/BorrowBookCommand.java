package eni.ecole.eadl_hexa_demo.core.application.dto;

// record = data class kotlin
public record BorrowBookCommand(
        Long bookId,
        String email
){

}