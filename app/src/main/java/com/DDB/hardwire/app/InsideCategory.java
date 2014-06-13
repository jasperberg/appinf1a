package com.DDB.hardwire.app;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.DDB.R;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;


public class InsideCategory extends Activity implements Serializable {

    private static String _category;
    List<Product> Products = new ArrayList<Product>();
    ListView productListView;
    private List<Product> productCategory = new ArrayList<Product>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inside_category);
        setTitle(_category);
        productListView = (ListView) findViewById(R.id.listView);
        addProduct();
        populateList();
        getActionBar().setDisplayHomeAsUpEnabled(true);

        productListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent intent = new Intent(InsideCategory.this, ProductView.class);
                int productid = productCategory.get(position).getId();
                int pkid = productCategory.get(position).getPkid();
                int[] array = {productid, pkid};
                intent.putExtra("Product", array);
                intent.putExtra("method", "None");
                startActivity(intent);
            }
        });
    }

    public List<Product> getProductCategory(){
        return productCategory;
    }

    public static void setCategory(String category){
        _category = category;
    }

    public void addProduct(){
        GetItems GI = new GetItems();
        Products = GI.getProductLister();
        int listLength = Products.size();
        for(int i = 0; i < listLength; i++){
            if(Products.get(i).getListId().equals(_category)){
                productCategory.add(Products.get(i));
            }
        }
    }

    private void populateList(){
        ArrayAdapter<Product> adapter = new ProductListAdapter();
        productListView.setAdapter(adapter);
    }


    public String getCategory(){
        return _category;
    }

    private class ProductListAdapter extends ArrayAdapter<Product>{
        public ProductListAdapter(){
            super (InsideCategory.this, R.layout.product, productCategory);
        }

        @Override
        public long getItemId(int position){
            return productCategory.get(position).getId();
        }

        @Override
        public View getView(int position, View view, ViewGroup parent) {
            if (view == null)
                view = getLayoutInflater().inflate(R.layout.product, parent, false);

            Product prod = productCategory.get(position);

            TextView name = (TextView) view.findViewById(R.id.productTitle);
            name.setText(prod.getProductName());
            TextView price = (TextView) view.findViewById(R.id.productPrice);
            DecimalFormat df = new DecimalFormat("0.00##");
            String result = df.format(prod.getProductPrice());
            price.setText("€"+result);
            ImageView picture = (ImageView) view.findViewById(R.id.productImage);
            picture.setImageBitmap(prod.getPicture());
            return view;
        }
    }
}
