package com.company.DictionaryOnDataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection{
    private Connection connection;

    public Connection getConnection(String Url, String username, String password) throws SQLException {
        if(connection != null){
            return connection;
        }

        connection = DriverManager.getConnection(Url,username, password);
        return connection;
    }

}
