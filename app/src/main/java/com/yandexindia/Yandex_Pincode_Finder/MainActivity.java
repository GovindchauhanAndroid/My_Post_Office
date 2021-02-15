package com.yandexindia.Yandex_Pincode_Finder;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;


public class MainActivity extends AppCompatActivity {
    private EditText PostOfficeNameEdit, PostpinEdit;
    public RequestQueue mQueue;
    private String Postofficenamekey, PostofficePinKey, url;
    private final String BaseUrlName = "https://api.postalpincode.in/postoffice/";
    private final String BaseUrlPincode = "https://api.postalpincode.in/pincode/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button postofficename = findViewById(R.id.name_search);
        Button postofficePin = findViewById(R.id.Search_Pin);
        PostOfficeNameEdit = findViewById(R.id.bank_name);
        PostpinEdit = findViewById(R.id.pin_code);

        mQueue = Volley.newRequestQueue(this);
        postofficename.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(PostOfficeNameEdit.getText().toString())) {
                    Toast.makeText(MainActivity.this, "Please Enter Branch Name", Toast.LENGTH_SHORT).show();
                } else {
                    PostpinEdit.setText("");
                    Postofficenamekey = PostOfficeNameEdit.getText().toString();
                    url = BaseUrlName + Postofficenamekey;
                    Intent Postname = new Intent(MainActivity.this, BankName.class);
                    Postname.putExtra("MyData", url);
                    startActivity(Postname);
                }
            }
        });


        postofficePin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(PostpinEdit.getText().toString())) {
                    Toast.makeText(MainActivity.this, "Please Enter PinCode", Toast.LENGTH_SHORT).show();
                } else {
                    PostOfficeNameEdit.setText("");
                    PostofficePinKey = PostpinEdit.getText().toString();
                    url = BaseUrlPincode + PostofficePinKey;
                    Intent Pncode = new Intent(MainActivity.this, PinCode.class);
                    Pncode.putExtra("MyDat", url);
                    startActivity(Pncode);
                }

            }
        });


    }

}
