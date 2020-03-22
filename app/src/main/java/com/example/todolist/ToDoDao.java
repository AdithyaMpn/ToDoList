package com.example.todolist;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ToDoDao {

    @Insert
    void insert(ToDoTable todorow);

    @Query("UPDATE todo_table SET body = :body WHERE id = :id")
    int update(int id, String body);

    @Query("SELECT * from todo_table")
    LiveData<List<ToDoTable>> getAllTodoRow();
}
