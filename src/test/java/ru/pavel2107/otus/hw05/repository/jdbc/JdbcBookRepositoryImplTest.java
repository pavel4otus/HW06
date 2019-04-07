package ru.pavel2107.otus.hw05.repository.jdbc;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import ru.pavel2107.otus.hw05.domain.Author;
import ru.pavel2107.otus.hw05.domain.Book;
import ru.pavel2107.otus.hw05.domain.Genre;
import ru.pavel2107.otus.hw05.repository.AuthorRepository;
import ru.pavel2107.otus.hw05.repository.BookRepository;
import ru.pavel2107.otus.hw05.repository.GenreRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(properties = {
        InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"
})
@DisplayName( "Репозиторий книг")
@Profile( "jdbc")
class JdbcBookRepositoryImplTest {

    @Autowired
    BookRepository repository;

    @Autowired
    GenreRepository genreRepository;

    @Autowired
    AuthorRepository authorRepository;


    @Test
    void save() {

        Genre  genre  = genreRepository.get(1L);
        Author author = authorRepository.get(1L);

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
        Genre  genre  = genreRepository.get(1L);
        Author author = authorRepository.get(1L);

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