package ru.aleksandrov.Util;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.*;

public class DBConnection {
    private static DBConnection dbConnect = null;
    private ComboPooledDataSource cpds;

    private DBConnection() throws IOException, SQLException, PropertyVetoException {
        Settings settings = Settings.getSettings();
        cpds = new ComboPooledDataSource();
        cpds.setDriverClass(settings.value("driver"));
        cpds.setJdbcUrl(settings.value("urlDB"));
        cpds.setUser(settings.value("login"));
        cpds.setPassword(settings.value("password"));

        cpds.setInitialPoolSize(5);
        cpds.setMinPoolSize(5);
        cpds.setAcquireIncrement(5);
        cpds.setMaxPoolSize(30);
        cpds.setMaxStatements(180);
    }

    public static DBConnection getInstance() throws PropertyVetoException, SQLException, IOException {
        if (dbConnect == null){
            dbConnect = new DBConnection();
            return dbConnect;
        } else {
            return dbConnect;
        }
    }

    public Connection getConnection() throws SQLException {
        return this.cpds.getConnection();
    }
}
