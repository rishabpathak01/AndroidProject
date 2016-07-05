
/**
 * Created by Rishab on 6/25/2016.
 */
package com.example.rishab.citrixtraining;



import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.rishab.rest.RestClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;


public class SingleListItem extends AppCompatActivity{
    static String id;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.single_list_item_view);
        TextView txtProduct = (TextView) findViewById(R.id.product_label);
        Intent i = getIntent();
        // getting attached intent data
        String product = i.getStringExtra("details");
          id=i.getStringExtra("id");
        // displaying selected product name
        txtProduct.setText(product);


        //listQualification(id);
      // System.out.println("This is intent id extracted :: ###################################### "+id);
        Button clickButton = (Button) findViewById(R.id.listqualification);
        clickButton.setOnClickListener( new View.OnClickListener() {
            public void onClick(View v) {
               listQualification(id);
            }
        });


        Button clickButton1 = (Button) findViewById(R.id.addQualification);
        clickButton1.setOnClickListener( new View.OnClickListener() {
            public void onClick(View v) {
                createQualification(id);
            }
        });


//  For updating User details
        Button clickButton2 = (Button) findViewById(R.id.updateUser);
        clickButton2.setOnClickListener( new View.OnClickListener() {
            public void onClick(View v) {
                update_User(id);
            }
        });
        Button clickButton3 = (Button) findViewById(R.id.deleteUser);
        clickButton3.setOnClickListener( new View.OnClickListener() {
            public void onClick(View v) {
                deleteUser(id);
            }
        });
    }

    public void listQualification(String userId)
    {
        System.out.println("this id is sent to listQualification Activity for listQualification-----------------------------------"+userId);
            Intent i = new Intent(getApplicationContext(), ListQualification.class);
            // sending data to new activity
            i.putExtra("id",userId);
            i.putExtra("flag","0");
            startActivity(i);
    }
    public void createQualification(String userId)
    {
        System.out.println("this id is sent to listQualification for createQualification method-----------------------------------"+userId);
        Intent i = new Intent(getApplicationContext(), ListQualification.class);
        // sending data to new activity
        i.putExtra("id",userId);
        i.putExtra("flag","1");
        startActivity(i);
    }

    public void update_User(String userId)
    {
        System.out.println("this id is sent to listQualification for updtaeUser method-----------------------------------"+userId);
        Intent i = new Intent(getApplicationContext(), ListQualification.class);
        // sending data to new activity
        i.putExtra("id",userId);
        i.putExtra("flag","2");
        startActivity(i);
    }
  /*  public void deleteUser(String userId)
    {
        System.out.println("this id is sent to listQualification for updtaeUser method-----------------------------------"+userId);
        Intent i = new Intent(getApplicationContext(), ListQualification.class);
        // sending data to new activity
        i.putExtra("id",userId);
        i.putExtra("flag","3");
        startActivity(i);
    }*/
  public void deleteUser(String userId)
  {

      setContentView(R.layout.activity_main);
      RequestParams params=new RequestParams("id",userId);
      RestClient.delete(userId,params,new JsonHttpResponseHandler() {
          @Override
          public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
              System.out.println("This is response: " + response);
              Intent i=new Intent(SingleListItem.this,MainActivity.class);
              startActivity(i);
              finish();
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
}


