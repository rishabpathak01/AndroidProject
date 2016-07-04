package com.example.rishab.citrixtraining;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rishab.rest.RestClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void listUsersClicked(View view) {
        Intent intent = new Intent(MainActivity.this, ListAllUsers.class);
        startActivity(intent);
    }

    public void addUserClicked(View view) {
        setContentView(R.layout.add_user);

        Button clickButton = (Button) findViewById(R.id.submit);

        if (clickButton != null) {
            clickButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    EditText fname = (EditText) findViewById(R.id.ufname);
                    EditText lname = (EditText) findViewById(R.id.ulname);
                    EditText username = (EditText) findViewById(R.id.username);
                    EditText email = (EditText) findViewById(R.id.uemail);
                    JSONObject jsonParams = new JSONObject();
                    StringEntity stringEntity = null;
                    try {
                        jsonParams.put("lastName", lname.getText().toString());
                        jsonParams.put("firstName", fname.getText().toString());
                        jsonParams.put("userName", username.getText().toString());
                        //+System.currentTimeMillis());
                        jsonParams.put("email", email.getText().toString());
                        stringEntity = new StringEntity(jsonParams.toString());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    RestClient.post(getApplicationContext(), "", null, stringEntity, new JsonHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                            System.out.println("This is response: " + response);
                        }

                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                            System.out.println("response: " + response);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                            System.out.println("Error" + responseString);
                        }

                    });

                }

            });
        }
        Button clickButton1 = (Button) findViewById(R.id.cancel);
        if (clickButton1 != null) {
            clickButton1.setOnClickListener( new View.OnClickListener() {
                public void onClick(View v) {
                  //  setContentView(R.layout.activity_main);
                    Intent i=new Intent(MainActivity.this,MainActivity.class);
                    startActivity(i);
                    finish();
                }
            });
        }
    }
}