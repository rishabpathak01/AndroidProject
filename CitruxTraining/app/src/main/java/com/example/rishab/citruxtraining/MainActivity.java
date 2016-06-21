package com.example.rishab.citruxtraining;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.rishab.citruxtraining.rest.RestClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void listAllUser(View view)
    {
        System.out.println("clicked list all users");
        RestClient.get("",null,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {

            //    System.out.println("the length of json value"+response.length());
                for(int i=0;i<response.length();i++)
                {
                    JSONObject o = null;
                    try {
                        o = response.getJSONObject(i);
                        System.out.println(o.getInt("id")+" "+o.getString("firstName")+" "+o.getString("lastName"));
                    }
                       // System.out.println(o.getString("lastName"));
                     catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
                System.out.println("This is response: "+response);
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                System.out.println("Error"+responseString);
            }
        });
    }
}
