package com.example.projectlast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class orderSuccessfull extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_successfull);
    }

    public void nextCus(View view) {
        Intent intent2 = new Intent(orderSuccessfull.this , WelcomeScreen.class);
        startActivity(intent2);

    }
}
