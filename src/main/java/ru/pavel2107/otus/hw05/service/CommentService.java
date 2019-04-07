package ru.pavel2107.otus.hw05.service;

import ru.pavel2107.otus.hw05.domain.Comment;

import java.util.List;

public interface CommentService {
    Comment save(Comment comment);
    Comment get( Long ID);
    List<Comment> getAll(Long BookID);
}
