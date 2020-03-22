package com.example.todolist;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.todoList);
        final TodoListAdapter todoListAdapter = new TodoListAdapter(MainActivity.this);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        recyclerView.setAdapter(todoListAdapter);
        ToDoViewModel toDoViewModel = ViewModelProviders.of(this).get(ToDoViewModel.class);

        toDoViewModel.getmAllRows().observe(this, new Observer<List<ToDoTable>>() {
            @Override
            public void onChanged(@Nullable final List<ToDoTable> todoRows) {
                // Update the cached copy of the words in the adapter.
                todoListAdapter.setWords(todoRows);
            }
        });


//        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        GetApi jsonPlaceHolderApi = retrofit.create(GetApi.class);
//
//        Call<List<TodoPojo>> listCall = jsonPlaceHolderApi.getTodoList();
//
//        listCall.enqueue(new Callback<List<TodoPojo>>() {
//            @Override
//            public void onResponse(@NonNull Call<List<TodoPojo>> call,@NonNull Response<List<TodoPojo>> response) {
//                if (response.isSuccessful()) {
//
//                    List<TodoPojo> todoList = response.body();
//
//
//                }
//
//            }
//
//            @Override
//            public void onFailure(@NonNull Call<List<TodoPojo>> call,@NonNull Throwable t) {
//
//            }
//        });



    }
}
