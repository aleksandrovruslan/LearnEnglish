package ru.aleksandrov.Models;

import java.util.HashSet;
import java.util.Set;

public class WordsCollection {
    private int collectionId;
    private String name;
    private Set<Word> words = new HashSet<>(0);

    public WordsCollection(){
    }

    public WordsCollection(int collectionId, String name){
        this.collectionId = collectionId;
        this.name = name;
    }

    public int getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(int collectionId) {
        this.collectionId = collectionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "WordsCollection{" +
                "collectionId=" + collectionId +
                ", name='" + name + '\'' +
                '}';
    }
}
