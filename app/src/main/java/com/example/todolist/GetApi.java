package com.example.todolist;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetApi {

    @GET("todos")
    Call<List<TodoPojo>> getTodoList();

}
