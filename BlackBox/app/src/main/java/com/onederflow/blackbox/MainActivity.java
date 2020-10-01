package com.onederflow.blackbox;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void openWebView(View view){
        Toast.makeText(this, "Вы открыли WebView?", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(MainActivity.this, WebViewActivity.class);
        startActivity(intent);
    }

    public void openGame(View view){
        Toast.makeText(this, "Вы открыли игру?", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(MainActivity.this, GameActivity.class);
        startActivity(intent);
    }
}

