package ru.aleksandrov.Util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.beans.PropertyVetoException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class RunSqlScript {
    private static final Logger log = LogManager.getLogger(RunSqlScript.class);
    public boolean scriptRunning(String path){
        Connection con = null;
        try (FileReader reader = new FileReader(path)) {
            DBConnection dbConnection = DBConnection.getInstance();
            con = dbConnection.getConnection();
            ScriptRunner runner = new ScriptRunner(con, false, false);
            runner.runScript(new BufferedReader(reader));
        } catch (PropertyVetoException e) {
            log.error("scriptRunning(): ", e);
        } catch (IOException e) {
            log.error("scriptRunning: ", e);
        } catch (SQLException e) {
            log.error("scriptRunning(): ", e);
        }
        return true;
    }
}
