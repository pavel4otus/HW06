package ru.pavel2107.otus.hw05.service;

import ru.pavel2107.otus.hw05.domain.Book;

import java.util.List;

public interface BookService {
    Book save(Book author);
    boolean delete( Long ID);
    Book get( Long ID);

    List<Book> getByName(String name);
    List<Book> getByAuthorID( Long authorID);
    Book getByISBN( String ISBN);
    List<Book> getAll();


}


