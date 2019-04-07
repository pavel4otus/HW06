package ru.pavel2107.otus.hw05.repository;

import ru.pavel2107.otus.hw05.domain.Author;

import java.util.List;

public interface AuthorRepository {
    Author save( Author author);
    boolean delete( Long ID);
    Author get( Long ID);
    List<Author> getByName(String name);
    List<Author> getAll();
}
