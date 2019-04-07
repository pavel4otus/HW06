package ru.pavel2107.otus.hw05.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.pavel2107.otus.hw05.domain.Comment;
import ru.pavel2107.otus.hw05.repository.CommentRepository;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    private CommentRepository repository;

    @Autowired
    public CommentServiceImpl( CommentRepository repository){
        this.repository = repository;
    }


    @Override
    public Comment save(Comment comment) {
        return repository.save( comment);
    }

    @Override
    public Comment get(Long ID) {
        return repository.get( ID);
    }

    @Override
    public List<Comment> getAll(Long BookID) {
        return repository.getAll( BookID);
    }
}
