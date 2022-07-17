package com.example.heathmonitor;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewholder> {
    private ArrayList<ModelClass> mclass;
    public  TaskAdapter(ArrayList<ModelClass>mclass) {
        this.mclass= mclass;

    }
    class TaskViewholder extends RecyclerView.ViewHolder{
        TextView tx1,tx2,tx3,tx4;

        public TaskViewholder(@NonNull View itemView) {
            super(itemView);
            tx1= itemView.findViewById(R.id.tvDate);
            tx2= itemView.findViewById(R.id.tvDiastolic);
            tx3=itemView.findViewById(R.id.tvSystolic);
            tx4=itemView.findViewById(R.id.tvHeartRate);
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
    public void onBindViewHolder(@NonNull TaskViewholder holder, int position) {
        holder.tx1.setText(mclass.get(position).getDate());
        holder.tx2.setText(mclass.get(position).getSystolic());
        holder.tx3.setText(mclass.get(position).getDiastolic());
        holder.tx4.setText(mclass.get(position).getBloodPressure());
    }

    @Override
    public int getItemCount() {
        return mclass.size() ;
    }
}
