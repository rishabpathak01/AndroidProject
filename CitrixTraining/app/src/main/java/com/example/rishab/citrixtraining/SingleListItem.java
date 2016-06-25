
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
                // TODO Auto-generated method stub
               listQualification(id);
            }
        });


    }

    public void listQualification( String userId)
    {
        System.out.println("this id is sent to listQualification Activity-----------------------------------"+userId);
            Intent i = new Intent(getApplicationContext(), ListQualification.class);
            // sending data to new activity
            i.putExtra("id",userId);
            startActivity(i);


    }




}


