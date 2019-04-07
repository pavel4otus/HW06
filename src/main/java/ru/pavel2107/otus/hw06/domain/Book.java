package ru.pavel2107.otus.hw06.domain;

import javax.persistence.*;
import java.util.List;

@Entity
@Table( name = "books")
public class Book extends AbstractNamedEntity{

    @ManyToOne
    @JoinColumn( name = "author_id")
    private Author author;

    @ManyToOne
    @JoinColumn( name = "genre_id")
    private Genre genre;

    @Column( name = "pub_house")
    private String    publishingHouse;

    @Column( name = "pub_year")
    private Integer   publicationYear;

    @Column( name = "pub_place")
    private String    publicationPlace;

    @Column( name = "isbn")
    private String    isbn;

    @OneToMany( mappedBy = "book", fetch = FetchType.LAZY)
    private List<Comment> comments;


    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public String getPublishingHouse() {
        return publishingHouse;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public void setPublishingHouse(String publishingHouse) {
        this.publishingHouse = publishingHouse;
    }

    public Integer getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(Integer publicationYear) {
        this.publicationYear = publicationYear;
    }

    public String getPublicationPlace() {
        return publicationPlace;
    }

    public void setPublicationPlace(String publicationPlace) {
        this.publicationPlace = publicationPlace;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }




    @Override
    public String toString() {
        return "Book{" +
                "id=" + getID() +
                ", name=" + getName() +
                ", author=" + author.getName() +
                ", genre=" + genre.getName() +
                ", publishingHouse='" + publishingHouse + '\'' +
                ", publicationYear=" + publicationYear +
                ", publicationPlace='" + publicationPlace + '\'' +
                ", ISBN='" + isbn + '\'' +
                '}';
    }
}
