package ru.aleksandrov.Models;

import java.util.HashSet;
import java.util.Set;

public class EnglishWord {
    private int englishId;
    private String englishWord;
    private Set<Word> words = new HashSet<>(0);

    public EnglishWord(){
    }

    public EnglishWord(int englishId, String englishWord){
        this.englishId = englishId;
        this.englishWord = englishWord;
    }

    public int getEnglishId() {
        return englishId;
    }

    public void setEnglishId(int englishId) {
        this.englishId = englishId;
    }

    public String getEnglishWord() {
        return englishWord;
    }

    public void setEnglishWord(String englishWord) {
        this.englishWord = englishWord;
    }

    @Override
    public String toString() {
        return "EnglishWord{" +
                "english_id=" + englishId +
                ", english_word='" + englishWord + '\'' +
                '}';
    }
}
