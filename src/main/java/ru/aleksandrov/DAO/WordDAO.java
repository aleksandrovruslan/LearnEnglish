package ru.aleksandrov.DAO;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.aleksandrov.Models.EnglishWord;
import ru.aleksandrov.Models.RussianWord;
import ru.aleksandrov.Models.User;
import ru.aleksandrov.Models.Word;
import ru.aleksandrov.Util.DBConnection;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.*;
import java.util.*;

public class WordDAO {
    private static Logger log = LogManager.getLogger(WordDAO.class);
    private Connection con = null;

    public WordDAO() throws PropertyVetoException, IOException, SQLException {
        DBConnection dbc = DBConnection.getInstance();
        con = dbc.getConnection();
    }

    public boolean isAddWords(Word ... words) throws SQLException {
        String SQL = "INSERT INTO words(user_id, english_id, russian_id, collection_id" +
                ", number_answers, correct_answers) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstatement = con.prepareStatement(SQL)){
            con.setAutoCommit(false);
            List<RussianWord> russianWords;
            for(Word word:words) {
                russianWords = word.getRussian();
                for(RussianWord r:russianWords) {
                    pstatement.setInt(1, word.getUser().getUserId());
                    pstatement.setInt(2, word.getEnglish().getEnglishId());
                    pstatement.setInt(3, r.getRussianId());
                    pstatement.setInt(4, word.getCollection().getCollectionId());
                    pstatement.setInt(5, word.getNumberAnswers());
                    pstatement.setInt(6, word.getCorrectAnswers());
                    pstatement.addBatch();
                }
            }
            pstatement.executeBatch();
            con.commit();
            con.setAutoCommit(true);
            return true;
        } catch (SQLException e) {
            log.error("addWord(): ", e);
            if (con != null) {
                try {
                    con.rollback();
                } catch (SQLException e1) {
                    log.error("addWord().rollback(): ", e1);
                }
            }
        } finally {
            if (con != null){
                con.close();
            }
        }
        return false;
    }

    public Word getWord(int userId, int englishId) {
        Word word = new Word();
        String SQL = "SELECT users.user_id AS user_id, users.name AS user_name" +
                ", english_words.english_word AS english" +
                ", russian_words.russian_id AS russian_id" +
                ", russian_words.russian_word AS russian" +
                ", words.collection_id, words.number_answers AS number_answers" +
                ", words.correct_answers AS correct_answers" +
                " FROM words INNER JOIN users" +
                " ON words.user_id = users.user_id" +
                " INNER JOIN english_words" +
                " ON words.english_id = english_words.english_id" +
                " INNER JOIN russian_words" +
                " ON words.russian_id = russian_words.russian_id" +
                " WHERE words.user_id = (?) AND  words.english_id = (?)";
        try (PreparedStatement pstatement = con.prepareStatement(SQL)) {
            List<RussianWord> russianWords = new ArrayList<>();
            pstatement.setInt(1, userId);
            pstatement.setInt(2, englishId);
            ResultSet result= pstatement.executeQuery();
            if (result.next()) {
                word.setEnglish(new EnglishWord(englishId, result.getString("english")));
                russianWords.add(new RussianWord(result.getInt("russian_id")
                        , result.getString("russian")));
                word.setNumberAnswers(result.getInt("number_answers"));
                word.setCorrectAnswers(result.getInt("correct_answers"));
            }
            while (result.next()) {
                russianWords.add(new RussianWord(result.getInt("russian_id")
                        , result.getString("russian")));
            }
            word.setRussian(russianWords);
            return word;
        } catch (SQLException e) {
            log.error("getWord(): ", e);
        }
        return null;
    }
    
    public boolean isUpdateWordsAnswers(Word ... words) throws SQLException {
        String SQL = "UPDATE words SET words.number_answers = (?), " +
                "words.correct_answers = (?) WHERE words.user_id = (?) " +
                "AND words.english_id = (?)";
        try (PreparedStatement pstatement = con.prepareStatement(SQL)) {
            con.setAutoCommit(false);
            List<RussianWord> russianWords;
            for (Word word:words) {
                pstatement.setInt(1, word.getNumberAnswers());
                pstatement.setInt(2, word.getCorrectAnswers());
                pstatement.setInt(3, word.getUser().getUserId());
                pstatement.setInt(4, word.getEnglish().getEnglishId());
                pstatement.addBatch();
            }
            pstatement.executeBatch();
            con.commit();
            con.setAutoCommit(true);
            con.close();
            return true;
        } catch(SQLException e) {
            log.error("isUpdateWord(): ", e);
            if (con != null) {
                try {
                    con.rollback();
                } catch (SQLException e1) {
                    log.error("addWord().rollback(): ", e1);
                }
            }
        } finally {
            if (con != null) {
                con.close();
            }
        }
        return false;
    }
    
    public boolean isDeleteWord(int userId, int englishId) {
        String SQL = "DELETE FROM words WHERE user_id = (?) " +
                "AND english_id = (?)";
        try (PreparedStatement pstatement = con.prepareStatement(SQL)){
            con.setAutoCommit(false);
            pstatement.setInt(1, userId);
            pstatement.setInt(2, englishId);
            pstatement.executeUpdate();
            con.commit();
            con.setAutoCommit(true);
            return true;
        } catch (SQLException e) {
            log.error("isDeleteWord(): ", e);
            if (con != null) {
                try {
                    con.rollback();
                } catch (SQLException e1) {
                    log.error("isDeleteWord().rollback(): ", e1);
                }
            }
        }
        return false;
    }
    
    public List<Word> getWords(int userId) {
        Map<Integer, Word> words = new HashMap<>();
        String SQL = "SELECT words.english_id AS english_id, " +
                "words.russian_id AS russian_id, " +
                "words.number_answers AS  number_answers, " +
                "words.correct_answers AS correct_answers, " +
                "english_words.english_word AS english_word, " +
                "russian_words.russian_word AS russian_word " +
                "FROM words INNER JOIN english_words " +
                "ON words.english_id = english_words.english_id " +
                "INNER JOIN russian_words " +
                "ON words.russian_id = russian_words.russian_id " +
                "WHERE words.user_id = (?)";
        try (PreparedStatement pstatement = con.prepareStatement(SQL)) {
            pstatement.setInt(1, userId);
            ResultSet result = pstatement.executeQuery();
            while (result.next()) {
                Integer englishId = result.getInt("english_id");
                Word word = words.get(englishId);
                if (word == null) {
                    word = new Word();
                    word.setEnglish(new EnglishWord(englishId, result.getString("english_word")));
                    words.put(word.getEnglish().getEnglishId(), word);
                }
                User user = new User();
                user.setUserId(userId);
                word.setUser(user);
                word.getRussian().add(new RussianWord(result.getInt("russian_id")
                        , result.getString("russian_word")));
                word.setNumberAnswers(result.getInt("number_answers"));
                word.setCorrectAnswers(result.getInt("correct_answers"));
            }
            return new ArrayList<>(words.values());
        } catch(SQLException e) {
            log.error("getWords(): ", e);
        }
        return null;
    }
}