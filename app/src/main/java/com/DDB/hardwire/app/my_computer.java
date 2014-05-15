package com.DDB.hardwire.app;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.DDB.R;

import java.util.ArrayList;

public class my_computer extends Activity {

    private String buildName;
    ArrayList<product> build = new ArrayList<product>();
    ListView myComputerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Your saved build");
        setContentView(R.layout.activity_my_computer);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        build = MainActivity.getBuild();
        myComputerView = (ListView) findViewById(R.id.computerListView);
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
            case R.id.action_settings:
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
        TextView name = (TextView) findViewById(R.id.computerName);
        name.setText(Name);
        MainActivity.setBuildName(Name);
    }

    private void populateList(){
        ArrayAdapter<product> adapter = new myComputerAdapter();
        myComputerView.setAdapter(adapter);
        TextView title = (TextView) findViewById(R.id.computerName);
        title.setText(MainActivity.getBuildName());
    }

    private class myComputerAdapter extends ArrayAdapter<product> {
        public myComputerAdapter(){
            super (my_computer.this, R.layout.product, build);
        }

        @Override
        public View getView(int position, View view, ViewGroup parent) {
            if (view == null)
                view = getLayoutInflater().inflate(R.layout.product, parent, false);

            product prod = build.get(position);

            TextView name = (TextView) view.findViewById(R.id.productTitle);
            name.setText(prod.getProductName());
            TextView price = (TextView) view.findViewById(R.id.productPrice);
            price.setText("€"+prod.getProductPrice());
            return view;
        }
    }

    public void changeName(View view){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);

        alert.setTitle("Rename");
        alert.setMessage("What do you want to name your build?");

        final EditText input = new EditText(this);
        alert.setView(input);

        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                String value = input.getText().toString();
                my_computer.this.setBuildName(value);
            }
        });

        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // Canceled.
            }
        });

        alert.show();
    }
}
