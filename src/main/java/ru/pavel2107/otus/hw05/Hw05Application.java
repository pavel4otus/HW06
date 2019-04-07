package ru.pavel2107.otus.hw05;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.pavel2107.otus.hw05.domain.Author;
import ru.pavel2107.otus.hw05.domain.Genre;
import ru.pavel2107.otus.hw05.repository.AuthorRepository;
import ru.pavel2107.otus.hw05.repository.GenreRepository;
import ru.pavel2107.otus.hw05.repository.jdbc.JdbcAuthorRepositoryImpl;
import ru.pavel2107.otus.hw05.repository.jdbc.JdbcGenreRepositoryImpl;

import org.h2.tools.Console;


import java.time.LocalDate;

@SpringBootApplication
public class Hw05Application {


    public static void main(String[] args)  throws Exception {
        ApplicationContext context = SpringApplication.run(Hw05Application.class, args);

//        AuthorRepository repository = context.getBean(JdbcAuthorRepositoryImpl.class);

/*
        Author author = new Author( null, "pavel", LocalDate.of( 1990, 12, 9), "test@mail.ru", "77727272", "no info");
        Author savedAuthor  = repository.save( author);
        System.out.println( savedAuthor.getName());
*/

//        GenreRepository genreRepository = context.getBean(JdbcGenreRepositoryImpl.class);


        Console.main( args);
//        Genre genre = new Genre();
//        genre.setName( "fantasy");

//        Genre saved = genreRepository.save( genre);
//        System.out.println( saved.getID());



    }

}
