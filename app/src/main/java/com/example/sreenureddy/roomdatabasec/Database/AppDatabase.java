package com.example.sreenureddy.roomdatabasec.Database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.sreenureddy.roomdatabasec.DataAcessObject.TaskDao;
import com.example.sreenureddy.roomdatabasec.Entity.Task;

// Database: It is an abstract class where we define all our entities. and database version defining

@Database(entities = {Task.class},version = 1)
public abstract class AppDatabase extends RoomDatabase {

   public abstract TaskDao taskDao();
}
//In the above class we define all the entities and the database version.