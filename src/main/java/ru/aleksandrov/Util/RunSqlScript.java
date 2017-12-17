package ru.aleksandrov.Util;

import java.beans.PropertyVetoException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class RunSqlScript {
    //TODO fix script loading
    public boolean scriptRunning(){
        Connection con = null;
        try {
            DBConnection dbConnection = DBConnection.getInstance();
            con = dbConnection.getConnection();
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ScriptRunner runner = new ScriptRunner(con, false, false);
        String file = "/create.sql";
        try (FileReader reader = new FileReader(file)) {
            runner.runScript(new BufferedReader(reader));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
}
