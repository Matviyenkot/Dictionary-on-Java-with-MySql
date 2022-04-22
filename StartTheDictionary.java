package com.company.DictionaryOnDataBase;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

//database on mysql
// name Dictionary table words2_0
//fields: id(AI), Ukrainian(null), English(null)

public class StartTheDictionary {
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "root";
    private static final String CONNECTION_URL = "jdbc:mysql://localhost:8889/Dictionary";


    public static void main(String[] args){

        DBConnection dbConnection = new DBConnection();

        try (Connection connection = dbConnection.getConnection(CONNECTION_URL, USER_NAME, PASSWORD);
             Statement statement = connection.createStatement()){

            UInterface UI = new UInterface();
            Dictionary dictionary = new Dictionary();

            Boolean working  = true;

            while(working){
                UI.showActions();
                switch (UI.getNumOfAction()){
                    case 1: dictionary.putWordsInDictionary(statement);
                        break;
                    case 2: dictionary.deleteWordFromDictionary(statement, UI.getNumOfAction());
                        break;
                    case 3: dictionary.deleteWordFromDictionary(statement, UI.getNumOfAction());
                        break;
                    case 4: dictionary.updateWord(connection);
                        break;
                    case 5: dictionary.showDictionary(statement);
                        break;
                    case 0: working = false;
                        break;
                }
            }

        }
        catch (SQLException sqle){
            System.out.println("Something was wrong!");
            System.out.println(sqle);
        }


    }
}
