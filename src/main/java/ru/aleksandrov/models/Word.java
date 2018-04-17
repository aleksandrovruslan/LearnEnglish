package ru.aleksandrov.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Word implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(
            min = 1,
            max = 255,
            message = "Incorrect size word collection. Min 1, max 255."
    )
    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "word")
    private Set<Quiz> quizzes = new HashSet<>();

    @ManyToMany(mappedBy = "translation")
    private Set<Quiz> quizTranslations = new HashSet<>();

    public Word() {
    }

    public Word(Set<Quiz> quizzes, Set<Quiz> quizTranslations, String name) {
        this.quizzes = quizzes;
        this.quizTranslations = quizTranslations;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Quiz> getQuizzes() {
        return quizzes;
    }

    public void setQuizzes(Set<Quiz> quizzes) {
        this.quizzes = quizzes;
    }

    public Set<Quiz> getQuizTranslations() {
        return quizTranslations;
    }

    public void setQuizTranslations(Set<Quiz> quizTranslations) {
        this.quizTranslations = quizTranslations;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Word{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
