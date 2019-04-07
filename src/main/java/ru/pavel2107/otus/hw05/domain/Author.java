package ru.pavel2107.otus.hw05.domain;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "authors")
public class Author extends AbstractNamedEntity{

    @Column( name = "birthdate")
    private LocalDate birthDate;

    @Column( name ="email")
    private String    email;

    @Column( name= "phone")
    private String    phone;

    @Column( name = "address")
    private String    address;

    @OneToMany( mappedBy = "author", fetch = FetchType.LAZY)
    private List<Book> books;

    public Author(){}

    public Author( Long ID, String Name, LocalDate birthDate, String email, String phone, String address){
        super( ID, Name);
        this.birthDate = birthDate;
        this.email     = email;
        this.phone     = phone;
        this.address   = address;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + getID() +
                ", name='" + getName() + '\'' +
                ", birthDate=" + birthDate +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
