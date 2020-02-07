package com.example.qr;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    Button Generatebutton,Scanbutton;
    ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        Generatebutton = findViewById(R.id.Generatebutton);
        Scanbutton = findViewById(R.id.Scanbutton);
        imageView= findViewById(R.id.imageView);

        Generatebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String data = editText.getText().toString();
                if (data.isEmpty()) {
                    editText.setError("Value required!");
                } else {

                    //QRGEncoder
                    // Initializing the QR Encoder with your value to be encoded, type you required and Dimension
                    QRGEncoder qrgEncoder = new QRGEncoder(data, null, QRGContents.Type.TEXT, 200);

                    try {
                        // Getting QR-Code as Bitmap
                        Bitmap qrbits = qrgEncoder.getBitmap();
                        // Setting Bitmap to ImageView
                        imageView.setImageBitmap(qrbits);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });



    }
}
