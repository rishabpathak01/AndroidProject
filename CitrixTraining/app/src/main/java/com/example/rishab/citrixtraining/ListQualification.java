package com.example.rishab.citrixtraining;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.rishab.rest.RestClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

/**
 * Created by Rishab on 6/26/2016.
 */
public class ListQualification extends AppCompatActivity{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.list_qualification);
        Intent i = getIntent();
        String id=i.getStringExtra("id");
        System.out.println("This is intent id extracted in list qualification :: ###################################### "+id);
        listQualification(id);
    }
    public void listQualification(String userId)
    {
        System.out.println("clicked listQualification");
        RestClient.get("qualification/"+userId,null,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                System.out.println("This is response: "+response);
                try {
                    String quali="\nId: "+response.getString("id")+"\nInstitute Name: "+response.getString("instituteName")+"\nDegree: "+
                            response.getString("degreeName")+"\nAdmission Year: "+response.getString("admissionYear")
                            +"\nPassing Year: "+response.getString("passoutYear")+"\nPercentage: "+response.getString("pecentage")+
                            "\nDistance Learning: "+response.getString("distance");
                    TextView textView = (TextView) findViewById(R.id.Qualification);
                    textView.setText(quali);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
/*
+"\nAdmission Year: "+response.getString("admissionYear")
                            +"\nPassing Year: "+response.getString("passoutYear")+ "\nPercentage: "+response.getString("percentage")+
                            "\nDistance Learning: "+response.getString("distance")

 */

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                System.out.println("Error"+responseString);
            }
        });


    }
}
