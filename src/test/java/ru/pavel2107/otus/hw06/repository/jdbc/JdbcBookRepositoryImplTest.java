package ru.pavel2107.otus.hw06.repository.jdbc;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import ru.pavel2107.otus.hw06.domain.Author;
import ru.pavel2107.otus.hw06.domain.Book;
import ru.pavel2107.otus.hw06.domain.Genre;
import ru.pavel2107.otus.hw06.repository.BookRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@JdbcTest
@Import({ JdbcBookRepositoryImpl.class, JdbcGenreRepositoryImpl.class, JdbcAuthorRepositoryImpl.class})
@DisplayName( "Репозиторий книг")
@ActiveProfiles( "jdbc")
class JdbcBookRepositoryImplTest {

    @Autowired
    BookRepository repository;

    @Test
    void save() {

        Genre  genre  = new Genre();
        genre.setID( 1L);

        Author author = new Author();
        author.setID( 1L);

        Book book = new Book();
        book.setName( "test");
        book.setIsbn( "123");
        book.setPublicationPlace( "place");
        book.setPublishingHouse( "hos");
        book.setPublicationYear(1200);
        book.setGenre( genre);
        book.setAuthor( author);
        book = repository.save( book);
        assertEquals( "test", book.getName());
    }

    @Test
    void delete() {
        int oldSize = repository.getAll().size();
        Genre  genre  = new Genre();
        genre.setID( 1L);

        Author author = new Author();
        author.setID( 1L);

        Book book = new Book();
        book.setName( "test");
        book.setIsbn( "123");
        book.setPublicationPlace( "place");
        book.setPublishingHouse( "hos");
        book.setPublicationYear(1200);
        book.setGenre( genre);
        book.setAuthor( author);
        book = repository.save( book);

        repository.delete( book.getID());

        int newSize = repository.getAll().size();
        assertEquals( oldSize, newSize);

    }

    @Test
    void get() {
        Book book = repository.get( 1L);
        assertEquals( 1L, book.getID().longValue());
    }

    @Test
    void getByISBN() {
        String isbn = "1111111";
        Book book = repository.getByISBN( isbn);
        assertEquals( isbn, book.getIsbn());
    }

    @Test
    void getByName() {
        String name = "book 1";
        List<Book> books = repository.getByName( name);
        Book book = books.get(0);
        assertEquals( name, book.getName());
    }

    @Test
    void getByAuthorID() {
        List<Book> books = repository.getByAuthorID(1L);
        for( Book b: books){
            assertEquals( 1L, b.getAuthor().getID().longValue());
        }
    }

    @Test
    void getAll() {
        List<Book> list = repository.getAll();
        assertNotEquals( 0, list.size());
    }
}