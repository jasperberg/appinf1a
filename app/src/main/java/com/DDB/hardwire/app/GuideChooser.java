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
    }

    public void openBuyGuide(View view){
        Intent intent = new Intent(this, Guides.class);
        intent.putExtra("id", 0);
        startActivity(intent);
    }

    public void componentGuide(View view){
        Intent intent = new Intent(this, Guides.class);
        intent.putExtra("id", 1);
        startActivity(intent);
    }

    public void pcTypeDialog(View view){
        final CharSequence[] choices = { "Gaming Pc", "Editing Pc", "Internet Pc", "Werk Pc", "Geen voorkeur"};
        SharedPreferences settings = getSharedPreferences("pcType", MODE_PRIVATE);
        final SharedPreferences.Editor editor = settings.edit();

        AlertDialog.Builder alert = new AlertDialog.Builder(this);

        alert.setTitle("Selecteer Pc type");

        alert.setItems(choices, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch(which){
                    case 0:
                        editor.putString("pcType", "Game");
                        break;
                    case 1:
                        editor.putString("pcType", "Editing");
                        break;
                    case 2:
                        editor.putString("pcType", "Internet");
                        break;
                    case 3:
                        editor.putString("pcType", "Werk");
                        break;
                    case 4:
                        editor.putString("pcType", "Geen");
                }
            }

        });

        //zet nog niet de pref!!!!!!

        editor.commit();
        alert.create().show();
    }
}
