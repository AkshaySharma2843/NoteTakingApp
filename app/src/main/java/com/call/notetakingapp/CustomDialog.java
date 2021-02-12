package com.call.notetakingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CustomDialog extends Dialog implements View.OnClickListener {
    TextView addNotes;
    EditText addTitle;
    EditText addDescription;
    Button addPost;
    Button cancel;
    PostsDatabase postsDatabase;
    PostDao postDao;
    DataRefreshListener dataRefreshListener;

    public CustomDialog( @NonNull Context context, DataRefreshListener dataRefreshListener) {
        super(context);
        this.dataRefreshListener = dataRefreshListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_dialog);
        initDatabase();
        initViews();

    }

    private void initViews() {
        addTitle = findViewById(R.id.et_title);
        addDescription = findViewById(R.id.et_description);
        addPost = findViewById(R.id.btn_addPost);
        cancel = findViewById(R.id.btn_cancelPost);
        addPost.setOnClickListener(this);
        cancel.setOnClickListener(this);
    }

    private void initDatabase() {
        postsDatabase = PostsDatabase.createDatabase(getContext());
       postDao = postsDatabase.postDao();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_addPost:
                checkAndAddPost();
                break;
            case R.id.btn_cancelPost:
                dismiss();
                break;
        }
    }

    private void checkAndAddPost() {
        if(!addTitle.getText().toString().isEmpty()&& !addDescription.getText().toString().isEmpty()){
            Post post = new Post();
            post.setTitle(addTitle.getText().toString());
            post.setDescription(addDescription.getText().toString());
            saveDataToDatabase(post);
        }
        else{
            Toast.makeText(getContext(), "Add Title And Description", Toast.LENGTH_SHORT).show();
        }
    }

    private void saveDataToDatabase(Post post) {
        class SavePost extends AsyncTask<Void, Void, Void>{

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected Void doInBackground(Void... voids) {
                postDao.insertPost(post);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                dataRefreshListener.onDataRefresh();
                dismiss();
            }
        }
        SavePost savePost = new SavePost();
        savePost.execute();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Window window = this.getWindow();
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

    }
}