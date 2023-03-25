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

import java.math.BigInteger;
import java.util.Locale;
import java.util.Random;

public class RSAEncActivity extends AppCompatActivity {
    EditText msg, p1, q1;
    TextView puk, prk, encrsa;
    Button btn;
    ImageView imageView;
    BigInteger p, q, phi, e, d, n;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rsaenc);
        msg = findViewById(R.id.msgrsaenc);
        p1 = findViewById(R.id.prsaenc);
        q1 = findViewById(R.id.qrsaenc);
        puk = findViewById(R.id.publicenc);
        prk = findViewById(R.id.privateenc);
        btn = findViewById(R.id.btnrsaenc);
        encrsa = findViewById(R.id.ansencrsa);
        imageView = findViewById(R.id.imageView7);
        msg.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                msg.setBackground(getDrawable(R.drawable.newborder));
            }
        });
        p1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                p1.setBackground(getDrawable(R.drawable.newborder));
            }
        });
        q1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                q1.setBackground(getDrawable(R.drawable.newborder));
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int bitLength = 1024;
                System.out.println("P:");
                p = new BigInteger(p1.getText().toString());
                System.out.println("Q:");
                q = new BigInteger(q1.getText().toString());
                n = p.multiply(q);
                phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
                e = BigInteger.probablePrime(bitLength / 2, new Random());
                while (phi.gcd(e).compareTo(BigInteger.ONE) > 0 && e.compareTo(phi) < 0) {
                    e.add(BigInteger.ONE);
                }
                d = e.modInverse(phi);
               // RSAEncActivity rsa = new RSAEncActivity(bitLength);
                String message = msg.getText().toString().toLowerCase(Locale.ROOT);
                StringBuilder encryptedMessage = new StringBuilder();
                for (int i = 0; i < message.length(); i++) {
                    char c = message.charAt(i);
                    if (c >= 'a' && c <= 'z') {
                        int asciiValue = (int) c - 'a' + 1;
                        encryptedMessage.append(Encrypt(BigInteger.valueOf(asciiValue)).toString()).append(" ");
                    }
                }
                encrsa.setText(encryptedMessage.toString().trim());
            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clipData = ClipData.newPlainText("Copy to Clipboard",encrsa.getText().toString());
                clipboardManager.setPrimaryClip(clipData);
                Toast.makeText(RSAEncActivity.this, "Cipher text is copied!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private BigInteger Encrypt(BigInteger message) {
        return message.modPow(e,n);
    }

    static int gcd(int e, int fi) {
        if (e == 0) {
            return fi;
        } else {
            return gcd(fi % e, e);
        }
    }

    public static boolean isPrime(int n) {
        if (n <= 1) {
            return false;
        }
        for (int i = 2; i < Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}