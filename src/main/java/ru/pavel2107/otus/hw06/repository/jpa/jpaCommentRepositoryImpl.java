package ru.pavel2107.otus.hw06.repository.jpa;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.pavel2107.otus.hw06.domain.Comment;
import ru.pavel2107.otus.hw06.repository.CommentRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@Transactional( readOnly = false)
@Profile( "jpa")
public class jpaCommentRepositoryImpl implements CommentRepository {

    @PersistenceContext
    private EntityManager em;


    @Override
    public Comment save(Comment comment) {
        if( comment.isNew()){
            em.persist( comment);
        } else{
            comment = em.merge( comment);
        }
        return comment;
    }

    @Override
    public Comment get(Long ID) {
        return em.find( Comment.class, ID);
    }

    @Override
    public List<Comment> getAll(Long BookID) {
        TypedQuery<Comment> query = em
                .createQuery( "select c from Comment c where c.book.id=:book_id order by c.dateTime", Comment.class)
                .setParameter( "book_id", BookID);
        return query.getResultList();
    }
}
