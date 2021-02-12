package com.call.notetakingapp;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.ArrayList;
import java.util.List;

@Dao
interface PostDao {

    //Insert
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertPost(Post post);

    @Query("SELECT * FROM post_table")
    public List<Post> getPost();

    @Query("DELETE FROM post_table WHERE id =:id")
    public void deletePost(int id);


}
