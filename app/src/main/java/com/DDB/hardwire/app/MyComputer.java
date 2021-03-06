package com.DDB.hardwire.app;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.DDB.R;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by MarK on 29-May-14.
 * The code behind the computer build screen.
 */
public class MyComputer extends Activity {

    String buildName;
    double price = 0;
    static List<Product> build = new ArrayList<Product>();
    ListView myComputerView;
    TextView pcTypeView;
    ProductDataSource datasource = new ProductDataSource(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        changeTitle();
        setContentView(R.layout.activity_my_computer);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        myComputerView = (ListView) findViewById(R.id.computerListView);
        pcTypeView = (TextView) findViewById(R.id.pcType);
        populateList();
        myComputerView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent intent = new Intent(MyComputer.this, ProductView.class);
                int productid = build.get(position).getId();
                int pkid = build.get(position).getPkid();
                int[] array = {productid, pkid};
                intent.putExtra("Product", array);
                intent.putExtra("method", "true");
                startActivity(intent);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        if (GetItems.getProductLister().size() == 0) {
            new GetItems().execute();
        }
        if (GetGuidePages.getPageList().size() == 0) {
            new GetGuidePages().execute();
        }
        changeTitle();
        populateList();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_computer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.change_name:
                changeName();
                return true;
            case R.id.delete_build:
                deleteDialog();
                return true;
            case R.id.settings:
                Intent intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    public void deleteDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Confirm");
        builder.setMessage("Are you sure?");

        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "Build deleted", Toast.LENGTH_LONG).show();
                MainActivity.deleteBuild();
                datasource.open();
                datasource.deleteBuild();
                datasource.close();
                MainActivity.setAddedMotherboard("Empty");
                MainActivity.setAddedProcessor("Empty");
                build = MainActivity.getBuild();
                populateList();
                changeTitle();
                dialog.dismiss();
            }

        });

        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();
    }

    public void setBuildName(String Name) {
        buildName = Name;
        datasource.open();
        datasource.changeBuildName(Name);
        datasource.close();
        changeTitle();
    }

    public void populateList(){
        setBuildType();
        datasource.open();
        build = datasource.getAllProducts();
        datasource.close();
        calculatePrice();
        ArrayAdapter<Product> adapter = new myComputerAdapter();
        myComputerView.setAdapter(adapter);
        changeTitle();
    }

    private class myComputerAdapter extends ArrayAdapter<Product> {
        public myComputerAdapter(){
            super (MyComputer.this, R.layout.product, build);
        }

        @Override
        public View getView(int position, View view, ViewGroup parent) {
            if (view == null)
                view = getLayoutInflater().inflate(R.layout.product, parent, false);
            List<Product> Products;
            Products = GetItems.getProductLister();
            Product prod = build.get(position);
            Product thisProduct = new Product(null, 0, null, null, 0.0, null);
            for(int i = 0; i < Products.size(); i++){
                if(Products.get(i).getId() == prod.getId()){
                    thisProduct = Products.get(i);
                }
            }

            TextView name = (TextView) view.findViewById(R.id.productTitle);
            name.setText(thisProduct.getProductName());
            TextView price = (TextView) view.findViewById(R.id.productPrice);
            DecimalFormat df = new DecimalFormat("0.00##");
            String result = df.format(thisProduct.getProductPrice());
            price.setText("€"+result);
            ImageView picture = (ImageView) view.findViewById(R.id.productImage);
            picture.setImageBitmap(thisProduct.getPicture());
            return view;
        }
    }

    public void changeName(){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);

        alert.setTitle("Hernoemen");
        alert.setMessage("Hoe moet uw computer heten?");

        final EditText input = new EditText(this);
        alert.setView(input);

        alert.setPositiveButton("Accepteren", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                String value = input.getText().toString();
                MyComputer.this.setBuildName(value);
                ProductDataSource datasource = new ProductDataSource(getApplicationContext());
                datasource.open();
                datasource.changeBuildName(value);
                changeTitle();
            }
        });

        alert.setNegativeButton("Annuleer", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // Canceled.
            }
        });

        alert.show();
    }

    public void changeTitle(){
        datasource.open();
        buildName = datasource.getBuildName();
        setTitle(buildName);
        datasource.close();
    }

    public void calculatePrice(){
        double locprice = 0;
        for(int i = 0; i < build.size(); i++){
            locprice = locprice + build.get(i).getProductPrice();
        }
        price = locprice;
        TextView tv = (TextView) findViewById(R.id.buildPrice);
        DecimalFormat df = new DecimalFormat("0.00##");
        String result = df.format(price);
        tv.setText("Totaal prijs: €"+result);
    }

    public static void deleteProduct(int productId){
        for(int i = 0; i < build.size(); i++){
            if(build.get(i).getId() == productId){
                build.remove(i);
            }
        }
    }

    public void setBuildType(){
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        String type = sharedPrefs.getString("prefPcType", "");
        if(type.equals("Game")){
            pcTypeView.setText("Gaming");
        }
        else if(type.equals("Editing")){
            pcTypeView.setText("Video editing");
        }
        else if(type.equals("Internet")){
            pcTypeView.setText("Internet");
        }
        else if(type.equals("Work")){
            pcTypeView.setText("Work");
        }
        else{
            pcTypeView.setText("");
        }
    }
}
