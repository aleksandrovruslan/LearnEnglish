package ru.aleksandrov.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
public class Quiz implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false
            , cascade = {CascadeType.PERSIST, CascadeType.MERGE}
            , fetch = FetchType.LAZY)
    @JoinColumn(name = "word_id")
    private Word word;

    @ManyToMany(fetch = FetchType.LAZY
            , cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "word_quiz",
            joinColumns = @JoinColumn(name = "quiz_id"),
            inverseJoinColumns = @JoinColumn(name = "translation_word_id"))
    private Set<Word> translation;

    @ManyToMany(mappedBy = "quizzes", fetch = FetchType.LAZY)
    private Set<Collection> collections;

    public Quiz() {
    }

    public Quiz(Word word, Set<Word> translation, Set<Collection> collections) {
        this.word = word;
        this.translation = translation;
        this.collections = collections;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Word getWord() {
        return word;
    }

    public void setWord(Word word) {
        this.word = word;
    }

    public Set<Word> getTranslation() {
        return translation;
    }

    public void setTranslation(Set<Word> translation) {
        this.translation = translation;
    }

    public Set<Collection> getCollections() {
        return collections;
    }

    public void setCollections(Set<Collection> collections) {
        this.collections = collections;
    }

    @Override
    public String toString() {
        return "Quiz{" +
                "id=" + id +
                ", word=" + word +
                ", translation=" + translation +
                '}';
    }
}
