package com.DDB.hardwire.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.DDB.R;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity {

    static List<Product> build = new ArrayList<Product>();
    static String addedProcessor = "Empty";
    static String addedMotherboard = "Empty";
    public ProductDataSource datasource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        datasource = new ProductDataSource(this);
        datasource.open();
        build = datasource.getAllProducts();
        haveNetworkConnection();
        Context context = getApplicationContext();
        if (!haveNetworkConnection()){
            CharSequence text = "Geen internetconnectie, producten kunnen niet geladen worden";
            int duration = Toast.LENGTH_LONG;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.settings:
                Intent intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    private boolean haveNetworkConnection() {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;

        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();
        for (NetworkInfo ni : netInfo) {
            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                if (ni.isConnected())
                    haveConnectedWifi = true;
            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                if (ni.isConnected())
                    haveConnectedMobile = true;
        }
        return haveConnectedWifi || haveConnectedMobile;
    }

    public void sendMessage(View view){
        Intent intent = new Intent(MainActivity.this, PcBuilder.class);
        startActivity(intent);
    }

    public void openGuides(View view){
        Intent intent = new Intent(MainActivity.this, GuideChooser.class);
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

    public static List<Product> getBuild() {
        return build;
    }

    public static void deleteBuild(){
        build.clear();
        indexAddedProducts();
    }

    public static void deleteProduct(int productId){
        for(int i = 0; i < build.size(); i++){
            if(build.get(i).getId() == productId){
                build.remove(i);
            }
        }
    }

    public static void indexAddedProducts(){
        addedProcessor = "Empty";
        addedMotherboard = "Empty";
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

    public void openAd(View view){
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.alternate.nl/html/product/information/page.html?articleId=1116135&channel_code=763&product_code=89988869&utm_source=publitas"));
        startActivity(browserIntent);
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
