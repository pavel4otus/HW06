package ru.pavel2107.otus.hw05.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.pavel2107.otus.hw05.domain.Author;
import ru.pavel2107.otus.hw05.domain.Book;
import ru.pavel2107.otus.hw05.repository.BookRepository;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;


    @Autowired
    public BookServiceImpl( BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    @Override
    public Book getByISBN(String ISBN) {
        return bookRepository.getByISBN( ISBN);
    }

    @Override
    public Book save(Book book) {
        return bookRepository.save( book);
    }

    @Override
    public boolean delete(Long ID) {
        return bookRepository.delete( ID);
    }

    @Override
    public Book get(Long ID) {
        return bookRepository.get( ID);
    }

    @Override
    public List<Book> getByName(String name) {
        return bookRepository.getByName( name);
    }

    @Override
    public List<Book> getByAuthorID(Long authorID) {
        return bookRepository.getByAuthorID( authorID);
    }

    @Override
    public List<Book> getAll() {
        return bookRepository.getAll();
    }
}
