package com.example.qr;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.google.zxing.Result;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

public class Scanner extends AppCompatActivity {
    CodeScanner codeScanner;
    CodeScannerView ScannerView;
    TextView outputText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanner);

        ScannerView = findViewById(R.id.ScannerView);
        outputText = findViewById(R.id.outputText);

        codeScanner= new CodeScanner(this,ScannerView);

        codeScanner.setDecodeCallback(new DecodeCallback() {
            @Override
            public void onDecoded(@NonNull final Result result) {


                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        outputText.setText(result.getText().toString());

                    }
                });
            }
        });

        ScannerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                codeScanner.startPreview();
            }
        });



    }

    @Override
    protected void onResume() {
        super.onResume();

        requestForCamera();
       // codeScanner.startPreview();


    }

    private void requestForCamera() {

        Dexter.withActivity(this).withPermission(Manifest.permission.CAMERA).withListener(new PermissionListener() {
            @Override
            public void onPermissionGranted(PermissionGrantedResponse response) {

                codeScanner.startPreview();
            }

            @Override
            public void onPermissionDenied(PermissionDeniedResponse response) {

                Toast.makeText(getApplicationContext(),"APP needs permition to run QR",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {

                //use until the camera permition is accepted by user
                token.continuePermissionRequest();
            }
        }).check();
    }
}
