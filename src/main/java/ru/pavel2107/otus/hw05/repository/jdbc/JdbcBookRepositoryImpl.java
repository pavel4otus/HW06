package ru.pavel2107.otus.hw05.repository.jdbc;

import org.h2.result.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.pavel2107.otus.hw05.domain.Author;
import ru.pavel2107.otus.hw05.domain.Book;
import ru.pavel2107.otus.hw05.domain.Genre;
import ru.pavel2107.otus.hw05.repository.AuthorRepository;
import ru.pavel2107.otus.hw05.repository.BookRepository;
import ru.pavel2107.otus.hw05.repository.GenreRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@SuppressWarnings({"SqlNoDataSourceInspection", "ConstantConditions", "SqlDialectInspection"})
@Profile( "jdbc")
public class JdbcBookRepositoryImpl implements BookRepository {

    private final String INSERT_QUERY = "INSERT INTO books( NAME, ISBN, PUB_PLACE, PUB_YEAR, PUB_HOUSE, GENRE_ID, author_id) VALUES( :name, :isbn, :pub_place, :pub_year, :pub_house, :genre_id, :author_id)";
    private final String UPDATE_QUERY = "UPDATE books SET  name=:name, isbn=:isbn, pub_place=:pub_place, pub_year=:pub_year, pub_house=:pub_house, genre_id=:genre_id, author_id=:author_id  where id=:id";
    private final String DELETE_QUERY = "DELETE FROM books where id=:id";
    private final String FIND_ALL     = "SELECT * FROM books order by name";
    private final String FIND_BY_ID   = "SELECT * FROM books WHERE id=:id";
    private final String FIND_BY_ISBN = "SELECT * FROM books WHERE isbn=:isbn";
    private final String FIND_BY_NAME = "SELECT * FROM books WHERE name=:name";
    private final String FIND_BY_AUTH = "SELECT * FROM books WHERE author_id=:author_id ORDER by name";


    private NamedParameterJdbcTemplate jdbcTemplate;
    private GenreRepository genreRepository;
    private AuthorRepository authorRepository;

    @Autowired
    public JdbcBookRepositoryImpl(GenreRepository genreRepository, AuthorRepository authorRepository, NamedParameterJdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
        this.authorRepository = authorRepository;
        this.genreRepository = genreRepository;
    }


    @Override
    public Book save(Book book) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue( "name",      book.getName());
        parameters.addValue( "isbn",      book.getIsbn());
        parameters.addValue( "pub_place", book.getPublicationPlace());
        parameters.addValue( "pub_year",  book.getPublicationYear());
        parameters.addValue( "pub_house", book.getPublishingHouse());
        parameters.addValue( "genre_id",  book.getGenre().getID());
        parameters.addValue( "author_id",  book.getAuthor().getID());
        book = Utils.insertOrUpdate( book, jdbcTemplate, INSERT_QUERY, UPDATE_QUERY, parameters);

        return book;
    }

    @Override
    public boolean delete(Long ID) {
        return Utils.executeQueryWithID( ID,  jdbcTemplate, DELETE_QUERY);
    }

    @Override
    public Book get(Long ID) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue( "id", ID);
        return Utils.getByParameters( parameters, jdbcTemplate, FIND_BY_ID, new BookRowMapper());
    }

    @Override
    public Book getByISBN(String ISBN) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue( "isbn", ISBN);
        return Utils.getByParameters( parameters, jdbcTemplate, FIND_BY_ISBN, new BookRowMapper());
    }

    @Override
    public List<Book> getByName(String name) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue( "name", name);
        return  jdbcTemplate.query( FIND_BY_NAME, parameters, new BookRowMapper());
    }


    @Override
    public List<Book> getByAuthorID(Long AuthorID) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue( "author_id", AuthorID);
        return  jdbcTemplate.query( FIND_BY_AUTH, parameters, new BookRowMapper());
    }


    @Override
    public List<Book> getAll() {
        return jdbcTemplate.query( FIND_ALL, new BookRowMapper());
    }

    private class BookRowMapper implements RowMapper<Book>{

        @Override
        public Book mapRow(ResultSet resultSet, int i) throws SQLException {
            Book book = new Book();
            book.setID(   resultSet.getLong( "id"));
            book.setName( resultSet.getString( "name"));
            book.setPublicationYear(  resultSet.getInt( "pub_year"));
            book.setIsbn( resultSet.getString( "isbn"));
            book.setPublicationPlace( resultSet.getString( "pub_place"));
            book.setPublicationPlace( resultSet.getString( "pub_house"));
            //
            // читаем жанр
            //
            Genre genre = genreRepository.get( resultSet.getLong( "genre_id"));
            book.setGenre( genre);
            //
            // читаем автора
            //
            book.setAuthor( authorRepository.get( resultSet.getLong( "author_id")));

            return book;
        }
    }


}
