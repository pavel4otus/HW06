package ru.pavel2107.otus.hw06.service;

import ru.pavel2107.otus.hw06.domain.Genre;

import java.util.List;

public interface GenreService {

    Genre save( Genre genre);
    boolean delete( Long ID);
    Genre get(Long ID);
    List<Genre> getAll();

}
