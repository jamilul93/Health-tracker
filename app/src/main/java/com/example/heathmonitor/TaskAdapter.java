package com.example.heathmonitor;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewholder> {
    private  Context mContext;
    private ArrayList<ModelClass> mclass;
    public  TaskAdapter(Context context, ArrayList<ModelClass>mclass) {
        this.mclass= mclass;
        this.mContext = context;

    }
    class TaskViewholder extends RecyclerView.ViewHolder{
        TextView tx1,tx2,tx3,tx4;
        Button UpdateButton;

        public TaskViewholder(@NonNull View itemView) {
            super(itemView);
            tx1= itemView.findViewById(R.id.tvDate);
            tx2= itemView.findViewById(R.id.tvDiastolic);
            tx3=itemView.findViewById(R.id.tvSystolic);
            tx4=itemView.findViewById(R.id.tvHeartRate);
            UpdateButton = itemView.findViewById(R.id.UpdateButtonId);
        }
    }

    @NonNull
    @Override
    public TaskViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(parent.getContext())  ;
        View view= inflater.inflate(R.layout.singlerow,parent,false);
        return new TaskViewholder (view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskAdapter.TaskViewholder holder, @SuppressLint("RecyclerView") int position) {
        holder.tx1.setText(mclass.get(position).getDate());
        holder.tx2.setText(mclass.get(position).getSystolic());
        holder.tx3.setText(mclass.get(position).getDiastolic());
        holder.tx4.setText(mclass.get(position).getBloodPressure());
        holder.UpdateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, UpdateActivity.class);
                intent.putExtra("index",position);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mclass.size() ;
    }
}
