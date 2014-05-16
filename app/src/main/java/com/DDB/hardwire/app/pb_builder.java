package com.DDB.hardwire.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.DDB.R;

public class pb_builder extends Activity {

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
                inside_category productListViewer = new inside_category();
                productListViewer.setTitle(listDataHeader.get(groupPosition) + " - " + listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition));
                productListViewer.setCategory(listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition));
                closeList();
                Intent intent = new Intent(pb_builder.this, inside_category.class);
                startActivity(intent);
                return false;
            }
        });
    }

    public void closeList(){
        for(int i = 0; i < 6; i++)
        expListView.collapseGroup(i);
    }

    public static void prepareListData() {
        String addedProcessor = MainActivity.getAddedProcessor();
        String addedMotherboard = MainActivity.getAddedMotherboard();
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        listDataHeader.add("Processor");
        listDataHeader.add("Moederbord");
        listDataHeader.add("Videokaart");
        listDataHeader.add("Geheugen");
        listDataHeader.add("Opslag");
        listDataHeader.add("Behuizing");

        List<String> Processor = new ArrayList<String>();
        if(addedMotherboard == "Empty") {
            Processor.add("Socket AM3+");
            Processor.add("Socket FM2+");
            Processor.add("Socket 1150");
            Processor.add("Socket 1155");
            Processor.add("Socket 2011");
        }
        else if(addedMotherboard == "MB Socket AM3+"){
            Processor.add("MB Socket AM3+");
        }
        else if(addedMotherboard == "MB Socket FM2+"){
            Processor.add("MB Socket FM2+");
        }
        else if(addedMotherboard == "MB Socket 1150"){
            Processor.add("MB Socket 1150");
        }
        else if(addedMotherboard == "MB Socket 1155"){
            Processor.add("MB Socket 1155");
        }
        else if(addedMotherboard == "MB Socket 2011"){
            Processor.add("MB Socket 2011");
        }

        List<String> Moederbord = new ArrayList<String>();
        if(addedProcessor == "Empty") {
            Moederbord.add("MB Socket AM3+");
            Moederbord.add("MB Socket FM2+");
            Moederbord.add("MB Socket 1150");
            Moederbord.add("MB Socket 1155");
            Moederbord.add("MB Socket 2011");
        }
        else if(addedProcessor == "Socket AM3+"){
            Moederbord.add("MB Socket AM3+");
        }
        else if(addedProcessor == "Socket FM2+"){
            Moederbord.add("MB Socket FM2+");
        }
        else if(addedProcessor == "Socket 1150"){
            Moederbord.add("MB Socket 1150");
        }
        else if(addedProcessor == "Socket 1155"){
            Moederbord.add("MB Socket 1155");
        }
        else if(addedProcessor == "Socket 2011"){
            Moederbord.add("MB Socket 2011");
        }

        List<String> Videokaart = new ArrayList<String>();
        Videokaart.add("AMD");
        Videokaart.add("Nvidia");

        List<String> Geheugen = new ArrayList<String>();
        Geheugen.add("DDR2");
        Geheugen.add("DDR3");

        List<String> Opslag = new ArrayList<String>();
        Opslag.add("HDD");
        Opslag.add("SSD");

        List<String> Behuizing = new ArrayList<String>();
        Behuizing.add("ATX");
        Behuizing.add("M-ATX");
        Behuizing.add("u-ATX");

        listDataChild.put(listDataHeader.get(0), Processor); // Header, Child data
        listDataChild.put(listDataHeader.get(1), Moederbord);
        listDataChild.put(listDataHeader.get(2), Videokaart);
        listDataChild.put(listDataHeader.get(3), Geheugen);
        listDataChild.put(listDataHeader.get(4), Opslag);
        listDataChild.put(listDataHeader.get(5), Behuizing);
    }
}

