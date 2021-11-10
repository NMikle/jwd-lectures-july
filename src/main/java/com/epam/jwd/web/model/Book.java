package com.epam.jwd.web.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public final class Book {

    private final Long id;
    private final String title;
    private final String description;
    private final Author author;
    private final LocalDate publishYear;
    private final BigDecimal price;

    private Book(Long id, String title,
                 String description, Author author,
                 LocalDate publishYear, BigDecimal price) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.author = author;
        this.publishYear = publishYear;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Author getAuthor() {
        return author;
    }

    public LocalDate getPublishYear() {
        return publishYear;
    }

    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(id, book.id)
                && Objects.equals(title, book.title)
                && Objects.equals(description, book.description)
                && Objects.equals(author, book.author)
                && Objects.equals(publishYear, book.publishYear)
                && Objects.equals(price, book.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                id, title,
                description, author,
                publishYear, price
        );
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", author=" + author +
                ", publishYear=" + publishYear +
                ", price=" + price +
                '}';
    }

    public static Builder with() {
        return new Builder();
    }

    public static final class Builder {

        private Long id;
        private String title;
        private String description;
        private Author author;
        private LocalDate publishYear;
        private BigDecimal price;

        private Builder() {
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder author(Author author) {
            this.author = author;
            return this;
        }

        public Builder publishYear(LocalDate publishYear) {
            this.publishYear = publishYear;
            return this;
        }

        public Builder price(BigDecimal price) {
            this.price = price;
            return this;
        }

        public Book create() {
            return new Book(
                    id, title,
                    description, author,
                    publishYear, price
            );
        }

    }
}
