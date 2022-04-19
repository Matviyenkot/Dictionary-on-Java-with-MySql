package com.company.DictionaryOnDataBase;

import java.sql.*;
import java.util.Scanner;


public class Dictionary {


    void showDictionary(Statement statement){

        String querry = "select * from words2_0";

        try {
            ResultSet resultSet = statement.executeQuery(querry);

            while(resultSet.next()){
                int id;
                String Ukrainian;
                String English;

                id = resultSet.getInt("id");
                Ukrainian = resultSet.getString("Ukrainian");
                English = resultSet.getString("English");

                dictionaryOut dictionaryOut = new dictionaryOut(id, Ukrainian, English);

                System.out.println(dictionaryOut);
            }
            System.out.println();
        }
        catch (SQLException sqle){
            System.out.println("Something was wrong!");
            System.out.println(sqle);
        }

    }

    private String askToInputString(String question){
        System.out.print(question);
        Scanner scanner = new Scanner(System.in);

//        String answer = scanner.nextLine();

        return scanner.nextLine();
    }

    void putWordsInDictionary(Statement statement){

        String wordOnUkrainian = askToInputString(
                "Please enter word on Ukrainian or '0' to get back in main menu: ");
        if(wordOnUkrainian.equals("0")){
            System.out.println();
            return;
        }
        String wordOnEnglish = askToInputString(
                "Please enter word on English or '0' to get back in main menu: ");

        if( wordOnEnglish.equals("0")){
            System.out.println();
            return;
        }

        try {
            if(wordOnUkrainian.length()>1 && wordOnEnglish.length()>1){

                statement.execute("insert into words2_0 (Ukrainian, English) value "
                        + "('" + wordOnUkrainian + "','" + wordOnEnglish + "')");

                System.out.println("Ukrainian word " + wordOnUkrainian + " was added to Dictionary");
                System.out.println("English word " + wordOnEnglish + " was added to Dictionary");
                System.out.println();
            } else{
                System.out.println("Please enter the word!");
                putWordsInDictionary(statement);
            }
        }
        catch (SQLException sqle){
            System.out.println("Something was wrong!");
            System.out.println(sqle);
        }


    }


    void deleteWordFromDictionary(Statement statement, int delete){

        String querry = "select * from words2_0";

        try {
            ResultSet resultSet = statement.executeQuery(querry);

            if(!resultSet.next()){
                System.out.println("No words in Dictionary yet!");
                System.out.println();
                return;
            }

            if(delete == 2){
                String strID = askToInputString(
                        "Please put the id of word which you want to delete from Dictionary or '0' to get back in main menu: ");
                try{
                    int id = Integer.parseInt(strID);
                    if(id == 0){
                        System.out.println();
                        return;
                    }
                    statement.execute("delete from words2_0 where id =" + id);
                }
                catch (NumberFormatException e){
                    deleteWordFromDictionary(statement,delete);
                }

            }
            else if(delete == 3){
                statement.execute("delete from words2_0");
                System.out.println("All words were deleted from Dictionary!");
            }

            System.out.println();

        }
        catch (SQLException sqle){
            System.out.println("Something was wrong!");
            System.out.println(sqle);
        }
    }

    void updateWord(Connection connection){

        try{
            String idUpdate = askToInputString(
                    "Please put the id of word which you want to update in Dictionary or '0' to get back in main menu: ");
            int id = Integer.parseInt(idUpdate);

            //give an opportunity to cancel the action and get back in main menu
            if(id == 0){
                System.out.println();
                return;
            }

            String wordOnUkrainian = askToInputString(
                    "Please enter new word on Ukrainian which you want to update: ");
            String wordOnEnglish = askToInputString(
                    "Please enter new word on English which you want to update: ");

            if(wordOnUkrainian.length()>1 && wordOnEnglish.length()>1){

                String query = "update words2_0 set Ukrainian = ?, English = ? where id = ?";


                PreparedStatement preparedStmt = connection.prepareStatement(query);
                preparedStmt.setString(1, wordOnUkrainian);
                preparedStmt.setString(2, wordOnEnglish);
                preparedStmt.setInt(3, id);

                preparedStmt.executeUpdate();

                System.out.println("Ukrainian word " + wordOnUkrainian + " was update in Dictionary");
                System.out.println("English word " + wordOnEnglish + " was update in Dictionary");
                System.out.println();
            } else{
                System.out.println("Please enter the words!");
            }

        }
        catch (NumberFormatException e){
            updateWord(connection);
        }
        catch (SQLException sqle){
            System.out.println("Something was wrong!");
            System.out.println(sqle);
        }

    }

}
