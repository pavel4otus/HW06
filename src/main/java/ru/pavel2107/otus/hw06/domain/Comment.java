package ru.pavel2107.otus.hw06.domain;

import javax.persistence.*;
import java.time.LocalDateTime;


//
// id - id комментария
// dateTime - время когда оставили
// name - имя пользователя
// comment - сам сомментарий
//

@Entity
@Table( name = "comments")
public class Comment extends AbstractNamedEntity {

    @Column( name = "datetime")
    private LocalDateTime dateTime;

    @Column( name = "comment")
    private String comment;

    @ManyToOne
    @JoinColumn( name="book_id")
    private Book book;


    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "dateTime=" + dateTime +
                ", user =" + getName() +
                ", comment='" + comment +
                "'}";
    }
}
