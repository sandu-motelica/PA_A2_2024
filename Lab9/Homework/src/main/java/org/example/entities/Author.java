package org.example.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "authors")
@NamedQueries({
        @NamedQuery(name = "Author.findAll", query = "SELECT a FROM Author a"),
        @NamedQuery(name = "Author.findByName", query = "SELECT a FROM Author a WHERE a.name LIKE :name")
})
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "author")
    private List<BookAuthor> bookAuthors;

    public List<BookAuthor> getBookAuthors() {
        return bookAuthors;
    }

    public void setBookAuthors(List<BookAuthor> bookAuthors) {
        this.bookAuthors = bookAuthors;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
