package ru.pavel2107.otus.hw06.repository.jpa;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.pavel2107.otus.hw06.domain.Author;
import ru.pavel2107.otus.hw06.repository.AuthorRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@Transactional( readOnly = false)
@Profile( "jpa")
public class jpaAuthorRepositoryImpl implements AuthorRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Author save(Author author) {
        if( author.isNew()){
            em.persist( author);
        } else{
            author = em.merge( author);
        }
        return author;
    }

    @Override
    public boolean delete(Long ID) {
        return em
                .createQuery( "delete from Author a where a.id=:id")
                .setParameter( "id", ID)
                .executeUpdate() != 0;
    }

    @Override
    public Author get(Long ID) {
        return em.find( Author.class, ID);
    }

    @Override
    public List<Author> getByName(String name) {
        TypedQuery<Author> query = em
                .createQuery( "select a from Author a where a.name=:name", Author.class)
                .setParameter( "name", name);
        return query.getResultList();
    }

    @Override
    public List<Author> getAll() {
        TypedQuery<Author> query = em.createQuery( "select a from Author a", Author.class);
        return query.getResultList();
    }
}
