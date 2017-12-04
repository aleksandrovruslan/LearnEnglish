package ru.aleksandrov.DAO;

import ru.aleksandrov.Entity.*;

import java.sql.SQLException;
import java.util.List;

public interface Storage {
    int addRole(Role role);
    Role getRole(int id);

    List<Role> getRoles() throws SQLException;

    int addEnglishWord(EnglishWord english);
    EnglishWord getEnglishWord(int id);
    int addCollection(WordsCollection collection);
    WordsCollection getCollection(int id);
    RussianWord[] addRussianWords(RussianWord ... russians);
    RussianWord getRussianWord(int id);
    int addUser(User user);
    User getUser(int id);
    List<User> getUsers();
    boolean addWord(Word word);
    Word getWord(int id);
}
