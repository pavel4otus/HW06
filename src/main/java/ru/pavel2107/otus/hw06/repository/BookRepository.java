package ru.pavel2107.otus.hw06.repository;

import ru.pavel2107.otus.hw06.domain.Book;

import java.util.List;

public interface BookRepository {
    Book save( Book book);
    boolean delete( Long ID);
    Book get( Long ID);
    Book getByISBN( String ISBN);
    List<Book> getByName(String name);
    List<Book> getByAuthorID(Long AuthorID);

    List<Book> getAll();

}
