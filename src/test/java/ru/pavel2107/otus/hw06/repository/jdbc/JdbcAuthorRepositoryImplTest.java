package ru.pavel2107.otus.hw06.repository.jdbc;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import ru.pavel2107.otus.hw06.domain.Author;
import ru.pavel2107.otus.hw06.repository.AuthorRepository;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@JdbcTest
@Import(JdbcAuthorRepositoryImpl.class)
@DisplayName( "Репозиторий авторов")
@ActiveProfiles( "jdbc")
class JdbcAuthorRepositoryImplTest {

    @Autowired
    AuthorRepository repository;

    @Test
    void save() {
        Author author = new Author();
        author.setBirthDate(LocalDate.now());
        author.setName( "test");
        author.setPhone( "123");
        author.setEmail("a@test.ru");
        author.setAddress( "qq");
        author = repository.save( author);
        assertEquals( "test", author.getName());
    }


    @Test
    void get() {
        Author author = repository.get(1L);
        assertEquals( 1L, author.getID().longValue());
    }

     @Test
    void getAll() {
        List<Author> list = repository.getAll();
        assertNotEquals( 0L, list.size());
    }
}