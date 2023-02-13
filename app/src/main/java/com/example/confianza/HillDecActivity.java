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

public class HillDecActivity extends AppCompatActivity {
    EditText editText1,editText2;
    ImageView imageView;
    Button button;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hill_dec);
        editText1 = findViewById(R.id.msghilldec);
        editText2 = findViewById(R.id.keyhilldec);
        imageView = findViewById(R.id.imageView7);
        button = findViewById(R.id.button5);
        textView = findViewById(R.id.textView7);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = editText1.getText().toString();
                String keys = editText2.getText().toString();
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
                int msgMatrix[][] = new int[3][1];
                int plainMatrix[][] = new int[3][1];
                for(int p = 0;p < 3; p++)
                {
                    msgMatrix[p][0] = (message.charAt(p)) % 65;
                    for (int i = 0; i < 3; i++)
                    {
                        for (int j = 0; j < 1; j++)
                        {
                            plainMatrix[i][j] = 0;

                            for (int x = 0; x < 3; x++)
                            {
                                plainMatrix[i][j] +=
                                        keyMatrix[i][x] * msgMatrix[x][j];
                            }

                            plainMatrix[i][j] = plainMatrix[i][j] % 26;
                        }
                    }
                }
                String plain  = "";
                for(int i = 0; i < 3; i++)
                {
                    plain += (char)(plainMatrix[i][0] + 65);
                }
                textView.setText(plain);
            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clipData = ClipData.newPlainText("Copy to Clipboard",textView.getText().toString());
                clipboardManager.setPrimaryClip(clipData);
                Toast.makeText(HillDecActivity.this, "Cipher text is copied!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}