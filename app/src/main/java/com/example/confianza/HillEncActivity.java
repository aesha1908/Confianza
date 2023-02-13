package com.example.confianza;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class HillEncActivity extends AppCompatActivity {
    EditText msg,key;
    Button button;
    TextView textView;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hill_enc);
        msg = findViewById(R.id.msghillenc);
        key = findViewById(R.id.keyhillenc);
        button = findViewById(R.id.button2);
        textView = findViewById(R.id.textView4);
        imageView= findViewById(R.id.imageView6);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = msg.getText().toString();
                String keys = key.getText().toString();
                int k = 0;
                int keyMatrix[][] = new int[3][3];
                for (int i = 0; i < 3; i++)
                {
                    for (int j = 0; j < 3; j++)
                    {
                        keyMatrix[i][j] = (keys.charAt(k)) % 65;
                        k++;
                    }
                }
                message = message.toUpperCase();
                int msgMatrix[][] = new int[3][1];
                int cipherMatrix[][] = new int[3][1];
                for(int p = 0;p < 3; p++)
                {
                    msgMatrix[p][0] = (message.charAt(p)) % 65;
                    for (int i = 0; i < 3; i++)
                    {
                        for (int j = 0; j < 1; j++)
                        {
                            cipherMatrix[i][j] = 0;
                            for (int x = 0; x < 3; x++)
                            {
                                cipherMatrix[i][j] += keyMatrix[i][x] * msgMatrix[x][j];
                            }
                            cipherMatrix[i][j] = cipherMatrix[i][j] % 26;
                        }
                    }
                }
                String cipher  = "";
                for(int i = 0; i < 3; i++)
                {
                    cipher += (char)(cipherMatrix[i][0] + 65);
                }
                textView.setText(cipher);
            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clipData = ClipData.newPlainText("Copy to Clipboard",textView.getText().toString());
                clipboardManager.setPrimaryClip(clipData);
                Toast.makeText(HillEncActivity.this, "Cipher text is copied!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}