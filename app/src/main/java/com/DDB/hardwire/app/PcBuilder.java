package com.DDB.hardwire.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.DDB.R;

public class PcBuilder extends Activity {

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    static List<String> listDataHeader;
    static HashMap<String, List<String>> listDataChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Computer Builder");
        setContentView(R.layout.activity_pb_builder);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        expListView = (ExpandableListView) findViewById(R.id.lvExp);
        prepareListData();
        MainActivity.indexAddedProducts();
        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);
        expListView.setAdapter(listAdapter);
        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition,int childPosition,long id) {
                InsideCategory productListViewer = new InsideCategory();
                productListViewer.setTitle(listDataHeader.get(groupPosition) + " - " + listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition));
                productListViewer.setCategory(listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition));
                Intent intent = new Intent(PcBuilder.this, InsideCategory.class);
                startActivity(intent);
                return false;
            }
        });
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

    public static void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        listDataHeader.add("Processor");
        listDataHeader.add("Moederbord");
        listDataHeader.add("Videokaart");
        listDataHeader.add("Geheugen");
        listDataHeader.add("Opslag");
        listDataHeader.add("Voeding");
        listDataHeader.add("Behuizing");

        List<String> Processor = new ArrayList<String>();
        Processor.add("Socket AM3+");
        Processor.add("Socket FM2+");
        Processor.add("Socket 1150");
        Processor.add("Socket 1155");
        Processor.add("Socket 2011");

        List<String> Moederbord = new ArrayList<String>();
        Moederbord.add("MB Socket AM3+");
        Moederbord.add("MB Socket FM2+");
        Moederbord.add("MB Socket 1150");
        Moederbord.add("MB Socket 1155");
        Moederbord.add("MB Socket 2011");

        List<String> Videokaart = new ArrayList<String>();
        Videokaart.add("AMD");
        Videokaart.add("Nvidia");

        List<String> Geheugen = new ArrayList<String>();
        Geheugen.add("DDR2");
        Geheugen.add("DDR3");

        List<String> Opslag = new ArrayList<String>();
        Opslag.add("HDD");
        Opslag.add("SSD");

        List<String> Voeding = new ArrayList<String>();
        Voeding.add("< 500 Watt");
        Voeding.add("> 500 Watt");

        List<String> Behuizing = new ArrayList<String>();
        Behuizing.add("ATX");
        Behuizing.add("u-ATX");

        listDataChild.put(listDataHeader.get(0), Processor); // Header, Child data
        listDataChild.put(listDataHeader.get(1), Moederbord);
        listDataChild.put(listDataHeader.get(2), Videokaart);
        listDataChild.put(listDataHeader.get(3), Geheugen);
        listDataChild.put(listDataHeader.get(4), Opslag);
        listDataChild.put(listDataHeader.get(5), Voeding);
        listDataChild.put(listDataHeader.get(6), Behuizing);
    }
}

