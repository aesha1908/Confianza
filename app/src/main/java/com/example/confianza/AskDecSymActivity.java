package com.example.confianza;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class AskDecSymActivity extends AppCompatActivity {
    Spinner spinner;
    Button btn;
    RelativeLayout coordinatorLayout;
    String[] types = {"Choose Algorithm","Playfair Cipher","Hill Cipher"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask_dec_sym);
        spinner = findViewById(R.id.Symdecalgo);
        btn = findViewById(R.id.decbutton);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, com.airbnb.lottie.R.layout.support_simple_spinner_dropdown_item,types);
        adapter.setDropDownViewResource(com.airbnb.lottie.R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = spinner.getSelectedItem().toString();
                if(text.equals("Choose Algorithm"))
                {
                    Toast.makeText(AskDecSymActivity.this, "Choose algorithm to decrypt text",
                            Toast.LENGTH_SHORT).show();
                }
                else if(text.equals("Playfair Cipher"))
                {
                    Intent intent = new Intent(AskDecSymActivity.this,PlayfairDecActivity.class);
                    startActivity(intent);
                }
                else if(text.equals("Hill Cipher"))
                {
                    Intent intent = new Intent(AskDecSymActivity.this,HillDecActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}