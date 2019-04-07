package ru.pavel2107.otus.hw06.repository.jdbc;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import ru.pavel2107.otus.hw06.domain.Genre;
import ru.pavel2107.otus.hw06.repository.GenreRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@JdbcTest
@Import(JdbcGenreRepositoryImpl.class)
@DisplayName( "Репозиторий жанров")
@ActiveProfiles( "jdbc")
class JdbcGenreRepositoryImplTest {

    @Autowired
    GenreRepository repository;

    @Test
    void save() {
        Genre genre = new Genre();
        genre.setName( "test");
        genre = repository.save( genre);
        assertEquals( "test", genre.getName());
    }

    @Test
    void delete() {
        int size = repository.getAll().size();

        Genre genre = new Genre();
        genre.setName( "test");
        genre = repository.save( genre);

        repository.delete( genre.getID());
        int newSize = repository.getAll().size();
        assertEquals( size, newSize);

    }

    @Test
    void get() {
        Genre  genre = repository.get(1L);
        assertEquals( 1L, genre.getID().longValue());
    }

    @Test
    void getAll() {
        List<Genre> list = repository.getAll();
        assertNotEquals( 0, list.size());
    }
}