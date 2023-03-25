package com.example.confianza;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;

public class DSDecActivity extends AppCompatActivity {
    EditText et1;
    Button btn;
    TextView tv,tv1;
    public int count=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dsdec);
        et1=findViewById(R.id.editTextTextPersonName2);
        btn=findViewById(R.id.button3);
        tv=findViewById(R.id.status);
        tv1 = findViewById(R.id.ptdsdec);
        et1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                et1.setBackground(getDrawable(R.drawable.newborder));
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = getIntent();
                    String plaintext1 = intent.getStringExtra("message");
                    String comparesign = intent.getStringExtra("sign");
                    KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
                    keyPairGen.initialize(2048);
                    KeyPair keyPair= keyPairGen.generateKeyPair();
                    PrivateKey privkey = keyPair.getPrivate();
                    Signature sign = Signature.getInstance("SHA256withRSA");
                    sign.initSign(privkey);
                    byte[] bytes = plaintext1.getBytes();
                    sign.update(bytes);
                    byte[] signature = sign.sign();
                    String finalsign = signature.toString();
                    System.out.println("finalsign is "+finalsign);
                    System.out.println("current is "+et1.getText().toString());
                    System.out.println("copied sign is "+comparesign);
                    if(comparesign.equals(et1.getText().toString())) {
                        tv1.setText(plaintext1);
                        tv.setText("Signature Verified");
                        count--;
                    } else {
                        System.out.println("Signature failed");
                        tv1.setText(" " +
                                "");
                        tv.setText("Invalid Signature");
                    }

                } catch (NoSuchAlgorithmException | InvalidKeyException e) {
                    e.printStackTrace();
                } catch (SignatureException e) {
                    e.printStackTrace();
                }

            }
        });

    }
}