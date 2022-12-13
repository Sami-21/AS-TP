package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {

    private ArrayList<PostModal> PostModalArrayList;
    private Context context;

    public PostAdapter(ArrayList<PostModal> PostModalArrayList, Context context) {
        this.PostModalArrayList = PostModalArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_rv_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        PostModal modal = PostModalArrayList.get(position);
        holder.titleView.setText(modal.getTitle());
        holder.authorView.setText(modal.getAuthor());
        holder.contentView.setText(modal.getContent());
    }

    @Override
    public int getItemCount() {
        return PostModalArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView titleView, authorView, contentView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleView = itemView.findViewById(R.id.postTitle);
            authorView = itemView.findViewById(R.id.postAuthor);
            contentView = itemView.findViewById(R.id.postContent);
        }
    }
}
