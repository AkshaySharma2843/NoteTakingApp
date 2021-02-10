package com.call.notetakingapp;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

class NotesViewHolder extends RecyclerView.ViewHolder {
    TextView title;
    TextView description;

    public NotesViewHolder(@NonNull View itemView) {
        super(itemView);

        title = itemView.findViewById(R.id.tv_title);
        description = itemView.findViewById(R.id.tv_description);
    }


    public void bind(Post notes) {
        title.setText(notes.getTitle());
        description.setText(notes.getDescription());

    }
}
