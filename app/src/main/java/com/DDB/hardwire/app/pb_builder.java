package com.DDB.hardwire.app;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.widget.ExpandableListView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.DDB.R;


public class pb_builder extends ActionBarActivity {

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pb_builder);

        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.lvExp);

        // preparing list data
        prepareListData();

        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);
    }

    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader.add("Processor");
        listDataHeader.add("Moederbord");
        listDataHeader.add("Videokaart");

        // Adding child data
        List<String> Processor = new ArrayList<String>();
        Processor.add("AMD");
        Processor.add("i7");

        List<String> Moederbord = new ArrayList<String>();
        Moederbord.add("Asus");
        Moederbord.add("Intel");

        List<String> Videokaart = new ArrayList<String>();
        Videokaart.add("AMD");
        Videokaart.add("Nvidea");

        listDataChild.put(listDataHeader.get(0), Processor); // Header, Child data
        listDataChild.put(listDataHeader.get(1), Moederbord);
        listDataChild.put(listDataHeader.get(2), Videokaart);
    }
}

