package ru.aleksandrov.Entity;

import java.util.HashSet;
import java.util.Set;

public class EnglishWord {
    private int englishId;
    private String englishWord;
    private Set<Word> words = new HashSet<Word>(0);

    public EnglishWord(){
    }

    public EnglishWord(int english_id, String english_word){
        this.englishId = english_id;
        this.englishWord = english_word;
    }

    public int getEnglishId() {
        return englishId;
    }

    public void setEnglishId(int english_id) {
        this.englishId = english_id;
    }

    public String getEnglishWord() {
        return englishWord;
    }

    public void setEnglishWord(String english_word) {
        this.englishWord = english_word;
    }

    @Override
    public String toString() {
        return "EnglishWord{" +
                "english_id=" + englishId +
                ", english_word='" + englishWord + '\'' +
                '}';
    }
}
