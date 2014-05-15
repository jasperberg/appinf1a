package com.DDB.hardwire.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import com.DDB.R;

import java.util.ArrayList;


public class MainActivity extends Activity {

    static ArrayList<product> build = new ArrayList<product>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

    public void sendMessage(View view){
        Intent intent = new Intent(MainActivity.this, pb_builder.class);
        startActivity(intent);
    }

    public void openMyComputer(View view){
        Intent intent = new Intent(MainActivity.this, my_computer.class);
        startActivity(intent);
    }
    public void openAbout(View view)
    {
        Intent intent = new Intent(MainActivity.this, about.class);
        startActivity(intent);
    }

    public static void addProduct(product product){
        build.add(product);
    }
}
