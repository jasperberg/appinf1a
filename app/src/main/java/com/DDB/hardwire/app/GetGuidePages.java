package com.DDB.hardwire.app;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MarK on 25-Jun-14.
 * Getting the Guides from online JSON file and parsing the information to objects.
 */
public class GetGuidePages extends AsyncTask<Void, Void, Void> {
    static List<GuidePage> pageList = new ArrayList<GuidePage>();

    private static String url = "http://student.hro.nl/0874096/guides.json";

    private static final String TAG_PAGES = "PAGES";
    private static final String TAG_TABNAME = "tabName";
    private static final String TAG_TEXT = "text";


    JSONArray pages = null;
    @Override
    protected Void doInBackground(Void... arg0) {
        long start = System.nanoTime();
        ServiceHandler sh = new ServiceHandler();
        pageList.clear();
        String jsonStr = sh.makeServiceCall(url, ServiceHandler.GET);

        Log.d("Response: ", "> " + jsonStr);

        if (jsonStr != null) {
            try {
                JSONObject jsonObj = new JSONObject(jsonStr);

                pages = jsonObj.getJSONArray(TAG_PAGES);

                for (int i = 0; i < pages.length(); i++) {
                    JSONObject c = pages.getJSONObject(i);

                    String tabName = c.getString(TAG_TABNAME);
                    String text = c.getString(TAG_TEXT);
                    GuidePage p = new GuidePage(tabName, text);
                    pageList.add(p);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            Log.e("ServiceHandler", "Couldn't get any data from the url");
        }
        long elapsedTime = System.nanoTime() - start;
        System.out.println("Parsing took " + elapsedTime/1000000000 + " seconds.");
        return null;
    }

    public static List<GuidePage> getPageList() {
        return pageList;
    }
}
