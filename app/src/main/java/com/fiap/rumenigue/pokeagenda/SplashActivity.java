package com.fiap.rumenigue.pokeagenda;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class SplashActivity extends AppCompatActivity {

    EditText etNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        etNumber = (EditText)findViewById(R.id.etNumber);
    }

    public void search(View view) {
        Intent i = new Intent(this, DetailActivity.class);
        i.putExtra("POKE_NUMBER", Integer.parseInt(etNumber.getText().toString()));
        startActivity(i);
    }
}
