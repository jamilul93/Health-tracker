package com.example.heathmonitor;

import java.util.ArrayList;
import java.util.List;

public class RecordList {

    public static ArrayList<ModelClass> mcl= new ArrayList<>();
    /**
     * This will  add
     */
    public void addRecord(ModelClass modelClass){
        if(mcl.contains(modelClass)){
            throw new IllegalArgumentException();
        }
        mcl.add(modelClass);


    }

}
