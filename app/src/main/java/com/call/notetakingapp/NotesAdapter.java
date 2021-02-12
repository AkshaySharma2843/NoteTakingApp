package com.call.notetakingapp;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

class NotesAdapter extends RecyclerView.Adapter<NotesViewHolder> {
    ArrayList<Post> post;
    PostClickListener postClickListener;

    public NotesAdapter(ArrayList<Post> post, PostClickListener postClickListener) {
        this.post = post;
        this.postClickListener = postClickListener;
    }

    @NonNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NotesViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.notes_row,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull NotesViewHolder holder, int position) {
        Post notes = post.get(position);
        holder.bind(notes,postClickListener);


    }

    @Override
    public int getItemCount() {
        return post.size();
    }

    public void setNewData(ArrayList<Post> post){
        this.post.clear();
        this.post = post;
        notifyDataSetChanged();
    }
}
