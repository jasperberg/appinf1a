package com.DDB.hardwire.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.DDB.R;

import java.util.ArrayList;


public class MainActivity extends Activity {

    static ArrayList<product> build = new ArrayList<product>();
    private static String buildName = "Click to change name";
    static String addedProcessor = "Empty";
    static String addedMotherboard = "Empty";

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

    public static void indexAddedProducts(){
        for(int i = 0; i < build.size(); i++){
            if(build.get(i).getListId()=="Socket AM3+"){
                addedProcessor = "Socket AM3+";
            }
            else if(build.get(i).getListId()=="Socket FM2+"){
                addedProcessor = "Socket FM2+";
            }
            else if(build.get(i).getListId()=="Socket 1150"){
                addedProcessor = "Socket 1150";
            }
            else if(build.get(i).getListId()=="Socket 1155"){
                addedProcessor = "Socket 1155";
            }
            else if(build.get(i).getListId()=="Socket 2011"){
                addedProcessor = "Socket 2011";
            }
            else if(build.get(i).getListId()=="MB Socket AM3+"){
                addedMotherboard = "MB Socket AM3+";
            }
            else if(build.get(i).getListId()=="MB Socket FM2+"){
                addedMotherboard = "MB Socket FM2+";
            }
            else if(build.get(i).getListId()=="MB Socket 1150"){
                addedMotherboard = "MB Socket 1150";
            }
            else if(build.get(i).getListId()=="MB Socket 1155"){
                addedMotherboard = "MB Socket 1155";
            }
            else if(build.get(i).getListId()=="MB Socket 2011"){
                addedMotherboard = "MB Socket 2011";
            }
        }
    }

    public static String getAddedProcessor(){
        return addedProcessor;
    }

    public static String getAddedMotherboard(){
        return addedMotherboard;
    }
}
