# Architecture hexagonale (Book only)

Ce projet suit une organisation explicite inspirée de l'article : **core / port / adapter**.

## Structure

```text
src/main/java/eni/ecole/eadl_hexa_demo/
├── core/
│   ├── domain/
│   │   ├── model/Book.java
│   │   ├── model/Author.java
│   │   └── exception/
│   └── application/
│       ├── dto/
│       └── usecase/
├── port/
│   └── BookRepository.java
├── adapter/
│   ├── in/web/
│   │   ├── BookController.java
│   │   ├── GlobalExceptionHandler.java
│   │   └── dto/
│   └── out/persistence/book/
│       ├── BookEntity.java
│       ├── JpaBookRepository.java
│       ├── BookMapper.java
│       └── SqlBookRepository.java
└── config/
    └── BeanConfiguration.java
```

## Règles appliquées

1. **Core** ne dépend pas de Spring/JPA.
2. **Port** définit le contrat (`BookRepository`).
3. **Adapter** implémente le port et expose les entrées REST.

## Note

La partie fonctionnelle **Author** a été retirée volontairement (use-cases, ports, adaptateurs, tests).  
Seul le modèle `Author` est conservé dans `core.domain.model` comme demandé.
