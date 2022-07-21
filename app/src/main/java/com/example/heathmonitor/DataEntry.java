package com.example.heathmonitor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class DataEntry extends AppCompatActivity {
   Button saveButton;
   String date,time,systolic,diastolic,bloodPressure,comment ;
   EditText edtx1,edtx2,edtx3,edtx4,edtx5,edtx6;
   ArrayList<ModelClass> jamiArray;
   SharedPreferences sharedPreferences;
   SharedPreferences.Editor editor;
   ModelClass modelclass;
   Gson gson;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_entry);

        saveButton=findViewById(R.id.addButton);
        edtx1=findViewById(R.id.dateValue);
        edtx2=findViewById(R.id.systolicValue);
        edtx3= findViewById(R.id.diastolicValue);
        edtx4 =findViewById(R.id.heartRateValue);
        edtx5=findViewById(R.id.dateValue);
        edtx6=findViewById(R.id.commentValue);

        retrieveData();

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(DataEntry.this,"button clicked",Toast.LENGTH_LONG).show();
                inputFormat();




                  //  public ModelClass(String date, String systolic, String diastolic, String bloodPressure) {



/*                private void saveData(date,systolic,diastolic,bloodPressure) {
                    SharedPreferences sharedPreferences= getApplicationContext().getSharedPreferences("Boold pressure",MODE_PRIVATE);
                    SharedPreferences.Editor editor =sharedPreferences.edit();
                    Gson gson =new Gson();
                    // arrayList.add(new ModelClass(name,Integer.parseInt(systolic),Integer.parseInt(diastolic) ));
                    String json = gson.toJson(arrayList);
                    editor.putString("data",json);
                    editor.apply();
                    tx1.setText("list data\n");
                    loadData();


                }*/




                //Intent intent1=new Intent(DataEntry.this,MainActivity.class);
                //startActivity(intent1);
                //intent1.putExtra("date",date);
               // intent1.putExtra("sys",systolic);
                //intent1.putExtra("dia",diastolic);
                //intent1.putExtra("bp",bloodPressure);

            }
        });
    }

    private void inputFormat() {
        if ((Integer.parseInt(edtx2.getText().toString()) >= 0) && (Integer.parseInt(edtx2.getText().toString()) <= 200)) {
            if ((Integer.parseInt(edtx3.getText().toString()) >= 0) && (Integer.parseInt(edtx3.getText().toString()) <= 150)) {
                if ((Integer.parseInt(edtx4.getText().toString()) >= 0) && (Integer.parseInt(edtx4.getText().toString())<=150)) {
                    Toast.makeText(DataEntry.this,"HI",Toast.LENGTH_LONG).show();
                    date= edtx1.getText().toString();
                    time=edtx5.getText().toString();
                    systolic=edtx2.getText().toString();
                    diastolic=edtx3.getText().toString();
                    bloodPressure =edtx4.getText().toString();
                    comment= edtx6.getText().toString();
                    modelclass = new ModelClass(date,time,systolic,diastolic,bloodPressure,comment);
                    jamiArray.add(modelclass);
                    PreferenceManager.getDefaultSharedPreferences(DataEntry.this).edit().clear().commit();
                    saveData();

                    startActivity(new Intent(DataEntry.this,MainActivity.class));

                }
                else{
                    Toast.makeText(DataEntry.this,"Invalid data format added",Toast.LENGTH_LONG).show();

                }

            }
            else {
                Toast.makeText(DataEntry.this,"Invalid data format added",Toast.LENGTH_LONG).show();
            }
        }
        else{
            Toast.makeText(DataEntry.this,"Invalid data format added",Toast.LENGTH_LONG).show();
        }
    }

    private void retrieveData()
    {
        sharedPreferences = getSharedPreferences("shared preference",MODE_PRIVATE);
        gson = new Gson();
        String jsonString = sharedPreferences.getString("jami",null);
        Type type = new TypeToken<ArrayList<ModelClass>>(){}.getType();
        jamiArray = gson.fromJson(jsonString,type);
        if(jamiArray ==null)
        {
            jamiArray = new ArrayList<>();
        }
    }
    private void saveData()
    {
        sharedPreferences = getSharedPreferences("shared preference",MODE_PRIVATE);
        editor = sharedPreferences.edit();
        gson = new Gson();
        String jsonString = gson.toJson(jamiArray);
        editor.putString("jami",jsonString);
        editor.apply();
    }
}











