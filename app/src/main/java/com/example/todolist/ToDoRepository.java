package com.example.todolist;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class ToDoRepository {

    private ToDoDao toDoDao;
    private LiveData<List<ToDoTable>> mAllRows;

    ToDoRepository(Application application) {
        ToDoRoomDatabase db = ToDoRoomDatabase.getDatabase(application);
        toDoDao = db.toDoDao();
        mAllRows = toDoDao.getAllTodoRow();
    }

    LiveData<List<ToDoTable>> getmAllRows() {
        return mAllRows;
    }

    public void insert (ToDoTable todorow) {
        new insertAsyncTask(toDoDao).execute(todorow);
    }
    public void update (int id, String body) {
        new updateAsyncTask(id, body, toDoDao).execute();
    }


    private static class insertAsyncTask extends AsyncTask<ToDoTable, Void, Void> {

        private ToDoDao mAsyncTaskDao;

        insertAsyncTask(ToDoDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final ToDoTable... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private static class updateAsyncTask extends AsyncTask<Void, Void, Void> {

        private ToDoDao mAsyncTaskDao;
        private int id;
        private String body;

        updateAsyncTask(int id, String body, ToDoDao dao) {
            mAsyncTaskDao = dao;
            this.id = id;
            this.body = body;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mAsyncTaskDao.update(id,body);
            return null;
        }
    }
}

