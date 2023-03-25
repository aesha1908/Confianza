package com.example.confianza;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class EncActivity extends AppCompatActivity {
    CardView rail,play,rsa,ds,aes,des;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enc);
        rail = findViewById(R.id.railc);
        play = findViewById(R.id.playfair);
        rsa = findViewById(R.id.rsaa);
        ds = findViewById(R.id.dss);
        aes = findViewById(R.id.AES);
        des = findViewById(R.id.DES);
        rail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EncActivity.this,HillEncActivity.class);
                startActivity(intent);
            }
        });
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EncActivity.this,PlayfairEncActivity.class);
                startActivity(intent);
            }
        });
        rsa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EncActivity.this,RSAEncActivity.class);
                startActivity(intent);
            }
        });
        ds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EncActivity.this,DSEncActivity.class);
                startActivity(intent);
            }
        });
        aes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EncActivity.this,aesActivity.class);
                startActivity(intent);
            }
        });
        des.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EncActivity.this,desencActivity.class);
                startActivity(intent);
            }
        });
    }
}