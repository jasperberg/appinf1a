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
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Computer Builder");
        setContentView(R.layout.activity_pb_builder);

        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.lvExp);

        // preparing list data
        prepareListData();

        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);


        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition,int childPosition,long id) {
                inside_category product = new inside_category();
                product.setTitle(listDataHeader.get(groupPosition) + " - " + listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition));
                product.setCategory(listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition));
                Intent intent = new Intent(pb_builder.this, inside_category.class);
                startActivity(intent);
            return false;
            }
        });
    }

    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        listDataHeader.add("Processor");
        listDataHeader.add("Moederbord");
        listDataHeader.add("Videokaart");
        listDataHeader.add("Geheugen");
        listDataHeader.add("Opslag");
        listDataHeader.add("Behuizing");

        List<String> Processor = new ArrayList<String>();
        Processor.add("Socket AM3+");
        Processor.add("Socket FM2+");
        Processor.add("Socket 1150");
        Processor.add("Socket 1155");
        Processor.add("Socket 2011");

        List<String> Moederbord = new ArrayList<String>();
        Moederbord.add("Socket AM3+");
        Moederbord.add("Socket FM2+");
        Moederbord.add("Socket 1150");
        Moederbord.add("Socket 1155");
        Moederbord.add("Socket 2011");

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

