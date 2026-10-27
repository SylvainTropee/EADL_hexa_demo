package eni.ecole.eadl_hexa_demo.core.domain.exception;

public class BookUnavailableException extends RuntimeException {
    public BookUnavailableException(String message) {
        super(message);
    }
}
