package ru.pavel2107.otus.hw05.repository.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.pavel2107.otus.hw05.domain.AbstractNamedEntity;
import ru.pavel2107.otus.hw05.domain.Author;
import ru.pavel2107.otus.hw05.repository.AuthorRepository;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ru.pavel2107.otus.hw05.repository.jdbc.Utils.insertOrUpdate;

@Repository
@SuppressWarnings({"SqlNoDataSourceInspection", "ConstantConditions", "SqlDialectInspection"})
@Profile( "jdbc")
public class JdbcAuthorRepositoryImpl implements AuthorRepository {

   private final String INSERT_QUERY = "insert into authors( name, birthdate, email, phone, address) values( :name, :birthdate, :email, :phone, :address)";
   private final String UPDATE_QUERY = "update authors set name =:name , birthdate=:birthdate, email=:email, phone=:phone, address=:address where id =:id";
   private final String DELETE_QUERY = "delete from authors where id=:id";
   private final String FIND_BY_NAME = "select * from authors where name=:name";
   private final String FIND_BY_ID   = "select * from authors where id=:id";
   private final String FIND_ALL     = "select * from authors order by name asc";

   private NamedParameterJdbcTemplate jdbcTemplate;

   @Autowired
   public JdbcAuthorRepositoryImpl( NamedParameterJdbcTemplate jdbcTemplate){
       this.jdbcTemplate = jdbcTemplate;
   }


    @Override
    public Author save(Author author) {
       MapSqlParameterSource parameters = new MapSqlParameterSource();
       parameters.addValue( "name",      author.getName());
       parameters.addValue( "birthdate", author.getBirthDate());
       parameters.addValue( "address",   author.getAddress());
       parameters.addValue( "email",     author.getEmail());
       parameters.addValue( "phone",     author.getPhone());

       author = Utils.insertOrUpdate( author, jdbcTemplate, INSERT_QUERY, UPDATE_QUERY, parameters);
       author = get( author.getID());

       return author;
    }

    @Override
    public boolean delete(Long ID) {
        return Utils.executeQueryWithID( ID,  jdbcTemplate, DELETE_QUERY);
    }

    @Override
    public Author get(Long ID) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue( "id", ID);
        return Utils.getByParameters( parameters, jdbcTemplate, FIND_BY_ID, new Utils.AuthorRowMapper());
    }

    @Override
    public List<Author> getByName(String name) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue( "name",      name);
        return jdbcTemplate.query( FIND_BY_NAME, parameters, new Utils.AuthorRowMapper());
    }

    @Override
    public List<Author> getAll() {
        return jdbcTemplate.query( FIND_ALL, new Utils.AuthorRowMapper());
    }


}
