package ru.aleksandrov.DAO;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.aleksandrov.Entity.Role;
import ru.aleksandrov.Entity.User;
import ru.aleksandrov.util.DBConnection;
import ru.aleksandrov.util.Settings;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private static final Logger log = LogManager.getLogger(RoleDAO.class.getName());
    private Connection con = null;

    public UserDAO() throws PropertyVetoException, IOException, SQLException {
        DBConnection dbc = DBConnection.getInstance();
        con = dbc.getConnection();
    }

    public boolean isAddUser(User user){
        String SQL = "INSERT INTO users (name, login, password, email, role_id) VALUES (?, ?, ?, ?, ?)";
        try(PreparedStatement pstatement = con.prepareStatement((SQL)
                , Statement.RETURN_GENERATED_KEYS)){
            con.setAutoCommit(false);
            pstatement.setString(1, user.getName());
            pstatement.setString(2, user.getLogin());
            pstatement.setString(3, user.getPassword());
            pstatement.setString(4, user.getEmail());
            pstatement.setInt(5, user.getRole().getRoleId());
            pstatement.executeUpdate();
            con.commit();
            con.setAutoCommit(true);
            return true;
        } catch (SQLException e){
            log.error("isAddUser(): ", e);
            try {
                con.rollback();
            } catch (SQLException e1) {
                log.error("isAddUser(): con.rollback(): ", e1);
            }
        }
        return false;
    }

    public User getUser(int id){
        User user = null;
        String SQL = "SELECT users.user_id AS user_id, users.name AS name" +
                ", users.login AS login, users.password AS password" +
                ", users.email AS email, role.role_id AS role_id" +
                ", role.name AS role_name FROM users LEFT OUTER JOIN role ON" +
                " users.role_id = role.role_id WHERE user_id = (?)";
        try(PreparedStatement pstatement = con.prepareStatement(SQL)){
            pstatement.setInt(1, id);
            ResultSet resultSet = pstatement.executeQuery();
            if (resultSet.next()){
                user = new User(resultSet.getInt("user_id")
                        , resultSet.getString("name")
                        , resultSet.getString("login")
                        , resultSet.getString("password")
                        , resultSet.getString("email")
                        , new Role(resultSet.getInt("role_id")
                        , resultSet.getString("role_name")));
                return user;
            }
        }catch (SQLException e){
            log.error("getRole(): ", e);
        }
        return user;
    }

    public boolean isUpdateUser(User user){
        String SQL = "UPDATE FROM users SET name = (?), login = (?)" +
                ", password = (?), email = (?), role_id = (?) WHERE user_id = (?)";
        try(PreparedStatement pstatement = con.prepareStatement(SQL)){
            con.setAutoCommit(false);
            pstatement.setString(1, user.getName());
            pstatement.setString(2, user.getLogin());
            pstatement.setString(3, user.getPassword());
            pstatement.setString(4, user.getEmail());
            pstatement.setInt(5, user.getRole().getRoleId());
            pstatement.setInt(6, user.getUserId());
            pstatement.executeUpdate();
            con.commit();
            con.setAutoCommit(true);
            return true;
        } catch (SQLException e){
            log.error("isUpdateUser(): ", e);
            try {
                con.rollback();
            } catch (SQLException e1) {
                log.error("isUpdateUser(): con.rollback(): ", e1);
            }
        }
        return false;
    }

    public boolean isDeleteUser(int id){
        String SQL = "DELETE FROM users WHERE user_id = (?)";
        try(PreparedStatement pstatement = con.prepareStatement(SQL)){
            con.setAutoCommit(false);
            pstatement.setInt(1, id);
            pstatement.executeUpdate();
            con.commit();
            con.setAutoCommit(true);
            return true;
        } catch (SQLException e){
            log.error("isDeleteUser(): ", e);
            try {
                con.rollback();
            } catch (SQLException e1) {
                log.error("isDeleteUser(): con.rollback(): ", e1);
            }
        }
        return false;
    }

    public List<User> getUsers(){
        List<User> users = new ArrayList<>(0);
        String SQL = "SELECT users.user_id AS user_id, users.name AS name" +
                ", users.login AS login, users.password AS password" +
                ", users.email AS email, users.role_id AS role_id" +
                ", role.name AS role_name FROM users LEFT OUTER JOIN role" +
                " ON users.role_id = role.role_id";
        try(PreparedStatement pstatement = con.prepareStatement(SQL)){
            ResultSet result = pstatement.executeQuery();
            while (result.next()){
                users.add(new User(result.getInt("user_id"), result.getString("name")
                        , result.getString("login"), result.getString("password")
                        , result.getString("email")
                        , new Role(result.getInt("role_id"), result.getString("role_name"))));
            }
        return users;
        }catch (SQLException e){
            log.error("getUsers(): ", e);
        }
        return users;
    }
    
    public User verifyUser(User user){String login = user.getLogin();
        String password = user.getPassword();
        String SQL = "SELECT users.user_id AS user_id, users.name AS name" +
                ", users.login AS login, users.password AS password" +
                ", users.email AS email, role.role_id AS role_id" +
                ", role.name AS role_name FROM users LEFT OUTER JOIN role ON" +
                " users.role_id = role.role_id WHERE login = (?)";
        try(PreparedStatement pstatement = con.prepareStatement(SQL)){
            pstatement.setString(1, login);
            ResultSet result = pstatement.executeQuery();
            if(result.next()){
                User verifiedUser = new User(result.getInt("user_id")
                        , result.getString("name"), result.getString("login")
                        , result.getString("password"), result.getString("email")
                        , new Role(result.getInt("role_id"), result.getString("role_name")));
                if(verifiedUser.getLogin().equals(login) && verifiedUser.getPassword().equals(password)){
                    return verifiedUser;
                }
            }
            return user;
        }catch(SQLException e){
            log.error("verifyUser(): ", e);
            e.printStackTrace();
        }
        return user;
    }
}