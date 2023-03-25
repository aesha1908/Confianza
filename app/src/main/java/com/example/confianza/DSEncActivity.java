package com.example.confianza;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;

public class DSEncActivity extends AppCompatActivity {
    EditText et1;
    ImageView imgbut;
    Button btn, verify;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dsenc);
        et1 = findViewById(R.id.editTextTextPersonName);
        imgbut = findViewById(R.id.imageButton);
        btn = findViewById(R.id.btnsign);
        tv = findViewById(R.id.signdsenc);
        verify = findViewById(R.id.btnver);
        et1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                et1.setBackground(getDrawable(R.drawable.newborder));
            }
        });
        final String[] signtosend = new String[1];
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String plaintext = et1.getText().toString();

                try {
                    // Generate key pair
                    KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
                    keyGen.initialize(2048);
                    KeyPair keyPair = keyGen.generateKeyPair();
                    PrivateKey privateKey = keyPair.getPrivate();
                    PublicKey publicKey = keyPair.getPublic();

                    // Create signature
                    Signature signature = Signature.getInstance("SHA256withRSA");
                    signature.initSign(privateKey);
//                    System.out.println("Enter you text here");

                    String text = plaintext;
                    signature.update(text.getBytes());
                    byte[] digitalSignature = signature.sign();
                    System.out.println(digitalSignature);
                    signtosend[0] = digitalSignature.toString();
                    tv.setText(digitalSignature.toString());

                    // Verify signature
                    signature.initVerify(publicKey);
                    signature.update(text.getBytes());
                    boolean isVerified = signature.verify(digitalSignature);
                    System.out.println("Signature verified: " + isVerified);
                    if (isVerified == true) {
                        System.out.println("in if statement");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                Intent init = new Intent(DSEncActivity.this, DSDecActivity.class);
                init.putExtra("public", plaintext);
            }
        });

        imgbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clipData = ClipData.newPlainText("TextView", tv.getText().toString());
                clipboardManager.setPrimaryClip(clipData);

                Toast.makeText(DSEncActivity.this, "Signature copied", Toast.LENGTH_SHORT).show();
            }
        });

        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DSEncActivity.this, DSDecActivity.class);
                intent.putExtra("message", et1.getText().toString());
                intent.putExtra("sign", signtosend[0]);
                startActivity(intent);
            }
        });
    }
}
