package com.example.cutit_000.github_user_search.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.cutit_000.github_user_search.Models.User;
import com.example.cutit_000.github_user_search.R;


public class UserInfo extends AppCompatActivity {
    private RecyclerView mRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        Intent intent = getIntent();
        final User udata = (User) intent.getParcelableExtra("User");

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, udata.getRepositories());
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter.setOnItemClickListener(new RecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent newActivity = new Intent(UserInfo.this, RepDetails.class);
                newActivity.putExtra("Repository", udata.getSpecifiedRepository(position));
                startActivity(newActivity);
            }
        });
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView nameTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            nameTextView = (TextView) itemView.findViewById(R.id.repo_name);
        }
    }
}