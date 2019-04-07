package ru.pavel2107.otus.hw06.repository.jpa;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import ru.pavel2107.otus.hw06.domain.Book;
import ru.pavel2107.otus.hw06.domain.Comment;
import ru.pavel2107.otus.hw06.repository.CommentRepository;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@EnableAutoConfiguration
@ContextConfiguration( classes = { jpaCommentRepositoryImpl.class, jpaBookRepositoryImpl.class})
@EntityScan( basePackages = { "ru.pavel2107.otus.hw06.domain"})
@DataJpaTest
@DisplayName( "Репозиторий комментариев")
@ActiveProfiles( "jpa")
class jpaCommentRepositoryImplTest {

    @Autowired
    CommentRepository repository;


    @Test
    void save() {
        Comment comment = new Comment();
        comment.setName( "test");
        comment.setDateTime( LocalDateTime.now());
        comment.setComment( "t");

        Book b = new Book();
        b.setID( 1L);

        comment.setBook( b);
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