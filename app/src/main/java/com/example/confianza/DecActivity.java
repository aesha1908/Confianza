package com.example.confianza;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DecActivity extends AppCompatActivity {
    Button desym,deasym;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dec);
        desym = findViewById(R.id.decsym);
        deasym = findViewById(R.id.decasym);
        desym.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DecActivity.this,AskDecSymActivity.class);
                startActivity(intent);
            }
        });
        deasym.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DecActivity.this,AskDecAsymActivity.class);
                startActivity(intent);
            }
        });
    }
}