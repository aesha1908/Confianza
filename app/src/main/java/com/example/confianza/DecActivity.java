package com.example.confianza;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DecActivity extends AppCompatActivity {
    CardView rail,play,rsa,ds,aes,des;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dec);
        rail = findViewById(R.id.railci);
        play = findViewById(R.id.playfairi);
        rsa = findViewById(R.id.rsaai);
        ds = findViewById(R.id.dssi);
        aes = findViewById(R.id.AESi);
        des = findViewById(R.id.DESi);
        rail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DecActivity.this,HillDecActivity.class);
                startActivity(intent);
            }
        });
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DecActivity.this,PlayfairDecActivity.class);
                startActivity(intent);
            }
        });
        rsa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DecActivity.this,RSADecActivity.class);
                startActivity(intent);
            }
        });
        ds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DecActivity.this,DSDecActivity.class);
                startActivity(intent);
            }
        });
        aes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DecActivity.this,aesdecActivity.class);
                startActivity(intent);
            }
        });
        des.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DecActivity.this,desdecActivity.class);
                startActivity(intent);
            }
        });
    }
}