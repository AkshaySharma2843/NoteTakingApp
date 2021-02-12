package com.call.notetakingapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,PostClickListener, DataRefreshListener {
    FloatingActionButton actionButton;
    RecyclerView recyclerView;
    PostsDatabase postsDatabase;
    PostDao postDao;
    ArrayList<Post> post;
    NotesAdapter notesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initDb();
        initView();
        drawRecycler();
    }

    private void initDb() {
        postsDatabase = PostsDatabase.createDatabase(this);
        postDao = postsDatabase.postDao();
    }

    private void drawRecycler() {
        post = new ArrayList<>();
        class GetData extends AsyncTask <Void,Void,Void>{

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                Toast.makeText(MainActivity.this, "Loading Data", Toast.LENGTH_SHORT).show();
            }

            @Override
            protected Void doInBackground(Void... voids) {
                post = (ArrayList<Post>) postDao.getPost() ;
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                if(notesAdapter == null){
                    notesAdapter = new NotesAdapter(post,MainActivity.this);
                    recyclerView.setAdapter(notesAdapter);
                }
                else{
                    notesAdapter.setNewData(post);
                }
            }
        }
        GetData getData = new GetData();
        getData.execute();
    }

    private void initView() {

        actionButton = findViewById(R.id.btn_add);
        actionButton.setOnClickListener(this);
        recyclerView = findViewById(R.id.rcy_notes);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.btn_add){
            CustomDialog c = new CustomDialog(this, this);
            c.show();

        }

    }

    @Override
    public void clickedPost(int id) {
        System.out.println("Post Id "+id);
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("Post Information");
        alertDialog.setPositiveButton("Delete Post", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                deletePost(id);
            }
        });
        alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        AlertDialog alertDial = alertDialog.create();
        alertDial.show();
    }

    private void deletePost(int id) {
        class DeleteTask extends AsyncTask<Void, Void , Void>{

            @Override
            protected Void doInBackground(Void... voids) {
                postDao.deletePost(id);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                drawRecycler();
            }
        }
        DeleteTask deleteTask = new DeleteTask();
        deleteTask.execute();

    }

    @Override
    public void onDataRefresh() {
        drawRecycler();
    }
}