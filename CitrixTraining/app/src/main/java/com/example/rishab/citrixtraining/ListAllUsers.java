package com.example.rishab.citrixtraining;


import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rishab.rest.RestClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import cz.msebera.android.httpclient.Header;


public class ListAllUsers extends AppCompatActivity {

    ListView list;
    TextView id;
    TextView fname;
    TextView lname;
    ArrayList<HashMap<String, String>> userlist = new ArrayList<HashMap<String, String>>();
    private static final String TAG_ID = "id";
    private static final String TAG_FNAME = "firstName";
    private static final String TAG_LNAME = "lastName";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_users);
        //    System.out.println("Helloooooooooooooo------------------------------------------------------------------------");
        RestClient.get("", null, new JsonHttpResponseHandler() {
            JSONObject android;

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                // System.out.println("the length of json value"+response.length());
                for (int i = 0; i < response.length(); i++) {

                    try {
                        android = response.getJSONObject(i);
                        String id = android.getString(TAG_ID);
                        String fname = android.getString(TAG_FNAME);
                        String lname = android.getString(TAG_LNAME);
                        String username = android.getString("userName");
                        String email = android.getString("email");
                        System.out.println("username---------------------" + username + "    " + email);
                        HashMap<String, String> map = new HashMap<String, String>();
                        map.put(TAG_ID, id);
                        map.put(TAG_FNAME, fname);
                        map.put(TAG_LNAME, lname);
                        userlist.add(map);
                        map.put("userName", username);
                        map.put("email", email);
                        list = (ListView) findViewById(R.id.list);
                        ListAdapter adapter = new SimpleAdapter(ListAllUsers.this, userlist,
                                R.layout.row_layout,
                                new String[]{TAG_ID, TAG_FNAME, TAG_LNAME}, new int[]{
                                R.id.id, R.id.first_name, R.id.last_name});
                        list.setAdapter(adapter);
                        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                            @Override
                            public void onItemClick(AdapterView<?> parent, View view,
                                                    int position, long id) {
                                //  Toast.makeText(ListAllUsers.this, "You Clicked on id "+oslist.get(+position).get("id"), Toast.LENGTH_SHORT).show();
                                // View v =findViewById(R.id.id);
                                // String product=v.toString();

                                String details = "User Details::\n\n" + "User Id:: " + userlist.get(position).get("id") + "\nName:: " + userlist.get(position).get("firstName")
                                        + " " + userlist.get(position).get("lastName") + "\n" + "Username:: " + userlist.get(position).get("userName") +
                                        "\n" + "Email:: " + userlist.get(position).get("email");
                                // System.out.println("ID=-------------------------------------------------------------"+oslist.get(position));
                                //oslist.get(position) on clicking option 5 gives  System.out: ID=---------------{firstName=Avinash, id=5, lastName=Gautam}
                                System.out.println("this is details of selected User::------------" + details);
                                // Launching new Activity on selecting single List Item
                                Intent i = new Intent(getApplicationContext(), SingleListItem.class);
                                // sending data to new activity
                                i.putExtra("details", details);
                                i.putExtra("id", userlist.get(position).get("id"));
                                startActivity(i);
                            }


                        });

                        //  System.out.println("Id"+o.getInt("id")+" Name: "+o.getString("firstName")+" "+o.getString("lastName"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }


            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                System.out.println("Error" + responseString);
            }
        });

    }

}


//--------------------------------SIMPLE LIST WITH STRINGS ------------------------------------------------------------------
        /*  String[] values = new String[] { "Android", "iPhone", "WindowsMobile",
                "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
                "Linux", "OS/2" };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.row_layout,R.id.id,values);
        setListAdapter(adapter);*/


//$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ EXTRACTING ID,FIRST NAME AND LAST NAME $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$44
/*        RestClient.get("",null,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {

                System.out.println("the length of json value"+response.length());
            /*    UserData d=new UserData();

                d.id_array=new int[response.length()];
                d.first_name=new String[response.length()];
                d.last_name=new String[response.length()];

                for(int i=0;i<response.length();i++)
                {
                    JSONObject o;
                    try {
                        o = response.getJSONObject(i);
                        System.out.println("Id"+o.getInt("id")+" Name: "+o.getString("firstName")+" "+o.getString("lastName"));
                    }
                    catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                System.out.println("Error"+responseString);
            }
        });*/
        //$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$

        //-------------------------------------------------------------------------------------------------------------------------------------------------------------------


 /*     RestClient.get("",null,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                for(int i=0;i<response.length();i++)
                {
                    JSONObject o;
                    try {
                        o = response.getJSONObject(i);
                        System.out.println("Id"+o.getInt("id")+" Name: "+o.getString("firstName")+" "+o.getString("lastName"));
                    }
                    catch (JSONException e) {
                        e.printStackTrace();
                    }

                }

                System.out.println("the length of json value"+response.length());
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                System.out.println("Error"+responseString);
            }
        });*/