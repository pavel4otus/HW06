package ru.pavel2107.otus.hw05.service;

import ru.pavel2107.otus.hw05.domain.Genre;

import java.util.List;

public interface GenreService {

    Genre save( Genre genre);
    boolean delete( Long ID);
    Genre get(Long ID);
    List<Genre> getAll();

}
