package ru.aleksandrov.DAO;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.aleksandrov.Entity.RussianWord;
import ru.aleksandrov.Entity.Word;
import ru.aleksandrov.util.DBConnection;

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

    public boolean isAddWord(Word word){
        String SQL = "INSERT INTO answers(user_id, english_id, collection_id" +
                ", number_answers, correct_answers) VALUES (?, ?, ?, ?, ?)";
        try(PreparedStatement pstatement = con.prepareStatement(SQL)){
            con.setAutoCommit(false);
            pstatement.setInt(1, word.getUser().getUserId());
            pstatement.setInt(2, word.getEnglish().getEnglishId());
            pstatement.setInt(3, word.getCollection().getCollectionId());
            pstatement.setInt(4, word.getNumberAnswers());
            pstatement.setInt(5, word.getCorrectAnswers());
            pstatement.executeUpdate();
            con.commit();
            con.setAutoCommit(true);
            return true;
        }catch (SQLException e){
            log.error("addWord(): ", e);
        }
        return false;
    }

    public Word getWord(int userId, int englishId){
        Word word = new Word();
        String SQL = "SELECT users.user_id AS user_id, users.name AS user_name" +
                ", english_words.english_word AS english" +
                ", russian_words.russian_id AS russian_id" +
                ", russian_words.russian_word AS russian" +
                ", words.collection_id, words.number_answers" +
                ", words.correct_answers" +
                " FROM words INNER JOIN users" +
                " ON words.user_id = users.user_id" +
                " INNER JOIN english_words" +
                " ON words.english_id = english_words.english_id" +
                " INNER JOIN russian_words" +
                " ON words.russian_id = russian_words.russian_id" +
                " WHERE words.user_id = (?) AND  words.english_id = (?)";
        try(PreparedStatement pstatement = con.prepareStatement(SQL)){
            pstatement.setInt(1, userId);
            pstatement.setInt(2, englishId);
            ResultSet resultSet = pstatement.executeQuery();
            while (resultSet.next()){
                word.setRussian(new RussianWord(resultSet.getInt("russian_id")
                        , resultSet.getString("russian")));
            }
            return word;
        }catch (SQLException e){
            log.error("getWord(): ", e);
        }
        return word;
    }
    
    public boolean isUpdateWord(Word word){
        String SQL = "";
        try(PreparedStatement pstatement = con.prepareStatement(SQL)){
            con.setAutoCommit(false);
            con.commit();
            con.setAutoCommit(true);
            return true;
        }catch(SQLException e){
            log.error("isUpdateWord(): ", e);
        }
        return false;
    }
    
    public boolean isDeleteWord(Word word){
        String SQL = "";
        try(PreparedStatement pstatement = con.prepareStatement(SQL)){
            con.setAutoCommit(false);
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
}