package com.example.heathmonitor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView1;
    public static TaskAdapter adapter;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    ModelClass modelclass;
    Gson gson;



    Button button;
    String s1,s2,s3,s4;

    /**
     * Data Entry for the users and have
     * a highlights in main page
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button=findViewById(R.id.AddBUttonId);
        retrieveData();

        /**
         *
         */
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,DataEntry.class);
                startActivity(intent);
                //finish();

            }
        });
        //AddTask();
       // mcl.add(new ModelClass(s1,s2,s3,s4));
/*        //Toast.makeText(MainActivity.this,s1,Toast.LENGTH_LONG).show();
        mcl.add(new ModelClass("22-10-22","120","80","66"));
        mcl.add(new ModelClass("22-10-22","120","80","66"));*/
        recyclerView1=findViewById(R.id.recyclarView);
        adapter =new TaskAdapter(MainActivity.this, RecordList.mcl);
        recyclerView1.setAdapter(adapter);
        adapter.setClickListener(new TaskAdapter.ClickListener() {
            @Override
            public void customOnClick(int position, View v) {

            }

            @Override
            public void customOnLongClick(int position, View v) {

            }

            /**
             * Delete button to delete data
             * from main page list.
             * @param position
             */

            @Override
            public void onDeleteClick(int position) {

               // RecordList.mcl.remove(position);
                new RecordList().deleteRecord(position);
                adapter.notifyItemRemoved(position);
                saveData();
                Toast.makeText(MainActivity.this,"Delete Successful",Toast.LENGTH_SHORT).show();
            }

            /**
             * Edit button to edit data from
             * editing page.
             * @param position
             */

            @Override
            public void onEditClick(int position) {
                Intent intent = new Intent(MainActivity.this, UpdateActivity.class);
                intent.putExtra("index",position);
                startActivity(intent);
                //finish();

            }

            /**
             * goto the edit page on click the edit button.
             * @param position
             */

            @Override
            public void DetailClick(int position){
                //Toast.makeText(MainActivity.this,"clicked",Toast.LENGTH_SHORT);
                Intent intent1= new Intent(MainActivity.this,Details_Activity.class);
                intent1.putExtra("index",position);
                startActivity(intent1);
            }
        });



    }

/*    private void AddTask() {
       s1=getIntent().getStringExtra("date");
       s2=getIntent().getStringExtra("sys");
       s3=getIntent().getStringExtra("dia");
       s4=getIntent().getStringExtra("bp");


    }*/

    /**
     *save the data
     */

private void saveData()
{
    sharedPreferences = getSharedPreferences("jami",MODE_PRIVATE);
    editor = sharedPreferences.edit();
    gson = new Gson();
    String jsonString = gson.toJson(RecordList.mcl);
    editor.putString("jami",jsonString);
    editor.apply();
}

    /**
     * Retrieve from Shared preference.
     */
    private void retrieveData()
    {
        sharedPreferences = getSharedPreferences("jami",MODE_PRIVATE);
        gson = new Gson();
        String jsonString = sharedPreferences.getString("jami",null);
        Type type = new TypeToken<ArrayList<ModelClass>>(){}.getType();
        RecordList.mcl = gson.fromJson(jsonString,type);
        if(RecordList.mcl ==null)
        {
            RecordList.mcl = new ArrayList<>();
        }
//        mcl.clear();
//        saveData();
    }
}