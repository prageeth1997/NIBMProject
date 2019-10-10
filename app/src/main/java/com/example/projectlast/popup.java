package com.example.projectlast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class popup extends AppCompatActivity {

    TextView txtName , txtPrice , qty;

    String CountLast;

    Button btn;

    cartDB cart_DB;
    //DatabaseReference pref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup);

        txtName = findViewById(R.id.ItemName);
        txtPrice = findViewById(R.id.ItemPrice);
        qty = findViewById(R.id.qty);

        btn = findViewById(R.id.button4);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width * .8) , (int)(height * .6));


        Intent intGetVal = getIntent();
        txtName.setText(intGetVal.getStringExtra("ItemName"));
        txtPrice.setText(intGetVal.getStringExtra("ItemPrice"));

        cart_DB = new cartDB();


        DatabaseReference getCount = FirebaseDatabase.getInstance().getReference().child("cartDB");
        getCount.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                long count1= dataSnapshot.getChildrenCount();
                String count = String.valueOf(count1);

                int count2 = Integer.parseInt(count);

                count2 = count2 + 1;

                cart_DB.setCout(String.valueOf(count2));


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        btn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                String count1 = cart_DB.getCout();


                DatabaseReference pref =  FirebaseDatabase.getInstance().getReference().child("cartDB");
                cart_DB.setItemName(txtName.getText().toString());
                cart_DB.setItemPrice(txtPrice.getText().toString());
                cart_DB.setQty(qty.getText().toString());


                pref.push();
                pref.child(count1).setValue(cart_DB);

                Toast.makeText(popup.this, "Item Successfully Added", Toast.LENGTH_SHORT).show();
                finish();


            }
        });




    }


    public void increase(View view){
        String quantity1 = qty.getText().toString();
        int quantity2 = Integer.parseInt(quantity1);
        quantity2 = quantity2 + 1;

        qty.setText(String.valueOf(quantity2));
    }

    public void decrease(View view){

        String quantity1 = qty.getText().toString();
        int quantity2 = Integer.parseInt(quantity1);
        quantity2 = quantity2 - 1;

        qty.setText(String.valueOf(quantity2));
    }




}
