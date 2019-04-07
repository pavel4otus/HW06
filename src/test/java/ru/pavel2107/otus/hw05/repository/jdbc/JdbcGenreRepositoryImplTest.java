package ru.pavel2107.otus.hw05.repository.jdbc;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import org.springframework.test.context.ActiveProfiles;
import ru.pavel2107.otus.hw05.domain.Genre;
import ru.pavel2107.otus.hw05.repository.GenreRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(properties = {
        InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"
})
@DisplayName( "Репозиторий жанров")
@ActiveProfiles( "jpa")
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