package ru.pavel2107.otus.hw06.service;

import ru.pavel2107.otus.hw06.domain.Comment;

import java.util.List;

public interface CommentService {
    Comment save(Comment comment);
    Comment get( Long ID);
    List<Comment> getAll(Long BookID);
}
