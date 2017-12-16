package ru.aleksandrov.Models;

import java.util.ArrayList;
import java.util.List;

public class Word {
    private User user;
    private EnglishWord english;
    private List<RussianWord> russian = new ArrayList<>();
    private WordsCollection collection;
    private int numberAnswers;
    private int correctAnswers;

    public Word() {
    }

    public Word(User user, EnglishWord english, List<RussianWord> russian
            , WordsCollection collection, int numberAnswers, int correctAnswers) {
        this.user = user;
        this.english = english;
        this.russian = russian;
        this.collection = collection;
        this.numberAnswers = numberAnswers;
        this.correctAnswers = correctAnswers;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public EnglishWord getEnglish() {
        return english;
    }

    public void setEnglish(EnglishWord english) {
        this.english = english;
    }

    public List<RussianWord> getRussian() {
        return russian;
    }

    public void setRussian(List<RussianWord> russian) {
        this.russian = russian;
    }

    public WordsCollection getCollection() {
        return collection;
    }

    public void setCollection(WordsCollection collection) {
        this.collection = collection;
    }

    public int getNumberAnswers() {
        return numberAnswers;
    }

    public void setNumberAnswers(int numberAnswers) {
        this.numberAnswers = numberAnswers;
    }

    public int getCorrectAnswers() {
        return correctAnswers;
    }

    public void setCorrectAnswers(int correctAnswers) {
        this.correctAnswers = correctAnswers;
    }

    @Override
    public String toString() {
        return "Word{" +
                "user=" + user +
                ", english=" + english +
                ", russian=" + russian +
                ", collection=" + collection +
                ", numberAnswers=" + numberAnswers +
                ", correctAnswers=" + correctAnswers +
                '}';
    }
}
