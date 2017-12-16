package ru.aleksandrov.Util;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.SQLException;

public class RunSqlScript {
    String aSQLScriptFilePath = "path/to/sql/script.sql";

    public RunSqlScript(){
        try {
            DBConnection dbConnection = DBConnection.getInstance();
            Connection con = dbConnection.getConnection();
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
