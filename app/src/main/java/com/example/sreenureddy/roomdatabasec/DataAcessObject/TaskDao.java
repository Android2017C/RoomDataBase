package com.example.sreenureddy.roomdatabasec.DataAcessObject;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.sreenureddy.roomdatabasec.Entity.Task;

import java.util.List;

//        DAO: Stands for Data Access Object. It is an interface that defines all the operations that we need to perform in our database.*/
@Dao
public interface TaskDao {

    @Query("SELECT * FROM task")
    List<Task> getAll();

    @Insert
    void insert(Task task);

    @Update
    void update(Task task);

    @Delete
    void delete(Task task);
}

//You can see above we defined all the methods needed for the Create, Read, Update and Delete operation.