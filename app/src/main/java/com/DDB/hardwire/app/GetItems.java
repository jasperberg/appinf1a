package com.DDB.hardwire.app;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jasper on 23-May-14.
 */
public class GetItems extends AsyncTask<Void, Void, Void> {
    private ProgressDialog pDialog;
    static List<Product> productLister = new ArrayList<Product>();

    // URL to get contacts JSON
    private static String url = "http://student.hro.nl/0874096/products.json";

    // JSON Node names
    private static final String TAG_PRODUCTS = "products";
    private static final String TAG_ID = "id";
    private static final String TAG_PRODUCTNAME = "productName";
    private static final String TAG_PRODUCTDESCRIPTION = "productDescription";
    private static final String TAG_PRODUCTPRICE = "productPrice";
    private static final String TAG_LISTID = "listid";
    private static final String TAG_PICTURE = "pictureUrl";


    JSONArray products = null;
    @Override
    protected Void doInBackground(Void... arg0) {
        // Creating service handler class instance
        ServiceHandler sh = new ServiceHandler();
        productLister.clear();
        // Making a request to url and getting response
        String jsonStr = sh.makeServiceCall(url, ServiceHandler.GET);

        Log.d("Response: ", "> " + jsonStr);

        if (jsonStr != null) {
            try {
                JSONObject jsonObj = new JSONObject(jsonStr);

                // Getting JSON Array node
                products = jsonObj.getJSONArray(TAG_PRODUCTS);

                // door alle producten loopen
                for (int i = 0; i < products.length(); i++) {
                    JSONObject c = products.getJSONObject(i);

                    int id = c.getInt(TAG_ID);
                    String productName = c.getString(TAG_PRODUCTNAME);
                    String listId = c.getString(TAG_LISTID);
                    String productDescription = c.getString(TAG_PRODUCTDESCRIPTION);
                    double productPrice = c.getDouble(TAG_PRODUCTPRICE);
                    String pictureUrl = c.getString(TAG_PICTURE);
                    Bitmap picture = getBitmapFromURL(pictureUrl);
                    Product p = new Product(listId, id, productName, productDescription, productPrice, picture);
                    productLister.add(p);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            Log.e("ServiceHandler", "Couldn't get any data from the url");
        }

        return null;
    }

    public static List<Product> getProductLister(){
        return productLister;
    }


    public static Bitmap getBitmapFromURL(String src) {
        try {
            Log.e("src", src);
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            Log.e("Bitmap","returned");
            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("Exception",e.getMessage());
            return null;
        }
    }
}
