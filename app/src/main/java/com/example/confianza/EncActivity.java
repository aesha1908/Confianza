package com.example.confianza;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class EncActivity extends AppCompatActivity {
    Button symm,asymm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enc);
        symm = findViewById(R.id.sym);
        asymm = findViewById(R.id.asym);
        symm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EncActivity.this,AskEncSymActivity.class);
                startActivity(intent);
            }
        });
        asymm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EncActivity.this,AskEncAsymActivity.class);
                startActivity(intent);
            }
        });
    }
}