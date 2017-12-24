package ru.aleksandrov.DAO;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.aleksandrov.Models.RussianWord;
import ru.aleksandrov.Util.DBConnection;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class RussianWordDAO {

    private static Logger log = LogManager.getLogger(RussianWordDAO.class);
    private Connection con = null;

    public RussianWordDAO() throws PropertyVetoException, IOException, SQLException {
        DBConnection dbc = DBConnection.getInstance();
        con = dbc.getConnection();
    }

    public List<RussianWord> addRussianWords(Set<String> russians) throws SQLException {
        List<RussianWord> russianWords = new ArrayList<>(russians.size());
        String SQL = "INSERT INTO russian_words (russian_word) VALUES (?)";
        try (PreparedStatement pstatement = con.prepareStatement(SQL
                , Statement.RETURN_GENERATED_KEYS)) {
            con.setAutoCommit(false);
            for(String r:russians){
                pstatement.setString(1, r);
                pstatement.addBatch();
            }
            pstatement.executeBatch();
            ResultSet generatedKeys = pstatement.getGeneratedKeys();
            while (generatedKeys.next()){
                RussianWord russianWord = new RussianWord();
                russianWord.setRussianId(generatedKeys.getInt(1));
                russianWords.add(russianWord);
            }
            con.commit();
            con.setAutoCommit(true);
            return russianWords;
        } catch (SQLException e) {
            log.error("addRussianWord(): ", e);
            if (con != null) {
                try {
                    con.rollback();
                } catch (SQLException e1) {
                    log.error("addRussianWord(): con.rollback(): ", e1);
                }
            }
        } finally {
            if (con != null) {
                con.close();
            }
        }
        return russianWords;
    }

    public RussianWord getRussianWord(int id) {
        RussianWord russian = null;
        String SQL = "SELECT * FROM russian_words WHERE russian_id = (?)";
        try (PreparedStatement pstatment = con.prepareStatement(SQL)) {
            pstatment.setInt(1, id);
            ResultSet result = pstatment.executeQuery();
            if(result.next()){
                russian = new RussianWord(id, result.getString("russian_word"));
                return russian;
            }
        } catch (SQLException e) {
            log.error("getRussianWord(): ", e);
        }
        return russian;
    }

    public boolean isUpdateRussianWord(RussianWord russian) throws SQLException {
        String SQL = "UPDATE russian_words SET russian_word = (?) WHERE russian_id = (?)";
        try (PreparedStatement pstatement = con.prepareStatement(SQL)) {
            con.setAutoCommit(false);
            pstatement.setString(1, russian.getRussianWord());
            pstatement.setInt(2, russian.getRussianId());
            pstatement.executeUpdate();
            con.commit();
            con.setAutoCommit(true);
            return true;
        } catch (SQLException e) {
            log.error("isUpdateRussianWord(): ", e);
            if (con != null) {
                try {
                    con.rollback();
                } catch (SQLException e1) {
                    log.error("isUpdateRussianWord(): con.rollback(): ", e1);
                }
            }
        } finally {
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public boolean isDeleteRussianWord(int id) {
        String SQL = "DELETE FROM russian_words WHERE russian_id = (?)";
        try (PreparedStatement pstatement = con.prepareStatement(SQL)) {
            con.setAutoCommit(false);
            pstatement.setInt(1, id);
            pstatement.executeUpdate();
            con.commit();
            con.setAutoCommit(true);
            return true;
        } catch (SQLException e) {
            log.error("isDeleteRussianWord(): ", e);
            if (con != null) {
                try {
                    con.rollback();
                } catch (SQLException e1) {
                    log.error("isDeleteRussianWord(): con.rollback(): ", e1);
                }
            }
        }
        return false;
    }
}
