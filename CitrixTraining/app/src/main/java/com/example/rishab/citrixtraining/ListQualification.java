package com.example.rishab.citrixtraining;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import com.example.rishab.rest.RestClient;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import org.json.JSONArray;
import org.json.JSONException;
import java.lang.String;
import org.json.JSONObject;
import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.entity.StringEntity;

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
        //flag is to check which function is requested by button click
        String flag=i.getStringExtra("flag");

        System.out.println("This is intent id extracted in list qualification :: ###################################### "+id+" flag"+flag);
        switch(flag)
        {
            case "0":
                listQualification(id);
                System.out.println(" list qualification is getting executed");
                break;
            case "1":
                createQualification(id);
                System.out.println(" create qualification is getting executed");

                break;
            case "2":
                updateUser(id);
                break;
            default:
                break;
        }
    }
    public void createQualification(final String userId)
    {
        setContentView(R.layout.create_qualification);
        System.out.println("clicked createQualification");
        Button clickButton = (Button) findViewById(R.id.submit);
        if (clickButton != null) {
            clickButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    EditText degname = (EditText) findViewById(R.id.degreeName);
                    EditText admyear = (EditText) findViewById(R.id.admYear);
                    EditText passyear = (EditText) findViewById(R.id.passYear);
                    EditText institute = (EditText) findViewById(R.id.instituteName);
                    EditText percent = (EditText) findViewById(R.id.percent);
                    RadioGroup rg1 = (RadioGroup) findViewById(R.id.rg);
                    String radiovalue = null;

                        radiovalue = ((RadioButton) findViewById(rg1.getCheckedRadioButtonId())).getText().toString();

                    System.out.println("this is selected radio button value  " + radiovalue);
                    System.out.println("this is userid "+userId);
                    JSONObject jsonParams = new JSONObject();
                    StringEntity stringEntity = null;
                    try {
                        jsonParams.put("id", userId);
                        jsonParams.put("degreeName", degname.getText().toString());
                        jsonParams.put("passoutYear", passyear.getText().toString());
                        jsonParams.put("userId", userId);
                        jsonParams.put("admissionYear", admyear.getText().toString());
                        jsonParams.put("instituteName", institute.getText().toString());
                        jsonParams.put("pecentage", percent.getText().toString());
                        jsonParams.put("distance",radiovalue);
                        stringEntity = new StringEntity(jsonParams.toString());

                    }
                    catch (Exception e) {
                        System.out.println("--------------------------------------------------------------------------------------------");
                        e.printStackTrace();
                    }

                   RequestParams params = new RequestParams("userId", userId);
                    RestClient.post(getApplicationContext(), userId+"/qualification", params,stringEntity, new JsonHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                            System.out.println("This is response: " + response);
                            listQualification(userId);
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
                    Intent i=new Intent(ListQualification.this,MainActivity.class);
                    startActivity(i);
                    finish();
                }
            });
        }
    }
    public void listQualification(String userId)
    {
        System.out.println("clicked listQualification");
        RequestParams params = new RequestParams("userId", userId);
        RestClient.get(userId+"/qualification",params,new JsonHttpResponseHandler(){

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                System.out.println("This is response: " + response);
                System.out.println("total education is "+response.length());

                String quali[] = new String[response.length()];
                for(int i=0;i<response.length();i++)
                {
                    JSONObject o;
                    try {
                        o = response.getJSONObject(i);
                        String dl=null;
                        if(o.getString("distance")=="false")
                            dl="No";
                        else
                            dl="Yes";
                        quali[i]="id: "+o.getInt("id")+"\nDegree Name: "+o.getString("degreeName")+"\nAdmission Year: "+o.getString("admissionYear")
                                +"\nUser Id: "+o.getString("userId")+"\nPassout Year: "+o.getString("passoutYear")+"\nInstitute Name: "+o.getString("instituteName")
                                +"\nDistance: "+dl+"\nPercentage: "+o.getString("pecentage")+"\n\n";

                    }
                    catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
                TextView textView = (TextView) findViewById(R.id.Qualification);
                for (int i=0;i<response.length();i++)
                    textView.append(quali[i]);
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
    public void updateUser(final String userId)
    {
        setContentView(R.layout.add_user);

                    final RequestParams params=new RequestParams("Id",userId);
                    RestClient.get(userId,params,new JsonHttpResponseHandler(){
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                            System.out.println("Response:::::: "+response);
                            EditText fname = (EditText) findViewById(R.id.ufname);
                            EditText lname = (EditText) findViewById(R.id.ulname);
                            EditText username = (EditText) findViewById(R.id.username);
                            EditText email = (EditText) findViewById(R.id.uemail);

                            try {
                                fname.setText(response.getString("firstName"));
                                lname.setText(response.getString("lastName"));
                                username.setText(response.getString("userName"));
                                email.setText(response.getString("email"));
                                System.out.println("lname:::::::::::::::::::::::::::::::::::::::::::::::::;;;"+lname.getText().toString());
                            } catch (JSONException e1) {
                                e1.printStackTrace();
                            }

                            final JSONObject jsonParams = new JSONObject();

                            final String    Lname=lname.getText().toString();
                            final String  Fname=fname.getText().toString();
                            final String      UserName=username.getText().toString();

                            final String     Email=email.getText().toString();
                            System.out.println("Lname@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@::   "+Lname);
                             //   final StringEntity se=stringEntity;



                                Button clickButton = (Button) findViewById(R.id.submit);
                                clickButton.setOnClickListener( new View.OnClickListener() {
                                    public void onClick(View v) {
                                         StringEntity stringEntity=null;
                                        EditText fname = (EditText) findViewById(R.id.ufname);
                                        EditText lname = (EditText) findViewById(R.id.ulname);
                                        EditText username = (EditText) findViewById(R.id.username);
                                        EditText email = (EditText) findViewById(R.id.uemail);

                                        JSONObject jsonParams = new JSONObject();
                                        final String    Lname=lname.getText().toString();
                                        final String  Fname=fname.getText().toString();
                                        final String      UserName=username.getText().toString();

                                        final String     Email=email.getText().toString();

                                        System.out.println("--------------------------------------------------------------\n" +
                                                "Lname  "+Lname+"   Fname  "+Fname+"  UserName  "+UserName+" Email "+Email+
                                                "-----------------------------------------------------------------------" );
                                        try {
                                            jsonParams.put("lastName", Lname);
                                            jsonParams.put("firstName", Fname);
                                            jsonParams.put("userName", UserName);
                                            //+System.currentTimeMillis());
                                            jsonParams.put("email", Email);

                                            stringEntity = new StringEntity(jsonParams.toString());
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }

                                        RequestParams params=new RequestParams("id", userId);

                                        RestClient.put(getApplicationContext(),userId,params,stringEntity,new JsonHttpResponseHandler() {
                                            @Override
                                            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                                                System.out.println("This is response: " + response);
                                            }
                                            @Override
                                            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                                                System.out.println("response: " + response);
                                                Intent i=new Intent(getApplicationContext(),ListAllUsers.class );
                                                startActivity(i);
                                            }

                                            @Override
                                            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                                                System.out.println("Error" + responseString);
                                            }

                                        });


                                    }

                                });
                            Button clickButton1 = (Button) findViewById(R.id.cancel);
                            if (clickButton1 != null) {
                                clickButton1.setOnClickListener( new View.OnClickListener() {
                                    public void onClick(View v) {
                                        //  setContentView(R.layout.activity_main);
                                        Intent i=new Intent(ListQualification.this,ListQualification.class);
                                        startActivity(i);

                                    }
                                });
                            }

                        }
                        @Override
                        public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                            System.out.println("Error:::::::: "+responseString);
                        }
                    });
    }


}
