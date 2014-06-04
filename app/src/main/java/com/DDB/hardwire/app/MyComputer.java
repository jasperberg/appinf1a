package com.DDB.hardwire.app;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.DDB.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MyComputer extends Activity {

    String buildName;
    static List<Product> build = new ArrayList<Product>();
    ListView myComputerView;
    ProductDataSource datasource = new ProductDataSource(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        changeTitle();
        setContentView(R.layout.activity_my_computer);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        myComputerView = (ListView) findViewById(R.id.computerListView);
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
    public void onResume(){
        super.onResume();
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
            case R.id.edit_buid:
                Toast.makeText(getApplicationContext(), "Now editing", Toast.LENGTH_LONG).show();
                return true;
            case R.id.delete_build:
                deleteDialog();
                return true;
            case R.id.share_build:
                return true;
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
        ProductDataSource datasource = new ProductDataSource(getApplicationContext());
        datasource.open();
        build = datasource.getAllProducts();
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

            Product prod = build.get(position);

            TextView name = (TextView) view.findViewById(R.id.productTitle);
            name.setText(prod.getProductName());
            TextView price = (TextView) view.findViewById(R.id.productPrice);
            price.setText("€"+prod.getProductPrice());
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

    public static void deleteProduct(int productId){
        for(int i = 0; i < build.size(); i++){
            if(build.get(i).getId() == productId){
                build.remove(i);
            }
        }
    }
}
