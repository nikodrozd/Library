package com.library.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotBlank(message = "Name cannot be empty")
    private String name;

    @NotBlank(message = "Surname cannot be empty")
    private String surname;

    @NotNull(message = "Date of birth cannot be empty")
    @JsonFormat(pattern = "dd.MM.yyyy")
    @Past(message = "Date of birth cannot be in future")
    private Date dateOfBirth;

    @OneToMany(mappedBy = "user")
    private List<Book> bookList = new LinkedList<>();

    @JsonProperty
    public long getId() {
        return id;
    }

    @JsonIgnore
    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty
    public List<Book> getBookList() {
        return bookList;
    }

    @JsonIgnore
    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void copyValuesFrom(User user) {
        this.name = user.name;
        this.surname = user.surname;
        this.dateOfBirth = user.dateOfBirth;
    }
}
