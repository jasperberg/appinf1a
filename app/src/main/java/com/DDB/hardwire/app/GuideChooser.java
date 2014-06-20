package com.DDB.hardwire.app;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.DDB.R;

public class GuideChooser extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide_chooser);
        getActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void openBuyGuide(View view){
        Intent intent = new Intent(this, Guides.class);
        intent.putExtra("id", 1);
        startActivity(intent);
    }

    public void componentGuide(View view) {
        Intent intent = new Intent(this, Guides.class);
        intent.putExtra("id", 0);
        startActivity(intent);
    }
}
