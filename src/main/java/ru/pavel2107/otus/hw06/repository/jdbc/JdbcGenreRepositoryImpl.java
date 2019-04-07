package ru.pavel2107.otus.hw06.repository.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.pavel2107.otus.hw06.domain.AbstractNamedEntity;
import ru.pavel2107.otus.hw06.domain.Genre;
import ru.pavel2107.otus.hw06.repository.GenreRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@SuppressWarnings({"SqlNoDataSourceInspection", "ConstantConditions", "SqlDialectInspection"})
@Profile( "jdbc")
public class JdbcGenreRepositoryImpl implements GenreRepository {

    private static final String INSERT_QUERY = "INSERT INTO genre(  name) values( :name)";
    private static final String UPDATE_QUERY = "UPDATE genre SET name = :name where id = :id";
    private static final String DELETE_QUERY = "DELETE FROM genre WHERE id = :id";
    private static final String FIND_BY_ID   = "SELECT * FROM genre where id = :id";
    private static final String FIND_ALL     = "SELECT * FROM genre order by id";

    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcGenreRepositoryImpl( NamedParameterJdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Genre save(Genre genre) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue( "name", genre.getName());

        genre = Utils.insertOrUpdate( genre, jdbcTemplate, INSERT_QUERY, UPDATE_QUERY, parameters);
        return get( genre.getID());
    }

    @Override
    public boolean delete(Long ID) {
        return Utils.executeQueryWithID( ID,  jdbcTemplate, DELETE_QUERY);
    }

    @Override
    public Genre get(Long ID) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue( "id", ID);
        return Utils.getByParameters( parameters, jdbcTemplate, FIND_BY_ID, new Utils.GenreRowMapper());
    }

    @Override
    public List<Genre> getAll() {
        List<Genre> list = jdbcTemplate.query( FIND_ALL, new Utils.GenreRowMapper());
        return list;
    }

}
