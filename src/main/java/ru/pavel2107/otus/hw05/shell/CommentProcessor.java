package ru.pavel2107.otus.hw05.shell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.pavel2107.otus.hw05.domain.Book;
import ru.pavel2107.otus.hw05.domain.Comment;
import ru.pavel2107.otus.hw05.service.BookService;
import ru.pavel2107.otus.hw05.service.CommentService;

import java.time.LocalDateTime;
import java.util.List;

@ShellComponent
public class CommentProcessor {

    private CommentService service;

    private BookService bookService;

    @Autowired
    public CommentProcessor( CommentService service, BookService bookService){
        this.service = service;
        this.bookService = bookService;
    }

    @ShellMethod( value = "show comments for book", key = "comments list")
    public void showComments(@ShellOption Long BookID){
        for( Comment comment : service.getAll( BookID)){
            System.out.println( comment.toString());
        }
    }


    @ShellMethod( value = "add comment for book", key = "comments add")
    public void addComment( @ShellOption Long BookID, @ShellOption String userName, @ShellOption String comment ){
        Comment c = new Comment();
        Book book = bookService.get( BookID);
        c.setBook( book);
        c.setDateTime( LocalDateTime.now());
        c.setComment( comment);
        c.setName( userName);

        service.save( c);
    }


}


/*
    Comment save(Comment comment);

 */