package ru.aleksandrov.Entity;

import java.util.HashSet;
import java.util.Set;

public class RussianWord {
    private int russianId;
    private String russianWord;
    private Set<Word> words = new HashSet<Word>(0);

    public RussianWord() {
    }
    
    public RussianWord(int russian_id, String russian_word) {
        this.russianId = russian_id;
        this.russianWord = russian_word;
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

    public void setRussianWord(String russian_word) {
        this.russianWord = russian_word;
    }

    @Override
    public String toString() {
        return "RussianWord{" +
                "russian_id=" + russianId +
                ", russian_word='" + russianWord + '\'' +
                '}';
    }
}
