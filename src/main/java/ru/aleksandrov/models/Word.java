package ru.aleksandrov.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
public class Word implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "word")
    private Set<Quiz> quizzes;

    @ManyToMany(mappedBy = "translation")
    private Set<Quiz> quizTranslations;

    private String name;

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
