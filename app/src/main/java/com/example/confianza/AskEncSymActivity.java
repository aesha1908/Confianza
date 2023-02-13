package com.example.confianza;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class AskEncSymActivity extends AppCompatActivity {
    Spinner sp;
    Button btn;
    String[] symalgos = {"Choose Algorithm","Playfair Cipher","Hill Cipher"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask_enc_sym);
        sp = findViewById(R.id.Symalgo);
        btn = findViewById(R.id.button);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, com.airbnb.lottie.R.layout.support_simple_spinner_dropdown_item,symalgos);
        adapter.setDropDownViewResource(com.airbnb.lottie.R.layout.support_simple_spinner_dropdown_item);
        sp.setAdapter(adapter);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = sp.getSelectedItem().toString();
                if(text.equals("Choose Algorithm"))
                {
                    Toast.makeText(AskEncSymActivity.this, "Choose algorithm to encrypt the message", Toast.LENGTH_SHORT).show();
                }
                else if(text.equals("Playfair Cipher"))
                {
                    Intent intent = new Intent(AskEncSymActivity.this,PlayfairEncActivity.class);
                    startActivity(intent);
                }
                else if(text.equals("Hill Cipher"))
                {
                    Intent intent = new Intent(AskEncSymActivity.this,HillEncActivity.class);
                    startActivity(intent);
                }
            }
        });

    }
}