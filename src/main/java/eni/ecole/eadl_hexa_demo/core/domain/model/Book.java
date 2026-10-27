package eni.ecole.eadl_hexa_demo.core.domain.model;

import eni.ecole.eadl_hexa_demo.core.domain.exception.BookUnavailableException;

public class Book {

    private Long id;
    private String title;
    private boolean available;

    public Book(Long id, String title, boolean available) {
        this.id = id;
        this.title = title;
        this.available = available;
    }

    public Book(String title) {
        this(null, title, true);
    }

    public void borrow() {
        if (!available) {
            throw new BookUnavailableException("Le livre '" + title + "' (ID: " + id + ") n'est pas disponible pour l'emprunt.");
        }
        this.available = false;
    }

    public void returnBook() {
        if (this.available) {
            throw new IllegalStateException("Le livre '" + title + "' (ID: " + id + ") est déjà disponible en rayon.");
        }
        this.available = true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
