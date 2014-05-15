package com.DDB.hardwire.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.DDB.R;

import java.io.Serializable;
import java.util.List;

public class product_view extends Activity implements Serializable {
    //product currentProduct = (product) getIntent().getSerializableExtra("Product");
    String Name, Description, ListId;
    int Price, currentProductId;
    List<product> prodCat;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_view);
        currentProductId = getIntent().getIntExtra("Product", 0);
        DataWrapper dw = (DataWrapper) getIntent().getSerializableExtra("data");
        prodCat = dw.getProductCategory();
        populate();
        getActionBar().setDisplayHomeAsUpEnabled(true);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.product_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void populate(){

        int size = prodCat.size();
        for(int i = 0; i < size; i++ ){
            if(prodCat.get(i).getId() == currentProductId){
                Name = prodCat.get(i).getProductName();
                Price = prodCat.get(i).getProductPrice();
                Description = prodCat.get(i).getProductDescription();
                ListId = prodCat.get(i).getListId();
            }
        }

        product currentProduct = new product(ListId, currentProductId, Name, Description, Price);

        setTitle(currentProduct.getProductName());

        TextView name = (TextView) findViewById(R.id.productNameTitle);
        name.setText(currentProduct.getProductName());
        TextView price = (TextView) findViewById(R.id.productPriceView);
        price.setText("€"+currentProduct.getProductPrice());
        TextView productType = (TextView) findViewById(R.id.productTypeId);
        productType.setText(currentProduct.getListId());
        TextView description = (TextView) findViewById(R.id.productDescriptionView);
        description.setText(currentProduct.getProductDescription());
    }
}


