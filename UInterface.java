package com.company.DictionaryOnDataBase;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UInterface {

    private  int numOfAction;

    public  int getNumOfAction() {
        return numOfAction;
    }

    public  void setNumOfAction(int numOfAction) {
        this.numOfAction = numOfAction;
    }

     void showActions(){
        System.out.println("1 Add new word to Dictionary");
        System.out.println("2 Delete word from Dictionary");
        System.out.println("3 Delete all words from Dictionary");
        System.out.println("4 Update word from Dictionary");
        System.out.println("5 Show Dictionary");
        System.out.println("0 Exit");
        choseAction();
    }

    private  void choseAction(){
        System.out.print("Please chose the action: ");

        try{
            Scanner scanner = new Scanner(System.in);
            int action = scanner.nextInt();

            if(action>=0 && action<=5){
                setNumOfAction(action);
            }
            else
                choseAction();
        }catch (InputMismatchException e){
            choseAction();
        }

    }
}

