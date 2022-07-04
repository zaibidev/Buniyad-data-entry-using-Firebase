package com.example.practice;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;

public class PostListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private com.example.practice.PostAdapter adapter;
    private FloatingActionButton add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_list);

        recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<com.example.practice.Post> options =
                new FirebaseRecyclerOptions.Builder<com.example.practice.Post>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Post"), com.example.practice.Post.class)
                        .build();

        adapter = new com.example.practice.PostAdapter(options,this);
        recyclerView.setAdapter(adapter);

        add = findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PostListActivity.this, com.example.practice.MainActivity.class));
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}