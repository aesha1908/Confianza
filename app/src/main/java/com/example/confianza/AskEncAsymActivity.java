package com.example.confianza;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class AskEncAsymActivity extends AppCompatActivity {
    Spinner spinner;
    Button button;
    String[] types = {"Choose Algorithm","RSA Algorithm","Digital Signature"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask_enc_asym);
        spinner = findViewById(R.id.Asymalgo);
        button = findViewById(R.id.btngo);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, com.airbnb.lottie.R.layout.support_simple_spinner_dropdown_item,types);
        adapter.setDropDownViewResource(com.airbnb.lottie.R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = spinner.getSelectedItem().toString();
                if(text.equals("Choose Algorithm"))
                {
                    Toast.makeText(AskEncAsymActivity.this, "Choose Algorithm to encrypt the message", Toast.LENGTH_SHORT).show();
                }
                else if(text.equals("RSA Algorithm"))
                {
                    Intent intent = new Intent(AskEncAsymActivity.this,RSAEncActivity.class);
                    startActivity(intent);
                }
                else if(text.equals("Digital Signature"))
                {
                    Intent intent = new Intent(AskEncAsymActivity.this,DSEncActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}