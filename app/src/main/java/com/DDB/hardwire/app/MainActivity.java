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
    private static String buildName = "Click to change name";

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

    public static void addProduct(product product){
        build.add(product);
    }

    public static ArrayList<product> getBuild(){
        return build;
    }

    public static void setBuildName(String Name){
        buildName = Name;
    }

    public static String getBuildName(){
        return buildName;
    }

    public static void deleteBuild(){
        build.clear();
    }
}
