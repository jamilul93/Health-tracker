package com.example.heathmonitor;


import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.sax.StartElementListener;
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
    ArrayList<ModelClass> mcl;
    ModelClass modelClass;
    EditText dateET,timeET,systolicET,diastolicET,heartRateET,commentET;
    String date, time, systolic,diastolic,bloodPressure,comment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        Intent intent = getIntent();
        int index = intent.getIntExtra("index",0);
        dateET= findViewById(R.id.UdateValue);
        timeET = findViewById(R.id.UtimeValue);
        systolicET = findViewById(R.id.UsystolicValue);
        diastolicET = findViewById(R.id.UdiastolicValue);
        heartRateET = findViewById(R.id.UheartRateValue);
        commentET = findViewById(R.id.UcommentValue);
        Button updateButton = findViewById( R.id.UpdateButtonId);
        retrieveData();
        modelClass = mcl.get(index);

        dateET.setText(modelClass.getDate());
        timeET.setText(modelClass.getTime());
        systolicET.setText(modelClass.getSystolic());
        diastolicET.setText(modelClass.getDiastolic());
        heartRateET.setText(modelClass.getBloodPressure());
        commentET.setText(modelClass.getComment());
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                date= dateET.getText().toString();
                time= timeET.getText().toString();
                systolic=systolicET.getText().toString();
                diastolic=diastolicET.getText().toString();
                bloodPressure =heartRateET.getText().toString();
                comment= commentET.getText().toString();
                modelClass = new ModelClass(date,time,systolic,diastolic,bloodPressure,comment);
                mcl.set(index,modelClass);
                PreferenceManager.getDefaultSharedPreferences(UpdateActivity.this).edit().clear().commit();
                saveData();
                //public ModelClass(String date, String time, String systolic, String diastolic, String bloodPressure, String comment) {
                MainActivity.mcl.set(index,modelClass);
                MainActivity.adapter.notifyDataSetChanged();
                //adapter.notifyItemChanged(index);
                Toast.makeText(UpdateActivity.this,"Update successful",Toast.LENGTH_SHORT).show();
                //startActivity(new Intent(UpdateActivity.this,MainActivity.class));
                finish();
            }

        });



    }


    private void retrieveData()
    {
        sharedPreferences = getSharedPreferences("jami",MODE_PRIVATE);
        gson = new Gson();
        String jsonString = sharedPreferences.getString("jami",null);
        Type type = new TypeToken<ArrayList<ModelClass>>(){}.getType();
        mcl = gson.fromJson(jsonString,type);
        if(mcl ==null)
        {
            mcl = new ArrayList<>();
        }
    }
    private void saveData()
    {
        sharedPreferences = getSharedPreferences("jami",MODE_PRIVATE);
        editor = sharedPreferences.edit();
        gson = new Gson();
        String jsonString = gson.toJson(mcl);
        editor.putString("jami",jsonString);
        editor.apply();
    }


}