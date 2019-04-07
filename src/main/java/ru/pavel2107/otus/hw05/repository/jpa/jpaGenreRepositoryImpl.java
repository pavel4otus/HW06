package ru.pavel2107.otus.hw05.repository.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.pavel2107.otus.hw05.domain.Genre;
import ru.pavel2107.otus.hw05.repository.GenreRepository;

import javax.persistence.*;
import java.util.List;

@Repository
@Transactional( readOnly = false)
@Profile( "jpa")
public class jpaGenreRepositoryImpl implements GenreRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Genre save(Genre genre) {
        if( genre.isNew()){
            em.persist( genre);
        } else {
            genre = em.merge( genre);
        }
        return genre;
    }

    @Override
    public boolean delete(Long ID) {
        return em
                .createQuery( "delete from Genre g where g.id =:id")
                .setParameter( "id", ID)
                .executeUpdate() !=0 ;
    }

    @Override
    public Genre get(Long ID) {
        return em.find( Genre.class, ID);
    }

    @Override
    public List<Genre> getAll() {
        TypedQuery<Genre> query = em.createQuery( "select g from Genre g", Genre.class);
        return query.getResultList();
    }
}
