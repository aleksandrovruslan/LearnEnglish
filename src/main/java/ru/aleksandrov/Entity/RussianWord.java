package ru.aleksandrov.Entity;

import java.util.HashSet;
import java.util.Set;

public class RussianWord {
    private int russianId;
    private String russianWord;
    private Set<Word> words = new HashSet<>(0);

    public RussianWord() {
    }
    
    public RussianWord(int russianId, String russianWord) {
        this.russianId = russianId;
        this.russianWord = russianWord;
    }

    public int getRussianId() {
        return russianId;
    }

    public void setRussianId(int russian_id) {
        this.russianId = russian_id;
    }

    public String getRussianWord() {
        return russianWord;
    }

    public void setRussianWord(String russianWord) {
        this.russianWord = russianWord;
    }

    @Override
    public String toString() {
        return "RussianWord{" +
                "russian_id=" + russianId +
                ", russian_word='" + russianWord + '\'' +
                '}';
    }
}
