package ru.pavel2107.otus.hw05.repository.jdbc;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import ru.pavel2107.otus.hw05.domain.Author;
import ru.pavel2107.otus.hw05.repository.AuthorRepository;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(properties = {
        InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"
})
@DisplayName( "Репозиторий авторов")
@Profile( "jdbc")
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
    void delete() {
    }

    @Test
    void get() {
        Author author = repository.get(1L);
        assertEquals( 1L, author.getID().longValue());
    }

    @Test
    void getByName() {
    }

    @Test
    void getAll() {
        List<Author> list = repository.getAll();
        assertNotEquals( 0L, list.size());
    }
}