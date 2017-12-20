package ru.aleksandrov.DAO;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.aleksandrov.Models.RussianWord;
import ru.aleksandrov.Util.DBConnection;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.*;

public class RussianWordDAO {

    private static Logger log = LogManager.getLogger(RussianWordDAO.class);
    private Connection con = null;

    public RussianWordDAO() throws PropertyVetoException, IOException, SQLException {
        DBConnection dbc = DBConnection.getInstance();
        con = dbc.getConnection();
    }

    public int addRussianWord(RussianWord russian){
        int id = 0;
        String SQL = "INSERT INTO russian_words (russian_word) VALUES (?)";
        try(PreparedStatement pstatement = con.prepareStatement(SQL
                , Statement.RETURN_GENERATED_KEYS)){
            con.setAutoCommit(false);
            pstatement.setString(1, russian.getRussianWord());
            pstatement.executeUpdate();
            ResultSet generatedKeys = pstatement.getGeneratedKeys();
            if (generatedKeys.next()){
                id = generatedKeys.getInt(1);
            }
            con.commit();
            con.setAutoCommit(true);
            return id;
        }catch (SQLException e){
            log.error("addRussianWord(): ", e);
            try {
                con.rollback();
            } catch (SQLException e1) {
                log.error("addRussianWord(): con.rollback(): ", e1);
            }
        }
        return id;
    }

    public RussianWord getRussianWord(int id){
        RussianWord russian = null;
        String SQL = "SELECT * FROM russian_words WHERE russian_id = (?)";
        try(PreparedStatement pstatment = con.prepareStatement(SQL)){
            pstatment.setInt(1, id);
            ResultSet result = pstatment.executeQuery();
            if(result.next()){
                russian = new RussianWord(id, result.getString("russian_word"));
                return russian;
            }
        }catch (SQLException e){
            log.error("getRussianWord(): ", e);
        }
        return russian;
    }

    public boolean isUpdateRussianWord(RussianWord russian){
        String SQL = "UPDATE russian_words SET russian_word = (?) WHERE russian_id = (?)";
        try(PreparedStatement pstatement = con.prepareStatement(SQL)){
            con.setAutoCommit(false);
            pstatement.setString(1, russian.getRussianWord());
            pstatement.setInt(2, russian.getRussianId());
            pstatement.executeUpdate();
            con.commit();
            con.setAutoCommit(true);
            return true;
        }catch (SQLException e){
            log.error("isUpdateRussianWord(): ", e);
            try {
                con.rollback();
            } catch (SQLException e1) {
                log.error("isUpdateRussianWord(): con.rollback(): ", e1);
            }
        }
        return false;
    }

    public boolean isDeleteRussianWord(int id){
        String SQL = "DELETE FROM russian_words WHERE russian_id = (?)";
        try(PreparedStatement pstatement = con.prepareStatement(SQL)){
            con.setAutoCommit(false);
            pstatement.setInt(1, id);
            pstatement.executeUpdate();
            con.commit();
            con.setAutoCommit(true);
            return true;
        }catch (SQLException e){
            log.error("isDeleteRussianWord(): ", e);
            try {
                con.rollback();
            } catch (SQLException e1) {
                log.error("isDeleteRussianWord(): con.rollback(): ", e1);
            }
        }
        return false;
    }
}
