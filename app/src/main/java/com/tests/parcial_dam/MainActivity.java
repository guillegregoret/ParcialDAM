package com.tests.parcial_dam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void onClickSiguiente(View view) {
        Intent mapaIntent = new Intent(this, MapActivity.class);
        startActivity(mapaIntent);
    }
}