package ru.aleksandrov.DAO;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.aleksandrov.Entity.EnglishWord;
import ru.aleksandrov.Entity.RussianWord;
import ru.aleksandrov.Entity.User;
import ru.aleksandrov.Entity.Word;
import ru.aleksandrov.Util.DBConnection;

        import java.beans.PropertyVetoException;
        import java.io.IOException;
        import java.sql.*;
        import java.util.ArrayList;
        import java.util.List;

public class WordDAO {
    private static Logger log = LogManager.getLogger(RoleDAO.class);
    private Connection con = null;

    public WordDAO() throws PropertyVetoException, IOException, SQLException {
        DBConnection dbc = DBConnection.getInstance();
        con = dbc.getConnection();
    }

    public boolean isAddWords(Word ... words){
        String SQL = "INSERT INTO words(user_id, english_id, russian_id, collection_id" +
                ", number_answers, correct_answers) VALUES (?, ?, ?, ?, ?, ?)";
        try(PreparedStatement pstatement = con.prepareStatement(SQL)){
            con.setAutoCommit(false);
            for(Word word:words) {
                pstatement.setInt(1, word.getUser().getUserId());
                pstatement.setInt(2, word.getEnglish().getEnglishId());
                pstatement.setInt(3, word.getRussian().getRussianId());
                pstatement.setInt(4, word.getCollection().getCollectionId());
                pstatement.setInt(5, word.getNumberAnswers());
                pstatement.setInt(6, word.getCorrectAnswers());
                pstatement.addBatch();
            }
            pstatement.executeBatch();
            con.commit();
            con.setAutoCommit(true);
            return true;
        }catch (SQLException e){
            log.error("addWord(): ", e);
        }
        return false;
    }

    public List<Word> getWord(int userId, int englishId){
        List<Word> words = null;
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
        try(PreparedStatement pstatement = con.prepareStatement(SQL)){
            words = new ArrayList<>();
            pstatement.setInt(1, userId);
            pstatement.setInt(2, englishId);
            ResultSet result= pstatement.executeQuery();
            while (result.next()){
                Word word = new Word();
                word.setEnglish(new EnglishWord(englishId, result.getString("english")));
                word.setRussian(new RussianWord(result.getInt("russian_id")
                        , result.getString("russian")));
                word.setNumberAnswers(result.getInt("number_answers"));
                word.setCorrectAnswers(result.getInt("correct_answers"));
                words.add(word);
            }
            return words;
        }catch (SQLException e){
            log.error("getWord(): ", e);
        }
        return words;
    }
    
    public boolean isUpdateWordsAnswers(Word ... words){
        String SQL = "UPDATE FROM words SET words.number_answers = (?), " +
                "words.correct_answers = (?) WHERE words.user_id = (?) " +
                "AND words.english_id = (?) AND words.russian_id = (?)";
        try(PreparedStatement pstatement = con.prepareStatement(SQL)){
            con.setAutoCommit(false);
            for(Word word:words){
                pstatement.setInt(1, word.getNumberAnswers());
                pstatement.setInt(2, word.getCorrectAnswers());
                pstatement.setInt(3, word.getUser().getUserId());
                pstatement.setInt(4, word.getEnglish().getEnglishId());
                pstatement.setInt(5, word.getRussian().getRussianId());
                pstatement.addBatch();
            }
            pstatement.executeBatch();
            con.commit();
            con.setAutoCommit(true);
            return true;
        }catch(SQLException e){
            log.error("isUpdateWord(): ", e);
        }
        return false;
    }
    
    public boolean isDeleteWord(Word ... words){
        String SQL = "DELETE FROM words WHERE user_id = (?) AND english_id = (?) AND russian_id = (?)";
        try(PreparedStatement pstatement = con.prepareStatement(SQL)){
            con.setAutoCommit(false);
            for(Word word:words) {
                pstatement.setInt(1, word.getUser().getUserId());
                pstatement.setInt(2, word.getEnglish().getEnglishId());
                pstatement.setInt(3, word.getRussian().getRussianId());
                pstatement.addBatch();
            }
            pstatement.executeBatch();
            con.commit();
            con.setAutoCommit(true);
            return true;
        }catch(SQLException e){
            log.error("isDeleteWord(): ", e);
        }
        return false;
    }
    
    public List<Word> getWords(Word word){
        List<Word> words = new ArrayList<>(0);
        String SQL = "";
        try(PreparedStatement pstatement = con.prepareStatement(SQL)){
            con.setAutoCommit(false);
            con.commit();
            con.setAutoCommit(true);
            return words;
        }catch(SQLException e){
            log.error("getWords(): ", e);
        }
        return words;
    }

    String str = "SELECT users.user_id AS user_id, users.name AS user_name, english_words.english_word AS english, russian_words.russian_id AS russian_id, russian_words.russian_word AS russian, words.collection_id, words.number_answers, words.correct_answers FROM words INNER JOIN users ON words.user_id = users.user_id INNER JOIN english_words ON words.english_id = english_words.english_id INNER JOIN russian_words ON words.russian_id = russian_words.russian_id WHERE words.user_id = 1 AND  words.english_id = 12";
}