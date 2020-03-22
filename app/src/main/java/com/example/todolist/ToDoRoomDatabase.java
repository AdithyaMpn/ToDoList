package com.example.todolist;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Database(entities = {ToDoTable.class}, version = 1, exportSchema = false)
public abstract class ToDoRoomDatabase extends RoomDatabase {

    private static ToDoRoomDatabase INSTANCE;
    private static Context contextt;
    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {

        private List<TodoPojo> todoData;

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            // If you want to keep the data through app restarts,
            // comment out the following line.
            final SharedPreferences sharedPreferences = contextt.getSharedPreferences("todo", Context.MODE_PRIVATE);

            if (sharedPreferences.getBoolean("first", true)) {

            Retrofit retrofit = new Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            GetApi jsonPlaceHolderApi = retrofit.create(GetApi.class);



                Call<List<TodoPojo>> listCall = jsonPlaceHolderApi.getTodoList();

                listCall.enqueue(new retrofit2.Callback<List<TodoPojo>>() {
                    @Override
                    public void onResponse(@NonNull Call<List<TodoPojo>> call, @NonNull Response<List<TodoPojo>> response) {
                        if (response.isSuccessful()) {

                            todoData = response.body();
                            sharedPreferences.edit().putBoolean("first", false).apply();
                            new PopulateDbAsync(INSTANCE, todoData).execute();
                        }

                    }

                    @Override
                    public void onFailure(@NonNull Call<List<TodoPojo>> call, @NonNull Throwable t) {

                    }
                });

            }


        }
    };

    static ToDoRoomDatabase getDatabase(final Context context) {
        contextt = context;
        if (INSTANCE == null) {
            synchronized (ToDoRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            ToDoRoomDatabase.class, "word_database")
                            // Wipes and rebuilds instead of migrating
                            // if no Migration object.
                            // Migration is not part of this practical.
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public abstract ToDoDao toDoDao();

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final ToDoDao mDao;
        private List<TodoPojo> todoData;


        PopulateDbAsync(ToDoRoomDatabase db, List<TodoPojo> todoData) {
            mDao = db.toDoDao();
            this.todoData = todoData;
        }

        @Override
        protected Void doInBackground(final Void... params) {
            // Start the app with a clean database.
            // Not needed if you only populate on creation.

            for (int i = 0; i <= todoData.size() - 1; i++) {
                ToDoTable word = new ToDoTable(todoData.get(i).getId(), todoData.get(i).getUserId(), todoData.get(i).getTitle(), todoData.get(i).isCompleted(),"");
                mDao.insert(word);
            }
            return null;
        }
    }

}

