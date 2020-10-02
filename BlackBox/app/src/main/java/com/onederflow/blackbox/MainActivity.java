package com.onederflow.blackbox;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        SharedPreferences myPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor myEditor = myPreferences.edit();
        String wts = myPreferences.getString("WhatToStart", "first");
        Toast.makeText(getApplicationContext(), "load = "  + wts, Toast.LENGTH_SHORT)
                .show();
        if(wts.equals("first")){
            if(isOnline()){
                wts = "web";
            } else{
                wts = "game";
            }
            myEditor.putString("WhatToStart", wts);
            myEditor.commit();
        }
        Toast.makeText(getApplicationContext(), "final = "  + wts, Toast.LENGTH_SHORT)
                .show();

        if (wts.equals("web")) {
            Intent intent = new Intent(MainActivity.this, WebViewActivity.class);
            startActivity(intent);
        }
        if (wts.equals("game")) {
            Intent intent = new Intent(MainActivity.this, GameActivity.class);
            startActivity(intent);
        }

        super.onCreate(savedInstanceState);
    }

    protected boolean isOnline() {
        String cs = Context.CONNECTIVITY_SERVICE;
        ConnectivityManager cm = (ConnectivityManager)
                getSystemService(cs);
        if (cm.getActiveNetworkInfo() == null) {
            return false;
        } else {
            return true;
        }
    }
}

