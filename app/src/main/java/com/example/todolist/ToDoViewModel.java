package com.example.todolist;


import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ToDoViewModel extends AndroidViewModel {

    private ToDoRepository mRepository;

    private LiveData<List<ToDoTable>> mAllRows;

    public ToDoViewModel(Application application) {
        super(application);
        mRepository = new ToDoRepository(application);
        mAllRows = mRepository.getmAllRows();
    }

    LiveData<List<ToDoTable>> getmAllRows() {
        return mAllRows;
    }

    public void insert(ToDoTable row) {
        mRepository.insert(row);
    }

    public void update(int id , String body) {
        mRepository.update(id, body);
    }

}