package ru.aleksandrov.DAO;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.aleksandrov.Models.Role;
import ru.aleksandrov.Util.DBConnection;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoleDAO {
    private static Logger log = LogManager.getLogger(RoleDAO.class);
    private Connection con = null;

    public RoleDAO() throws PropertyVetoException, IOException, SQLException {
        DBConnection dbc = DBConnection.getInstance();
        con = dbc.getConnection();
    }

    public int isAddRole(Role role){
        int id = 0;
        String SQL = "INSERT INTO role (name) VALUES (?)";
        try(PreparedStatement pstatement = con.prepareStatement((SQL)
                , Statement.RETURN_GENERATED_KEYS)){
            con.setAutoCommit(false);
            pstatement.setString(1, role.getName());
            pstatement.executeUpdate();
            ResultSet generatedKeys = pstatement.getGeneratedKeys();
            if(generatedKeys.next()){
                id = generatedKeys.getInt("role_id");
            }
            con.commit();
            con.setAutoCommit(true);
            return id;
        } catch (SQLException e){
            log.error("isAddRole(): ", e);
            try {
                con.rollback();
            } catch (SQLException e1) {
                log.error("isAddRole(): con.rollback(): ", e1);
            }
        }
        return id;
    }

    public Role getRole(int id){
        Role role = null;
        String SQL = "SELECT * FROM role WHERE role_id = (?)";
        try(PreparedStatement pstatement = con.prepareStatement(SQL)){
            pstatement.setInt(1, id);
            ResultSet resultSet = pstatement.executeQuery();
            if (resultSet.next()){
                role = new Role(resultSet.getInt("role_id")
                        , resultSet.getString("name"));
                return role;
            }
        }catch (SQLException e){
            log.error("getRole(): ", e);
        }
        return role;
    }

    public boolean isUpdateRole(Role role){
        String SQL = "UPDATE FROM role SET name = (?) WHERE role_id = (?)";
        try(PreparedStatement pstatement = con.prepareStatement(SQL)){
            con.setAutoCommit(false);
            pstatement.setString(1, role.getName());
            pstatement.setInt(2, role.getRoleId());
            pstatement.executeUpdate();
            con.commit();
            con.setAutoCommit(true);
            return true;
        } catch (SQLException e){
            log.error("isUpdateRole(): ", e);
            try {
                con.rollback();
            } catch (SQLException e1) {
                log.error("isUpdateRole(): con.rollback(): ", e1);
            }
        }
        return false;
    }

    public boolean isDeleteRole(int id){
        String SQL = "DELETE FROM role WHERE role_id = (?)";
        try(PreparedStatement pstatement = con.prepareStatement(SQL)){
            con.setAutoCommit(false);
            pstatement.setInt(1, id);
            pstatement.executeUpdate();
            con.commit();
            con.setAutoCommit(true);
            return true;
        } catch (SQLException e){
            log.error("isDeleteRole(): ", e);
            try {
                con.rollback();
            } catch (SQLException e1) {
                log.error("isDeleteRole(): con.rollback(): ", e1);
            }
        }
        return false;
    }

    public List<Role> getRoles(){
        List<Role> roles = new ArrayList<>(0);
        String SQL = "SELECT * FROM role";
        try(PreparedStatement pstatement = con.prepareStatement(SQL)){
            ResultSet result = pstatement.executeQuery();
            while (result.next()){
                roles.add(new Role(result.getInt("role_id")
                        , result.getString("name")));
            }
            return roles;
        }catch (SQLException e){
            log.error("getRoles(): ", e);
        }
        return roles;
    }
}
