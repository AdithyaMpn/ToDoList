package com.example.todolist;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import java.util.List;

public class TodoListActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_list);
        final ToDoViewModel toDoViewModel = ViewModelProviders.of(this).get(ToDoViewModel.class);
        final int id = (int) getIntent().getExtras().get("id");


        toDoViewModel.getmAllRows().observe(this, new Observer<List<ToDoTable>>() {
            @Override
            public void onChanged(@Nullable final List<ToDoTable> todoRows) {
                // Update the cached copy of the words in the adapter.
                if (todoRows != null) {
                    TextView note_title = findViewById(R.id.note_title);
                    TextView note_id = findViewById(R.id.note_id);
                    EditText body = findViewById(R.id.body);
                    body.setText(todoRows.get(id).getBody());
                    note_id.setText(getResources().getString(R.string.noteid, todoRows.get(id).getId()));
                    note_title.setText(todoRows.get(id).getTitle());
                }
            }
        });

        Button cancel = findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Button save = findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText body = findViewById(R.id.body);
                toDoViewModel.update(id+1, body.getText().toString());
                finish();
            }
        });

    }

}
