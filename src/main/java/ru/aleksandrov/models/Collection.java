package ru.aleksandrov.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
public class Collection implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne(optional = false
            , cascade = {CascadeType.PERSIST, CascadeType.MERGE}
            , fetch = FetchType.LAZY)
    @JoinColumn(name = "type_id")
    private CollectionType type;

    @ManyToMany(mappedBy = "collections", fetch = FetchType.LAZY)
    private Set<User> users;

    @ManyToMany(fetch = FetchType.LAZY
            , cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "collection_quiz"
            , joinColumns = @JoinColumn(name = "collection_id")
            , inverseJoinColumns = @JoinColumn(name = "quiz_id"))
    private Set<Quiz> quizzes;

    public Collection() {
    }

    public Collection(String name, CollectionType type, Set<User> users, Set<Quiz> quizzes) {
        this.name = name;
        this.type = type;
        this.users = users;
        this.quizzes = quizzes;
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

    public CollectionType getType() {
        return type;
    }

    public void setType(CollectionType type) {
        this.type = type;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Set<Quiz> getQuizzes() {
        return quizzes;
    }

    public void setQuizzes(Set<Quiz> quizzes) {
        this.quizzes = quizzes;
    }

    @Override
    public String toString() {
        return "Collection{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", quizzes=" + quizzes +
                '}';
    }
}
