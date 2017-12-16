package ru.aleksandrov.DAO;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.aleksandrov.Models.WordsCollection;
import ru.aleksandrov.Util.DBConnection;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WordsCollectionDAO {
    private static Logger log = LogManager.getLogger(RoleDAO.class);
    private Connection con = null;

    public WordsCollectionDAO() throws PropertyVetoException, IOException, SQLException {
        DBConnection dbc = DBConnection.getInstance();
        con = dbc.getConnection();
    }

    public int isAddWordsCollection(WordsCollection collection){
        int id = 0;
        String SQL = "INSERT INTO words_collections (name) VALUES (?)";
        try(PreparedStatement pstatement = con.prepareStatement((SQL)
                , Statement.RETURN_GENERATED_KEYS)){
            con.setAutoCommit(false);
            pstatement.setString(1, collection.getName());
            pstatement.executeUpdate();
            ResultSet generatedKeys = pstatement.getGeneratedKeys();
            if(generatedKeys.next()){
                id = generatedKeys.getInt("collection_id");
            }
            con.commit();
            con.setAutoCommit(true);
            return id;
        } catch (SQLException e){
            log.error("isAddWordsCollection(): ", e);
            try {
                con.rollback();
            } catch (SQLException e1) {
                log.error("isAddWordsCollection(): con.rollback(): ", e1);
            }
        }
        return id;
    }

    public WordsCollection getWordsCollection(int id){
        WordsCollection collection = null;
        String SQL = "SELECT * FROM words_collections WHERE collection_id = (?)";
        try(PreparedStatement pstatement = con.prepareStatement(SQL)){
            pstatement.setInt(1, id);
            ResultSet resultSet = pstatement.executeQuery();
            if (resultSet.next()){
                collection = new WordsCollection(resultSet.getInt("collection_id")
                        , resultSet.getString("name"));
                return collection;
            }
        }catch (SQLException e){
            log.error("getWordsCollection(): ", e);
        }
        return collection;
    }

    public boolean isUpdateWordsCollection(WordsCollection collection){
        String SQL = "UPDATE FROM words_collections SET name = (?) WHERE collection_id = (?)";
        try(PreparedStatement pstatement = con.prepareStatement(SQL)){
            con.setAutoCommit(false);
            pstatement.setString(1, collection.getName());
            pstatement.setInt(2, collection.getCollectionId());
            pstatement.executeUpdate();
            con.commit();
            con.setAutoCommit(true);
            return true;
        } catch (SQLException e){
            log.error("isUpdateWordsCollection: ", e);
            try {
                con.rollback();
            } catch (SQLException e1) {
                log.error("isUpdateWordsCollection: con.rollback(): ", e1);
            }
        }
        return false;
    }

    public boolean isDeleteWordsCollection(int id){
        String SQL = "DELETE FROM words_collections WHERE collection_id = (?)";
        try(PreparedStatement pstatement = con.prepareStatement(SQL)){
            con.setAutoCommit(false);
            pstatement.setInt(1, id);
            pstatement.executeUpdate();
            con.commit();
            con.setAutoCommit(true);
            return true;
        } catch (SQLException e){
            log.error("isDeleteWordsCollection(): ", e);
            try {
                con.rollback();
            } catch (SQLException e1) {
                log.error("isDeleteWordsCollection(): con.rollback(): ", e1);
            }
        }
        return false;
    }

    public List<WordsCollection> getWordsCollections(){
        List<WordsCollection> collections = new ArrayList<>(0);
        String SQL = "SELECT * FROM words_collections";
        try(PreparedStatement pstatement = con.prepareStatement(SQL)){
            ResultSet result = pstatement.executeQuery();
            while (result.next()){
                collections.add(new WordsCollection(result.getInt("collection_id")
                        , result.getString("name")));
            }
            return collections;
        }catch (SQLException e){
            log.error("getWordsCollections(): ", e);
        }
        return collections;
    }
}
