package com.company.DictionaryOnDataBase;

public class dictionaryOut {
    private int id;
    private String Ukrainian;
    private String English;

    public dictionaryOut(int id, String Ukrainian, String English){
        this.id = id;
        this.Ukrainian = Ukrainian;
        this.English = English;
    }

    @Override
    public String toString(){
        return "id: " + id + "\t" + "Word on Ukrainian: " + Ukrainian + "\t"
                + "Word on English: " + English;
    }
}
