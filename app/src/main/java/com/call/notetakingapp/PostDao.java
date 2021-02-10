package com.call.notetakingapp;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

@Dao
interface PostDao {

    //Insert
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertPost(Post post);




}
