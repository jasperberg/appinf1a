package com.DDB.hardwire.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.DDB.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


public class inside_category extends Activity {

    private static String _category;
    List<product> Products = new ArrayList<product>();
    ListView productListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inside_category);
        setTitle(_category);
        productListView = (ListView) findViewById(R.id.listView);
        addProduct();
        populateList();
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

    public static void setCategory(String category){
        _category = category;
    }

    public void addProduct(){
        Products.add(new product("Intel",1, "i7", "Dayum", 430));
        Products.add(new product("Intel",2, "i5", "Not that dayum", 200));
    }

    public void populateList(){
        ArrayAdapter<product> adapter = new ProductListAdapter();
        productListView.setAdapter(adapter);
    }


    public String getCategory(){
        return _category;
    }

    private class ProductListAdapter extends ArrayAdapter<product>{
        public ProductListAdapter(){
            super (inside_category.this, R.layout.product, Products);
        }

        @Override
        public View getView(int position, View view, ViewGroup parent) {
            if (view == null)
                view = getLayoutInflater().inflate(R.layout.product, parent, false);

            product prod = Products.get(position);

            TextView name = (TextView) view.findViewById(R.id.productTitle);
            name.setText(prod.getProductName());
            TextView price = (TextView) view.findViewById(R.id.productPrice);
            price.setText("€"+prod.getProductPrice());

            return view;
        }
    }
}
