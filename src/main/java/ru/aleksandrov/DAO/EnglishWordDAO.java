package ru.aleksandrov.DAO;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.aleksandrov.Entity.EnglishWord;
import ru.aleksandrov.util.DBConnection;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.*;

public class EnglishWordDAO {

    private static Logger log = LogManager.getLogger(RoleDAO.class);
    private Connection con = null;

    public EnglishWordDAO() throws PropertyVetoException, IOException, SQLException {
        DBConnection dbc = DBConnection.getInstance();
        con = dbc.getConnection();
    }

    public int isAddEnglishWord(EnglishWord english){
        int id = 0;
        String SQL = "INSERT INTO english_words (english_word) VALUES (?)";
        try(PreparedStatement pstatement = con.prepareStatement(SQL
                        , Statement.RETURN_GENERATED_KEYS)){
            con.setAutoCommit(false);
            pstatement.setString(1, english.getEnglishWord());
            pstatement.executeUpdate();
            ResultSet generatedKeys = pstatement.getGeneratedKeys();
            if (generatedKeys.next()){
                id = generatedKeys.getInt("english_id");
            }
            con.commit();
            con.setAutoCommit(true);
            return id;
        }catch (SQLException e){
            log.error("isAddEnglishWord(): ", e);
            try {
                con.rollback();
            } catch (SQLException e1) {
                log.error("isAddEnglishWord(): con.rollback(): ", e1);
            }
        }
        return id;
    }

    public EnglishWord getEnglishWord(int id){
        EnglishWord english = null;
        String SQL = "SELECT * FROM english_words WHERE english_id = (?)";
        try(PreparedStatement pstatment = con.prepareStatement(SQL)){
            pstatment.setInt(1, id);
            ResultSet result = pstatment.executeQuery();
            if(result.next()){
                english = new EnglishWord(id, result.getString("english_word"));
                return english;
            }
        }catch (SQLException e){
            log.error("getEnglishWord(): ", e);
        }
        return english;
    }

    public boolean isUpdateEnglishWord(EnglishWord english){
        String SQL = "UPDATE FROM english_words SET english_word = (?) WHERE english_id = (?)";
        try(PreparedStatement pstatement = con.prepareStatement(SQL)){
            con.setAutoCommit(false);
            pstatement.setString(1, english.getEnglishWord());
            pstatement.setInt(2, english.getEnglishId());
            pstatement.executeUpdate();
            con.commit();
            con.setAutoCommit(true);
            return true;
        }catch (SQLException e){
            log.error("isUpdateEnglishWord(): ", e);
            try {
                con.rollback();
            } catch (SQLException e1) {
                log.error("isUpdateEnglishWord(): con.rollback(): ", e1);
            }
        }
        return false;
    }

    public boolean isDeleteEnglishWord(int id){
        String SQL = "DELETE FROM english_words WHERE english_id = (?)";
        try(PreparedStatement pstatement = con.prepareStatement(SQL)){
            con.setAutoCommit(false);
            pstatement.setInt(1, id);
            pstatement.executeUpdate();
            con.commit();
            con.setAutoCommit(true);
            return true;
        }catch (SQLException e){
            log.error("isDeleteEnglishWord(): ", e);
            try {
                con.rollback();
            } catch (SQLException e1) {
                log.error("isDeleteEnglishWord(): con.rollback(): ", e1);
            }
        }
        return false;
    }
}
