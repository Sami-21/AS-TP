package com.example.myapplication;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    private Button button;
    private ArrayList<PostModal> PostModalArrayList;
    private DB DB;
    private PostAdapter PostAdapter;
    private RecyclerView posts;

    public void openActivity2(){
        Intent intent = new Intent(this , MainActivity2.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button =(Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity2();
            }
        });



        PostModalArrayList = new ArrayList<>();
        DB = new DB(MainActivity.this);

        PostModalArrayList = DB.readPosts();

        PostAdapter = new PostAdapter(PostModalArrayList, MainActivity.this);
        posts = findViewById(R.id.posts);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this, RecyclerView.VERTICAL, false);
        posts.setLayoutManager(linearLayoutManager);

        posts.setAdapter(PostAdapter);
    }
}

