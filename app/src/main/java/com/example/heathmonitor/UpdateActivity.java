package com.example.heathmonitor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class UpdateActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Gson gson;
    ArrayList<ModelClass> recordsArrayList;
    ModelClass modelClass;
    EditText date,time,systolic,diastolic,heartRate,comment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        Intent intent = getIntent();
        int index = intent.getIntExtra("index",0);
        date= findViewById(R.id.UdateValue);
        time = findViewById(R.id.UtimeValue);
        systolic = findViewById(R.id.UsystolicValue);
        diastolic = findViewById(R.id.diastolicValue);
        heartRate = findViewById(R.id.heartRateValue);
        comment = findViewById(R.id.commentValue);
        Button updateButton = findViewById( R.id.UpdateButtonId);
        retrieveData();
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PreferenceManager.getDefaultSharedPreferences(UpdateActivity.this).edit().clear().commit();
                saveData();
            }

        });
        modelClass = recordsArrayList.get(index);

        date.setText(""+modelClass.getDate());
        time.setText(""+modelClass.getTime());
        systolic.setText(""+modelClass.getSystolic());
        diastolic.setText(""+modelClass.getDiastolic());
        heartRate.setText(""+modelClass.getBloodPressure());
        comment.setText(""+modelClass.getComment());


    }


        private void retrieveData()
        {
            sharedPreferences = getSharedPreferences("shared",MODE_PRIVATE);
            gson = new Gson();
            String jsonString = sharedPreferences.getString("record",null);
            Type type = new TypeToken<ArrayList<ModelClass>>(){}.getType();
            recordsArrayList = gson.fromJson(jsonString,type);
            if(recordsArrayList ==null)
            {
                recordsArrayList = new ArrayList<>();
            }
        }
    private void saveData()
    {
        sharedPreferences = getSharedPreferences("shared",MODE_PRIVATE);
        editor = sharedPreferences.edit();
        gson = new Gson();
        String jsonString = gson.toJson(recordsArrayList);
        editor.putString("record",jsonString);
        editor.apply();
    }


    }
