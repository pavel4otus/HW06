package ru.pavel2107.otus.hw06.repository.jdbc;

import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import ru.pavel2107.otus.hw06.domain.AbstractNamedEntity;
import ru.pavel2107.otus.hw06.domain.Author;
import ru.pavel2107.otus.hw06.domain.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Profile( "jdbc")
public class Utils {

    public static <T extends AbstractNamedEntity> T insertOrUpdate(
            T namedEntity,
            NamedParameterJdbcTemplate jdbcTemplate,
            String INSERT_QUERY,
            String UPDATE_QUERY,
            MapSqlParameterSource parameters) {

        if( namedEntity.isNew())  {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            if( jdbcTemplate.update( INSERT_QUERY, parameters, keyHolder, new String[]{"id"}) > 0) {
                namedEntity.setID(keyHolder.getKey().longValue());
            }
        } else {
            parameters.addValue("id", namedEntity.getID());
            jdbcTemplate.update( UPDATE_QUERY, parameters);
        }
        return namedEntity;
    }

    public static <T extends AbstractNamedEntity> T getByParameters(
            MapSqlParameterSource parameters,
            NamedParameterJdbcTemplate jdbcTemplate,
            String QUERY,
            RowMapper rowMapper){
        List<T> list = jdbcTemplate.query( QUERY, parameters, rowMapper);
        return list != null && list.size() > 0 ? list.get( 0): null;
    }


    public static boolean executeQueryWithID( Long ID,  NamedParameterJdbcTemplate jdbcTemplate, String QUERY ) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("id", ID);
        return jdbcTemplate.update( QUERY, parameters) != 0;
    }


    public static class AuthorRowMapper implements RowMapper<Author>{
        @Override
        public Author mapRow(ResultSet resultSet, int i) throws SQLException {
            Author author = new Author(
                    resultSet.getLong( "id"),
                    resultSet.getString( "name"),
                    resultSet.getDate( "birthdate").toLocalDate(),
                    resultSet.getString( "email"),
                    resultSet.getString( "phone"),
                    resultSet.getString( "address")
            );
            return author;
        }
    }


    public static class GenreRowMapper implements RowMapper<Genre>{

        @Override
        public Genre mapRow(ResultSet resultSet, int i) throws SQLException {
            Genre genre = new Genre();
            genre.setName( resultSet.getString( "name"));
            genre.setID(   resultSet.getLong( "id"));
            return genre;
        }
    }



}
