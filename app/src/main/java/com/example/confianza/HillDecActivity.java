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

import java.util.Arrays;
import java.util.Locale;

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
        button = findViewById(R.id.btnhilldec);
        textView = findViewById(R.id.textView7);
        editText1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                editText1.setBackground(getDrawable(R.drawable.newborder));
            }
        });
        editText2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText2.setBackground(getDrawable(R.drawable.newborder));
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cipher = editText1.getText().toString().toLowerCase(Locale.ROOT);
                int keys = Integer.parseInt(editText2.getText().toString());
                char[][] rail = new char[keys][cipher.length()];

                // filling the rail matrix to distinguish filled
                // spaces from blank ones
                for (int i = 0; i < keys; i++)
                    Arrays.fill(rail[i], '\n');

                // to find the direction
                boolean dirDown = true;

                int row = 0, col = 0;

                // mark the places with '*'
                for (int i = 0; i < cipher.length(); i++) {
                    // check the direction of flow
                    if (row == 0)
                        dirDown = true;
                    if (row == keys - 1)
                        dirDown = false;

                    // place the marker
                    rail[row][col++] = '*';

                    // find the next row using direction flag
                    if (dirDown)
                        row++;
                    else
                        row--;
                }

                // now we can construct the fill the rail matrix
                int index = 0;
                for (int i = 0; i < keys; i++)
                    for (int j = 0; j < cipher.length(); j++)
                        if (rail[i][j] == '*'
                                && index < cipher.length())
                            rail[i][j] = cipher.charAt(index++);

                StringBuilder result = new StringBuilder();

                row = 0;
                col = 0;
                for (int i = 0; i < cipher.length(); i++) {
                    // check the direction of flow
                    if (row == 0)
                        dirDown = true;
                    if (row == keys - 1)
                        dirDown = false;

                    // place the marker
                    if (rail[row][col] != '*')
                        result.append(rail[row][col++]);

                    // find the next row using direction flag
                    if (dirDown)
                        row++;
                    else
                        row--;
                }
                textView.setText(result.toString());
            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clipData = ClipData.newPlainText("Copy to Clipboard",textView.getText().toString());
                clipboardManager.setPrimaryClip(clipData);
                Toast.makeText(HillDecActivity.this, "plain text is copied!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}