package ru.pavel2107.otus.hw05.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table( name ="genre")
public class Genre extends AbstractNamedEntity{

    @OneToMany( mappedBy = "genre", fetch = FetchType.LAZY)
    private List<Book> books;

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "Genre{id=" + getID() + ", name='" + getName() + "'}";
    }
}
