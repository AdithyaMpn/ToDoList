package com.example.todolist;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "todo_table")
public class ToDoTable {

    @PrimaryKey
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "userId")
    private int userId;

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "completed")
    private boolean completed;

    @ColumnInfo(name = "body")
    private String body;



    public ToDoTable(int id, int userId, String title, boolean completed, String body)
    {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.completed = completed;
        this.body = body;
    }


    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public String getTitle() {
        return title;
    }

    public boolean isCompleted() {
        return completed;
    }

    public String getBody() {
        return body;
    }




}
