package com.example.confianza;

import static android.provider.Contacts.SettingsColumns.KEY;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class desencActivity extends AppCompatActivity {
    EditText et1, et2;
    TextView tv;
    Button bt1,bt2;
    ImageView imageView;
    private static final String ALGORITHM = "DES";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desenc);
        et1 = findViewById(R.id.msgdesenc);
        et2 = findViewById(R.id.keydesenc);
        tv = findViewById(R.id.ansdesenc);
        bt1 = findViewById(R.id.btndesenc);
        bt2 = findViewById(R.id.btndesdecc);
        imageView = findViewById(R.id.imageViewdesd);
        et1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                et1.setBackground(getDrawable(R.drawable.newborder));
            }
        });
        et2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                et2.setBackground(getDrawable(R.drawable.newborder));
            }
        });
        bt1.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                String msg = et1.getText().toString();
                String ki = et2.getText().toString();

                String message = msg;
                String passphrase = ki;

                // Generate a DES key from the passphrase
                byte[] salt = {1, 2, 3, 4, 5, 6, 7, 8};
                SecretKeyFactory factory = null;
                try {
                    factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
                KeySpec spec = new PBEKeySpec(passphrase.toCharArray(), salt, 65536, 64);
                SecretKey tmp = null;
                try {
                    tmp = factory.generateSecret(spec);
                } catch (InvalidKeySpecException e) {
                    e.printStackTrace();
                }
                SecretKey secretKey = new SecretKeySpec(tmp.getEncoded(), "DES");

                // Initialize the Cipher object for encryption
                Cipher cipher = null;
                try {
                    cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                } catch (NoSuchPaddingException e) {
                    e.printStackTrace();
                }
                try {
                    cipher.init(Cipher.ENCRYPT_MODE, secretKey);
                } catch (InvalidKeyException e) {
                    e.printStackTrace();
                }

                // Encrypt the message and encode to base64 string
                byte[] encryptedMessage = new byte[0];
                try {
                    encryptedMessage = cipher.doFinal(message.getBytes());
                } catch (BadPaddingException e) {
                    e.printStackTrace();
                } catch (IllegalBlockSizeException e) {
                    e.printStackTrace();
                }
                String base64Encoded = Base64.getEncoder().encodeToString(encryptedMessage);
                System.out.println("Encrypted message (base64): " + base64Encoded);
                tv.setText(base64Encoded);
                // Initialize the Cipher object for decryption
                try {
                    cipher.init(Cipher.DECRYPT_MODE, secretKey);
                } catch (InvalidKeyException e) {
                    e.printStackTrace();
                }

                // Decrypt the message
                byte[] decryptedMessage = new byte[0];
                try {
                    decryptedMessage = cipher.doFinal(Base64.getDecoder().decode(base64Encoded));
                } catch (BadPaddingException e) {
                    e.printStackTrace();
                } catch (IllegalBlockSizeException e) {
                    e.printStackTrace();
                }
                System.out.println("Decrypted message: " + new String(decryptedMessage));
            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clipData = ClipData.newPlainText("Copy to Clipboard",tv.getText().toString());
                clipboardManager.setPrimaryClip(clipData);
                Toast.makeText(desencActivity.this, "Cipher text is copied!", Toast.LENGTH_SHORT).show();
            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(desencActivity.this,desdecActivity.class);
                in.putExtra("key",et2.getText().toString());
                in.putExtra("text",et1.getText().toString());
                startActivity(in);
            }
        });
    }
}
