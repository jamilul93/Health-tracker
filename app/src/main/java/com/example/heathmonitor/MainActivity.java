package com.example.heathmonitor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView1;
    private TaskAdapter adapter;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    ModelClass modelclass;
    Gson gson;

    Button button;
    String s1,s2,s3,s4;
    private ArrayList<ModelClass> mcl= new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button=findViewById(R.id.AddBUttonId);
        retrieveData();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,DataEntry.class);
                startActivity(intent);

            }
        });
        //AddTask();
       // mcl.add(new ModelClass(s1,s2,s3,s4));
/*        //Toast.makeText(MainActivity.this,s1,Toast.LENGTH_LONG).show();
        mcl.add(new ModelClass("22-10-22","120","80","66"));
        mcl.add(new ModelClass("22-10-22","120","80","66"));*/
        recyclerView1=findViewById(R.id.recyclarView);
        adapter =new TaskAdapter(mcl);
        recyclerView1.setAdapter(adapter);
    }

/*    private void AddTask() {
       s1=getIntent().getStringExtra("date");
       s2=getIntent().getStringExtra("sys");
       s3=getIntent().getStringExtra("dia");
       s4=getIntent().getStringExtra("bp");


    }*/
    private void retrieveData()
    {
        sharedPreferences = getSharedPreferences("shared preference",MODE_PRIVATE);
        gson = new Gson();
        String jsonString = sharedPreferences.getString("jami",null);
        Type type = new TypeToken<ArrayList<ModelClass>>(){}.getType();
        mcl = gson.fromJson(jsonString,type);
        if(mcl ==null)
        {
            mcl = new ArrayList<>();
        }
    }
}