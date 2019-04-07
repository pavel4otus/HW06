package ru.pavel2107.otus.hw06.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.pavel2107.otus.hw06.domain.Author;
import ru.pavel2107.otus.hw06.repository.AuthorRepository;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    private AuthorRepository repository;


    @Autowired
    public AuthorServiceImpl( AuthorRepository repository){
        this.repository = repository;
    }

    @Override
    public Author save(Author author) {
        return repository.save( author);
    }

    @Override
    public boolean delete(Long ID) {
        return repository.delete( ID);
    }

    @Override
    public Author get(Long ID) {
        return repository.get( ID);
    }

    @Override
    public List<Author> getByName(String name) {
        return repository.getByName( name);
    }

    @Override
    public List<Author> getAll() {
        return repository.getAll();
    }
}
