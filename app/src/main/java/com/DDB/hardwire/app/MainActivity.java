package com.DDB.hardwire.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.DDB.R;

import java.util.ArrayList;


public class MainActivity extends Activity {

    static ArrayList<Product> build = new ArrayList<Product>();
    private static String buildName = "Click to change name";
    static String addedProcessor = "Empty";
    static String addedMotherboard = "Empty";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new GetItems().execute();
    }

    public void sendMessage(View view){
        Intent intent = new Intent(MainActivity.this, PcBuilder.class);
        startActivity(intent);
    }

    public void openMyComputer(View view){
        Intent intent = new Intent(MainActivity.this, MyComputer.class);
        startActivity(intent);
    }
    public void openAbout(View view)
    {
        Intent intent = new Intent(MainActivity.this, About.class);
        startActivity(intent);
    }

    public static void addProduct(Product product){
        build.add(product);
    }

    public static ArrayList<Product> getBuild(){
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
            if(build.get(i).getListId().equals("Socket AM3+")){
                addedProcessor = "Socket AM3+";
            }
            else if(build.get(i).getListId().equals("Socket FM2+")){
                addedProcessor = "Socket FM2+";
            }
            else if(build.get(i).getListId().equals("Socket 1150")){
                addedProcessor = "Socket 1150";
            }
            else if(build.get(i).getListId().equals("Socket 1155")){
                addedProcessor = "Socket 1155";
            }
            else if(build.get(i).getListId().equals("Socket 2011")){
                addedProcessor = "Socket 2011";
            }
            else if(build.get(i).getListId().equals("MB Socket AM3+")){
                addedMotherboard = "MB Socket AM3+";
            }
            else if(build.get(i).getListId().equals("MB Socket FM2+")){
                addedMotherboard = "MB Socket FM2+";
            }
            else if(build.get(i).getListId().equals("MB Socket 1150")){
                addedMotherboard = "MB Socket 1150";
            }
            else if(build.get(i).getListId().equals("MB Socket 1155")){
                addedMotherboard = "MB Socket 1155";
            }
            else if(build.get(i).getListId().equals("MB Socket 2011")){
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

    public static void setAddedProcessor(String proc){
        addedProcessor = proc;
    }

    public static void setAddedMotherboard(String mod){
        addedProcessor = mod;
    }
}
