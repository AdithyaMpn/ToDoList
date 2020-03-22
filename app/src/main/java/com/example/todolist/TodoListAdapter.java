package com.example.todolist;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TodoListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{


    private AppCompatActivity appCompatActivity;
    private List<ToDoTable> todoData;

    public TodoListAdapter(AppCompatActivity appCompatActivity){


        this.appCompatActivity = appCompatActivity;
    }

    void setWords(List<ToDoTable> todoData){
        this.todoData = todoData;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLayout = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_todo, parent, false);
        return new VIHolder(itemLayout);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        ((VIHolder)holder).title.setText(todoData.get(position).getTitle());
        if(todoData.get(position).isCompleted()){
            ((VIHolder)holder).completed.setText("Completed");
        }else{
            ((VIHolder)holder).completed.setText("Not Completed");
        }
        ((VIHolder)holder).todo_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(appCompatActivity, TodoListActivity.class);
                intent.putExtra("id",position);
                appCompatActivity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (todoData != null)
            return todoData.size();
        else return 0;
    }

    private class VIHolder extends RecyclerView.ViewHolder {
        TextView title,completed;
        ConstraintLayout todo_layout;

        VIHolder(View itemLayoutView) {
            super(itemLayoutView);

            todo_layout = (ConstraintLayout) itemLayoutView.findViewById(R.id.todo_layout);
            title = (TextView) itemLayoutView.findViewById(R.id.title);
            completed = (TextView) itemLayoutView.findViewById(R.id.completed);



        }
    }
}
