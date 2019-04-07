package ru.pavel2107.otus.hw05.repository.jpa;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.pavel2107.otus.hw05.domain.Book;
import ru.pavel2107.otus.hw05.repository.BookRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@Transactional( readOnly = false)
@Profile( "jpa")
public class jpaBookRepositoryImpl implements BookRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Book save(Book book) {
        if( book.isNew()){
            em.persist( book);
        } else {
            book = em.merge( book);
        }
        return book;
    }

    @Override
    public boolean delete(Long ID) {
        return em
                .createQuery( "delete from Book b where b.id =:id")
                .setParameter( "id", ID)
                .executeUpdate() !=0 ;    }

    @Override
    public Book get(Long ID) {
        return em.find( Book.class, ID);
    }

    @Override
    public Book getByISBN(String ISBN) {
        TypedQuery<Book> query = em
                .createQuery( "select b from Book b where b.isbn = :isbn", Book.class)
                .setParameter( "isbn", ISBN);
        return query.getSingleResult();
    }

    @Override
    public List<Book> getByName(String name) {
        TypedQuery<Book> query = em
                .createQuery( "select b from Book b where b.name = :name", Book.class)
                .setParameter( "name", name);
        return query.getResultList();
    }

    @Override
    public List<Book> getByAuthorID(Long AuthorID) {
        TypedQuery<Book> query = em
                .createQuery( "select b from Book b where b.author.id = :author_id", Book.class)
                .setParameter( "author_id", AuthorID);
        return query.getResultList();
    }

    @Override
    public List<Book> getAll() {
        TypedQuery<Book> query = em.createQuery( "select b from Book b", Book.class);
        return query.getResultList();
    }
}
