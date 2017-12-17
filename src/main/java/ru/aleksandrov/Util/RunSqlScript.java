package ru.aleksandrov.Util;

import java.beans.PropertyVetoException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class RunSqlScript {
    public boolean scriptRunning(String path){
        Connection con = null;
        try (FileReader reader = new FileReader(path)) {
            DBConnection dbConnection = DBConnection.getInstance();
            con = dbConnection.getConnection();
            ScriptRunner runner = new ScriptRunner(con, false, false);
            runner.runScript(new BufferedReader(reader));
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
}
