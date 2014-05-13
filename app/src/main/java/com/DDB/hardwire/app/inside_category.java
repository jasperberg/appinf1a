package com.DDB.hardwire.app;

import android.app.Activity;
import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.DDB.R;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


public class inside_category extends Activity implements Serializable {

    private static String _category;
    List<product> Products = new ArrayList<product>();
    ListView productListView;
    private List<product> productCategory = new ArrayList<product>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inside_category);
        setTitle(_category);
        productListView = (ListView) findViewById(R.id.listView);
        addProduct();
        populateList();

        productListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent intent = new Intent(inside_category.this, product_view.class);
                int productid = productCategory.get(position).getId();
                intent.putExtra("Product", productid);
                intent.putExtra("data", new DataWrapper(productCategory));
                startActivity(intent);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.inside_category, menu);
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

    public List<product> getProductCategory(){
        return productCategory;
    }

    public static void setCategory(String category){
        _category = category;
    }

    public void addProduct(){
        Products.add(new product("Socket AM3+",0, "i7", "Dayum", 430));
        Products.add(new product("Socket AM3+",1, "Cool", "yeah", 430));
        Products.add(new product("Socket 2011",2, "i5", "Not that dayum", 200));
        Products.add(new product("AMD",3, "i7", "Dayum", 430));
        Products.add(new product("Nvidia",4, "i5", "Not that dayum", 200));
        Products.add(new product("DDR2",5, "i7", "Dayum", 430));
        Products.add(new product("ATX",6, "i5", "Not that dayum", 200));

        int listLength = Products.size();
        for(int i = 0; i < listLength; i++){
            if(Products.get(i).getListId().contains(_category)){
                productCategory.add(Products.get(i));
            }
        }
    }

    private void populateList(){
        ArrayAdapter<product> adapter = new ProductListAdapter();
        productListView.setAdapter(adapter);
    }


    public String getCategory(){
        return _category;
    }

    private class ProductListAdapter extends ArrayAdapter<product>{
        public ProductListAdapter(){
            super (inside_category.this, R.layout.product, productCategory);
        }

        @Override
        public long getItemId(int position){
            // return the id(), or whatever you use to access the id on your product object
            return productCategory.get(position).getId();
        }

        @Override
        public View getView(int position, View view, ViewGroup parent) {
            if (view == null)
                view = getLayoutInflater().inflate(R.layout.product, parent, false);

            product prod = productCategory.get(position);

            TextView name = (TextView) view.findViewById(R.id.productTitle);
            name.setText(prod.getProductName());
            TextView price = (TextView) view.findViewById(R.id.productPrice);
            price.setText("€"+prod.getProductPrice());



            return view;
        }
    }
}
