package ru.pavel2107.otus.hw06.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.pavel2107.otus.hw06.domain.Genre;
import ru.pavel2107.otus.hw06.repository.GenreRepository;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {


    private GenreRepository repository;

    @Autowired
    public GenreServiceImpl(GenreRepository repository){
        this.repository = repository;
    }

    @Override
    public Genre save(Genre genre) {
        return repository.save( genre);
    }

    @Override
    public boolean delete(Long ID) {
        return repository.delete( ID);
    }

    @Override
    public Genre get(Long ID) {
        return repository.get( ID);
    }

    @Override
    public List<Genre> getAll() {
        return repository.getAll();
    }
}
