package com.example.rishab.citrixtraining;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import cz.msebera.android.httpclient.Header;

import com.example.rishab.rest.RestClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import org.json.JSONException;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;


public class MainActivity extends AppCompatActivity {
    String json_string,result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public class UserData{
        int id_array[];
        String last_name[];
        String first_name[];
    }
    public void listUsersClicked(View view)
    {


        RestClient.get("",null,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {

                System.out.println("the length of json value"+response.length());
                UserData d=new UserData();

                d.id_array=new int[response.length()];
                d.first_name=new String[response.length()];
                d.last_name=new String[response.length()];

                for(int i=0;i<response.length();i++)
                {
                    JSONObject o = null;
                    try {
                        o = response.getJSONObject(i);
                        d.id_array[i]=o.getInt("id");
                        d.first_name[i]=o.getString("firstName");
                        d.last_name[i]=o.getString("lastName");

                    }
                    // System.out.println(o.getString("lastName"));
                    catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
                for(int i=0;i<response.length();i++)
                {
                    System.out.println("Id:"+d.id_array[i]+" First Name:"+d.first_name[i]+" Last Name:"+d.last_name[i]);

                }
                // result=response.toString();
                // System.out.println("This is response: "+response);
                //   json_string=result;
             /*  if(json_string==null)
                    System.out.println("not able to get a json string");
                else{
                    System.out.println("ok----------------------------------------------------------------------------------------");
                    System.out.println(json_string);
                    Intent i=new Intent(MainActivity.this,ListAllUsers.class);
                    //json_data is the key name
                    i.putExtra("json_data",json_string);
                    startActivity(i);
                }*/
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                System.out.println("Error"+responseString);
            }
        });

    }
}