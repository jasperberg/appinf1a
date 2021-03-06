package com.DDB.hardwire.app;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.DDB.R;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by MarK on 29-May-14.
 * The view that shows the information of a product.
 */
public class ProductView extends Activity implements Serializable {
    String Name, Description, ListId;
    int[] currentProductId;
    int pkid;
    int pid;
    double Price;
    Bitmap Pic;
    List<Product> Products;
    List<Product> currProd = new ArrayList<Product>();
    String methodText;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_view);
        currentProductId = getIntent().getIntArrayExtra("Product");
        pkid = currentProductId[1];
        pid = currentProductId[0];
        methodText = getIntent().getStringExtra("method");
        if(methodText.equals("true")){
            changeButtonSpecs();
        }
        GetItems gi = new GetItems();
        Products = gi.getProductLister();
        populate();
        getActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onResume(){
        super.onResume();
        if(GetItems.getProductLister().size() == 0){
            new GetItems().execute();
        }
        if(GetGuidePages.getPageList().size() == 0){
            new GetGuidePages().execute();
        }
    }

    private void populate(){
        int size = Products.size();
        for(int i = 0; i < size; i++ ){
            if(Products.get(i).getId() == pid){
                Name = Products.get(i).getProductName();
                Price = Products.get(i).getProductPrice();
                Description = Products.get(i).getProductDescription();
                ListId = Products.get(i).getListId();
                Pic = Products.get(i).getPicture();
            }
        }

        Product currentProduct = new Product(ListId, pid, Name, Description, Price, Pic);

        currProd.add(new Product(ListId, pid, Name, Description, Price, Pic));

        setTitle(currentProduct.getProductName());

        TextView name = (TextView) findViewById(R.id.productNameTitle);
        name.setText(currentProduct.getProductName());
        TextView price = (TextView) findViewById(R.id.productPriceView);
        DecimalFormat df = new DecimalFormat("0.00##");
        String result = df.format(currentProduct.getProductPrice());
        price.setText("€"+result);
        TextView productType = (TextView) findViewById(R.id.productTypeId);
        productType.setText(currentProduct.getListId());
        TextView description = (TextView) findViewById(R.id.productDescriptionView);
        description.setText(currentProduct.getProductDescription());
        ImageView picture = (ImageView) findViewById(R.id.productImageView);
        picture.setImageBitmap(currentProduct.getPicture());
    }

    public void addProductToBuild(View view){
        ProductDataSource datasource = new ProductDataSource(getApplicationContext());
        datasource.open();
        MainActivity.indexAddedProducts();
        String addedMotherboard = MainActivity.getAddedMotherboard();
        String addedProcessor = MainActivity.getAddedProcessor();
        if(currProd.get(0).getListId().contains("Socket")) {
            if (addedProcessor.equals("Socket AM3+") && !currProd.get(0).getListId().equals("MB Socket AM3+")) {
                Toast.makeText(getApplicationContext(), "Dit Product is niet compatible", Toast.LENGTH_SHORT).show();
            } else if (addedProcessor.equals("Socket FM2+") && !currProd.get(0).getListId().equals("MB Socket FM2+")) {
                Toast.makeText(getApplicationContext(), "Dit Product is niet compatible", Toast.LENGTH_SHORT).show();
            } else if (addedProcessor.equals("Socket 1150") && !currProd.get(0).getListId().equals("MB Socket 1150")) {
                Toast.makeText(getApplicationContext(), "Dit Product is niet compatible", Toast.LENGTH_SHORT).show();
            } else if (addedProcessor.equals("Socket 1155") && !currProd.get(0).getListId().equals("MB Socket 1155")) {
                Toast.makeText(getApplicationContext(), "Dit Product is niet compatible", Toast.LENGTH_SHORT).show();
            } else if (addedProcessor.equals("Socket 2011") && !currProd.get(0).getListId().equals("MB Socket 2011")) {
                Toast.makeText(getApplicationContext(), "Dit Product is niet compatible", Toast.LENGTH_SHORT).show();
            } else if (addedMotherboard.equals("MB Socket AM3+") && !currProd.get(0).getListId().equals("Socket AM3+")) {
                Toast.makeText(getApplicationContext(), "Dit Product is niet compatible", Toast.LENGTH_SHORT).show();
            } else if (addedMotherboard.equals("MB Socket FM2+") && !currProd.get(0).getListId().equals("Socket FM2+")) {
                Toast.makeText(getApplicationContext(), "Dit Product is niet compatible", Toast.LENGTH_SHORT).show();
            } else if (addedMotherboard.equals("MB Socket 1150") && !currProd.get(0).getListId().equals("Socket 1150")) {
                Toast.makeText(getApplicationContext(), "Dit Product is niet compatible", Toast.LENGTH_SHORT).show();
            } else if (addedMotherboard.equals("MB Socket 1155") && !currProd.get(0).getListId().equals("Socket 1155")) {
                Toast.makeText(getApplicationContext(), "Dit Product is niet compatible", Toast.LENGTH_SHORT).show();
            } else if (addedMotherboard.equals("MB Socket 2011") && !currProd.get(0).getListId().equals("Socket 2011")) {
                Toast.makeText(getApplicationContext(), "Dit Product is niet compatible", Toast.LENGTH_SHORT).show();
            } else if (currProd.get(0).getListId().contains("MB")) {
                if (!addedMotherboard.equals("Empty")) {
                    Toast.makeText(getApplicationContext(), "U heeft al een moederbord toegevoegd", Toast.LENGTH_SHORT).show();
                } else {
                    MainActivity.addProduct(currProd.get(0));
                    datasource.addProduct(currProd.get(0));
                    Toast.makeText(getApplicationContext(), "Product is toegevoegd aan uw build", Toast.LENGTH_SHORT).show();
                }
            } else if (!currProd.get(0).getListId().contains("MB")) {
                if (!addedProcessor.equals("Empty")) {
                    Toast.makeText(getApplicationContext(), "U heeft al een processor toegevoegd", Toast.LENGTH_SHORT).show();
                } else {
                    MainActivity.addProduct(currProd.get(0));
                    datasource.addProduct(currProd.get(0));
                    Toast.makeText(getApplicationContext(), "Product is toegevoegd aan uw build", Toast.LENGTH_SHORT).show();
                }
            }
        }
        else{
            MainActivity.addProduct(currProd.get(0));
            datasource.addProduct(currProd.get(0));
            Toast.makeText(getApplicationContext(), "Product is toegevoegd aan uw build", Toast.LENGTH_SHORT).show();
        }
    }

    public void removeFromBuild(){
        MyComputer.deleteProduct(pid);
        MainActivity.deleteProduct(pid);
        ProductDataSource datasource = new ProductDataSource(getApplicationContext());
        datasource.open();
        datasource.deleteProduct(pkid);
        if(currProd.get(0).getListId().contains("MB")){
            MainActivity.setAddedMotherboard("Empty");
        }
        if(currProd.get(0).getListId().contains("Socket") && !currProd.get(0).getListId().contains("MB")){
            MainActivity.setAddedProcessor("Empty");
        }
    }

    public void changeButtonSpecs(){
        Button btn = (Button) findViewById(R.id.btnAddToBuild);
        btn.setText("Verwijder uit build");
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Product is verwijderd", Toast.LENGTH_SHORT).show();
                removeFromBuild();
            }
        });
    }
}