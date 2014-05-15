package com.DDB.hardwire.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import com.DDB.R;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
}
