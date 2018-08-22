package com.example.sreenureddy.roomdatabasec.AddTask;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sreenureddy.roomdatabasec.Entity.Task;
import com.example.sreenureddy.roomdatabasec.MainActivity;
import com.example.sreenureddy.roomdatabasec.R;
import com.example.sreenureddy.roomdatabasec.SingleInstanceForDatabase.DatabaseClient;

public class AddTaskActivity extends AppCompatActivity{

    private EditText editTextTask,editTextDesc,editTextFinishBy;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        editTextTask = findViewById(R.id.editTextTask);
        editTextDesc = findViewById(R.id.editTextDesc);
        editTextFinishBy = findViewById(R.id.editTextFinishBy);
        findViewById(R.id.button_save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveTask();
            }
        });

    }
    private void saveTask()
    {
       final String stask=editTextTask.getText().toString().trim();
        final String sdesc=editTextDesc.getText().toString().trim();
        final String sfinishby=editTextFinishBy.getText().toString().trim();

     if(stask.isEmpty())
     {
         editTextTask.setError("Task Required");
         editTextTask.requestFocus();
         return;
     }

     if(sdesc.isEmpty())
     {
         editTextDesc.setError("Desc Required");
         editTextDesc.requestFocus();
         return;
     }

     if(sfinishby.isEmpty())
     {
         editTextFinishBy.setError("Finish By Required");
         editTextFinishBy.requestFocus();
         return;
     }

     class SaveTask extends AsyncTask<Void,Void,Void>
     {

         @Override
         protected Void doInBackground(Void... voids) {
             //creating Task
             Task insert=new Task();
             insert.setTask(stask);
             insert.setDesc(sdesc);
             insert.setFinishBy(sfinishby);
             insert.setFinished(false);
             //adding to database
             DatabaseClient.getInstance(getApplication()).getAppDatabase().taskDao().insert(insert);

             return null;
         }

         @Override
         protected void onPostExecute(Void aVoid) {
             super.onPostExecute(aVoid);
             finish();
             startActivity(new Intent(getApplicationContext(), MainActivity.class));
             Toast.makeText(getApplicationContext(),"Saved Details....",Toast.LENGTH_LONG).show();
         }
     }

     SaveTask callToExecute=new SaveTask();
        callToExecute.execute();

    }


}
