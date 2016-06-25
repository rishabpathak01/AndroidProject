
/**
 * Created by Rishab on 6/25/2016.
 */
package com.example.rishab.citrixtraining;



import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;


public class SingleListItem extends AppCompatActivity{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.single_list_item_view);
        TextView txtProduct = (TextView) findViewById(R.id.product_label);
        Intent i = getIntent();
        // getting attached intent data
        String product = i.getStringExtra("details");
        // displaying selected product name
        txtProduct.setText(product);

    }

}
