package com.example.cutit_000.github_user_search.Activities;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.example.cutit_000.github_user_search.Models.Repository;
import com.example.cutit_000.github_user_search.Models.User;
import com.example.cutit_000.github_user_search.Network.ApiClient;
import com.example.cutit_000.github_user_search.R;
import com.example.cutit_000.github_user_search.interfaces.IRepositoryReceiver;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements IRepositoryReceiver {
    final User mUser = new User();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }


        final TextView textView = (TextView) findViewById(R.id.userStatus);
        final Button repButton = (Button) findViewById(R.id.repButton);
        repButton.setEnabled(false);
        final ApiClient check = new ApiClient();

        final EditText userField = (EditText) findViewById(R.id.inputUsernameField);
        findViewById(R.id.searchButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = userField.getText().toString();
                if (username.isEmpty()) {
                    Toast.makeText(MainActivity.this, "nume utilizator nu poate fi gol", Toast.LENGTH_LONG).show();
                } else {
                    if (check.checkUser(username)) {
                        textView.setText("User existent");
                        repButton.setEnabled(true);
                        check.getUser(username, new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    String name = response.getString("login");
                                    Integer id = response.getInt("id");
                                    String avatar_url = response.getString("avatar_url");
                                    mUser.setData(id, name, avatar_url);
                                    check.getRepositories(name, MainActivity.this);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    } else {
                        textView.setText("User inexistent");
                        repButton.setEnabled(false);
                    }
                }
            }
        });

        repButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, UserInfo.class);

                intent.putExtra("User", mUser);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onRepositoryReceived(Repository[] repositories) {
        mUser.setRepositories(repositories);
    }
}