package com.example.heathmonitor;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

public class Utils {
    public  void saveData(Context context)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences("jami",context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String jsonString = gson.toJson(RecordList.mcl);
        editor.putString("jami",jsonString);
        editor.apply();
    }
}
