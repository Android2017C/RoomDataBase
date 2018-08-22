package com.example.sreenureddy.roomdatabasec;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.sreenureddy.roomdatabasec.AddTask.AddTaskActivity;
import com.example.sreenureddy.roomdatabasec.Entity.Task;
import com.example.sreenureddy.roomdatabasec.SingleInstanceForDatabase.DatabaseClient;

import java.util.List;

/*What is Room?
        The Room persistence library provides an abstraction layer over SQLite to allow for more robust database access while harnessing the full power of SQLite.

        Basically, with the help of room we can quickly create sqlite databases and perform the operations like create, read, update and delete. Room makes everything very easy and quick.

        Components of Room
        We have 3 components of room.

        Entity: Instead of creating the SQLite table, we will create the Entity. Entity is nothing but a model class annotated with @Entity. The variables of this class is our columns, and the class is our table.
        Database: It is an abstract class where we define all our entities.
        DAO: Stands for Data Access Object. It is an interface that defines all the operations that we need to perform in our database.*/
public class MainActivity extends AppCompatActivity {

    private FloatingActionButton buttonAddTask;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerview_tasks);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        buttonAddTask = findViewById(R.id.floating_button_add);
        buttonAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddTaskActivity.class);
                startActivity(intent);
            }
        });


        getTasks();

    }
    private void getTasks() {

        class GetTasks extends AsyncTask<Void, Void, List<Task>> {
            @Override
            protected List<Task> doInBackground(Void... voids) {
                List<Task> taskList = DatabaseClient
                        .getInstance(getApplicationContext())
                        .getAppDatabase()
                        .taskDao()
                        .getAll();
                return taskList;
            }

            @Override
            protected void onPostExecute(List<Task> tasks) {
                super.onPostExecute(tasks);
            }
        }


    }

}
