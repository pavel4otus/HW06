package ru.pavel2107.otus.hw05.shell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.pavel2107.otus.hw05.domain.Genre;
import ru.pavel2107.otus.hw05.service.GenreService;

import java.util.List;

@ShellComponent
public class GenreProcessor {

    private GenreService service;

    @Autowired
    public GenreProcessor(GenreService service){
        this.service = service;
    }

    @ShellMethod( value = "add genre", key = "genre add")
    public String addGenre(
            @ShellOption String name){

        Genre genre = new Genre();
        genre.setName( name);
        genre = service.save( genre);
        return  "created genre " + name + " with (id)=" + genre.getID();
    }

    @ShellMethod( value = "list genries", key = "genre list")
    public void listGenre(){

        for( Genre g: service.getAll()){
            System.out.println( g.toString());
        }
    }

    @ShellMethod( value ="delete genre", key = "genre delete")
    public String deleteGenre( @ShellOption Long genreID){
        return service.delete( genreID) ?
                "deleted genre id # " + genreID : "genre id # " + genreID + " not deleted";

    }

}


