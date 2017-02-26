package com.example.cutit_000.github_user_search.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.cutit_000.github_user_search.Models.Repository;
import com.example.cutit_000.github_user_search.R;

public class RepDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rep_details);

        Intent intent = getIntent();
        final Repository repoData = (Repository) intent.getParcelableExtra("Repository");

        TextView mId = (TextView) findViewById(R.id.id);
        TextView mName = (TextView) findViewById(R.id.name);
        TextView mUsername = (TextView) findViewById(R.id.username);
        TextView mDescription = (TextView) findViewById(R.id.description);

        mId.setText("1. id: " + repoData.getId());
        mName.setText("2. user: " + repoData.getUser());
        mUsername.setText("3. name: " + repoData.getName());
        mDescription.setText("4. description: " + repoData.getDescription());

    }
}
