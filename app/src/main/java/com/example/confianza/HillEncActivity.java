package com.example.confianza;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.util.Arrays;
import java.util.Locale;

public class HillEncActivity extends AppCompatActivity {
    EditText msg,key;
    Button button;
    TextView textView;
    ImageView imageView;
    Drawable drawable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hill_enc);
        msg = findViewById(R.id.msghillenc);
        key = findViewById(R.id.keyhillenc);
        button = findViewById(R.id.button2);
        textView = findViewById(R.id.textView4);
        imageView = findViewById(R.id.imageView6);
        drawable = getDrawable(R.drawable.newborder);
        msg.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                msg.setBackground(drawable);
            }
        });
        key.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                key.setBackground(drawable);
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = msg.getText().toString().toLowerCase(Locale.ROOT);
                int keys = Integer.parseInt(key.getText().toString());
                char[][] rail = new char[keys][message.length()];
                for (int i = 0; i < keys; i++)
                    Arrays.fill(rail[i], '\n');
                boolean dirDown = false;
                int row = 0, col = 0;
                for (int i = 0; i < message.length(); i++) {
                    if (row == 0 || row == keys - 1)
                        dirDown = !dirDown;
                    rail[row][col++] = message.charAt(i);
                    if (dirDown)
                        row++;
                    else
                        row--;
                }
                StringBuilder result = new StringBuilder();
                for (int i = 0; i < keys; i++)
                    for (int j = 0; j < message.length(); j++)
                        if (rail[i][j] != '\n')
                            result.append(rail[i][j]);
                textView.setText(result.toString());
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