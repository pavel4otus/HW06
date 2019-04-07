package ru.pavel2107.otus.hw05.repository.jpa;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import org.springframework.test.context.ActiveProfiles;
import ru.pavel2107.otus.hw05.domain.Comment;
import ru.pavel2107.otus.hw05.repository.BookRepository;
import ru.pavel2107.otus.hw05.repository.CommentRepository;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(properties = {
        InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"
})
@DisplayName( "Репозиторий комментариев")
@ActiveProfiles( "jpa")
class jpaCommentRepositoryImplTest {

    @Autowired
    CommentRepository repository;

    @Autowired
    BookRepository bookRepository;

    @Test
    void save() {
        Comment comment = new Comment();
        comment.setName( "test");
        comment.setDateTime( LocalDateTime.now());
        comment.setComment( "t");
        comment.setBook( bookRepository.get( 1L));
        comment = repository.save( comment);
        assertEquals( "test", comment.getName());
    }

    @Test
    void get() {
        Comment comment = repository.get( 1L);
        assertEquals( 1, comment.getID().longValue());
    }

    @Test
    void getAll() {
       int size = repository.getAll(1L).size();
       assertEquals( 1, size);
    }
}