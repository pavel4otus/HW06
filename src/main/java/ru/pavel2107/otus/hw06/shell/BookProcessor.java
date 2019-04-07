package ru.pavel2107.otus.hw06.shell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.pavel2107.otus.hw06.domain.Book;
import ru.pavel2107.otus.hw06.service.AuthorService;
import ru.pavel2107.otus.hw06.service.BookService;
import ru.pavel2107.otus.hw06.service.GenreService;

import java.util.List;

@ShellComponent
public class BookProcessor {

    private BookService bookService;
    private AuthorService authorService;
    private GenreService genreService;

    @Autowired
    public BookProcessor(BookService bookService, AuthorService authorService, GenreService genreService){
        this.bookService   = bookService;
        this.authorService = authorService;
        this.genreService  = genreService;
    }


    @ShellMethod( value ="add book", key="book add")
    public String addBook(
            @ShellOption String ISBN, @ShellOption String name, @ShellOption Long authorID,
            @ShellOption Integer pubYear, @ShellOption String pubPlace, @ShellOption String pubHouse,
            @ShellOption Long GenreID){

        Book book = new Book();
        book.setIsbn( ISBN);
        book.setName( name);
        book.setPublicationPlace( pubPlace);
        book.setPublicationYear( pubYear);
        book.setAuthor( authorService.get( authorID));
        book.setGenre(  genreService.get( GenreID));

        bookService.save( book);
        return "You added new book " + book.toString();
    }


    @ShellMethod( value = "show all books", key ="book list")
    public void listBooks(){
         for( Book book : bookService.getAll()){
             System.out.println( book.toString());
         }
    }

   @ShellMethod( value = "find book by id", key ="book find by id")
   public String find(@ShellOption Long bookID){
        Book book = bookService.get( bookID);
        return book == null ? "Book id # " + bookID + " not found" : book.toString();
   }

    @ShellMethod( value = "find book by isbn", key ="book find by isbn")
    public String findByISBN(@ShellOption String ISBN){
        Book book = bookService.getByISBN( ISBN);
        return book == null ? "Book isbn # " + ISBN+ " not found" : book.toString();
    }

    @ShellMethod( value = "find book by authorid", key ="book find by authorid")
    public String findByAuthorID(@ShellOption Long AuthorID){
        List<Book> books = bookService.getByAuthorID( AuthorID);
        String result = "";
        if( books == null || books.size() == 0){
            result = "No books for this author";
        } else{
            for ( Book book : books){
                result = result + book.toString() + "\n";
            }
        }

        return result;
    }


}


