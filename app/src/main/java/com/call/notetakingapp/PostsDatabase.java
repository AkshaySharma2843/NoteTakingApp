package com.call.notetakingapp;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(
        entities = Post.class,
        version = 1,
        exportSchema = false
)
abstract class PostsDatabase extends RoomDatabase {
    public static PostsDatabase database;
     abstract PostDao postDao();

    public static PostsDatabase createDatabase(Context context){
      if(database == null){
          database = Room.databaseBuilder(context, PostsDatabase.class, "POST_DB").build();
      }
      return database;
    }
}
