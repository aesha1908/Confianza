package com.example.confianza;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.math.BigInteger;
import java.util.Random;

public class RSADecActivity extends AppCompatActivity {
    EditText msg, p1, q1;
    Button btn;
    TextView tv, prk;
    BigInteger p, q, phi, e, d, n;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rsadec);
        msg = findViewById(R.id.msgrsadec);
        p1 = findViewById(R.id.prsadec);
        q1 = findViewById(R.id.qrsadec);
        btn = findViewById(R.id.btnrsadec);
        prk = findViewById(R.id.publicdec);
        tv = findViewById(R.id.ansdecrsa);
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
                String res = msg.getText().toString();
                System.out.println("P:");
                p = new BigInteger(p1.getText().toString());
                System.out.println("Q:");
                q = new BigInteger(q1.getText().toString());
                n = p.multiply(q);
                phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
                e = BigInteger.probablePrime(bitLength/2, new Random());
                while (phi.gcd(e).compareTo(BigInteger.ONE) > 0 && e.compareTo(phi) < 0) {
                    e.add(BigInteger.ONE);
                }
                d = e.modInverse(phi);
                prk.setText("Key for decryption: "+d);
                String[] encryptedParts = res.toString().trim().split("\\s+");
                StringBuilder decryptedMessage = new StringBuilder();
                for (String encryptedPart : encryptedParts) {
                    BigInteger encryptedValue = new BigInteger(encryptedPart);
                    int decryptedValue = decrypt(encryptedValue).intValue();
                    char decryptedChar = (char) (decryptedValue - 1 + 'a');
                    decryptedMessage.append(decryptedChar);
                }
                tv.setText(decryptedMessage.toString());
            }
        });
    }
    public BigInteger decrypt(BigInteger message) {
        return message.modPow(d, n);
    }
}