package com.DDB.hardwire.app;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.DDB.R;

/**
 * Created by MarK on 11-Jun-14.
 */
public class Guides_page extends Fragment {
    int id;
    String text;

    public Guides_page(int id){
        this.id = id;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_page, container, false);
        TextView tv = (TextView) rootView.findViewById(R.id.page);
        text = Guides.getTextById(id);
        tv.setText(text);
        return rootView;
    }
}
