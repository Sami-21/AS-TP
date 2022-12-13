package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {
    private Button addPost;
    private EditText title , author ,content;
    private DB Database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        addPost =findViewById(R.id.addPost);
        title= findViewById(R.id.title);
        author= findViewById(R.id.author);
        content= findViewById(R.id.content);

        Database = new DB(MainActivity2.this);

        addPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String titleValue = title.getText().toString();
               String authorValue = author.getText().toString();
               String contentValue = content.getText().toString();

                if (titleValue.isEmpty() || authorValue.isEmpty() || contentValue.isEmpty()) {
                    Toast.makeText(MainActivity2.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                Database.addPost(titleValue, authorValue, contentValue);

                Toast.makeText(MainActivity2.this, "Post added.", Toast.LENGTH_SHORT).show();
                title.setText("");
                author.setText("");
                content.setText("");
            }
        });
    }

}