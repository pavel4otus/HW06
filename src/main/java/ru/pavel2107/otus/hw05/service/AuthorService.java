package ru.pavel2107.otus.hw05.service;

import ru.pavel2107.otus.hw05.domain.Author;

import java.util.List;

public interface AuthorService {
    Author save(Author author);
    boolean delete( Long ID);
    Author get( Long ID);
    List<Author> getByName(String name);
    List<Author> getAll();
}
