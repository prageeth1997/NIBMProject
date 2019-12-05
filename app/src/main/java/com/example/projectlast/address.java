package com.example.projectlast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EdgeEffect;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class address extends AppCompatActivity {



   deliveryetails deliveryDetails_DB;
   ordersDB orders_DB;
    DatabaseReference pref;

    Button btn;
    EditText ed1 , ed2 , ed3 , ed4 , ed5;
    TextView msg1 , msg2 , msg3;

    String cA;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);

        deliveryDetails_DB = new deliveryetails();

        btn = findViewById(R.id.button2);
       ed1 = findViewById(R.id.editText7);
        ed2 = findViewById(R.id.editText5);
        ed3 = findViewById(R.id.editText3);
        ed4 = findViewById(R.id.editText6);
        ed5 = findViewById(R.id.editText4);

        msg1 = findViewById(R.id.textView17);
        msg2 = findViewById(R.id.textView18);
        msg3 = findViewById(R.id.textView20);


        btn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(ed1.getText().toString().equals("") ){

                    msg3.setVisibility(View.VISIBLE);
                    msg2.setVisibility(View.GONE);
                    msg1.setVisibility(View.GONE);
                }
                else {
                    if(ed2.getText().toString().equals("")&& ed3.getText().toString().equals("") && ed4.getText().toString().equals("") ){

                        msg1.setVisibility(View.VISIBLE);
                        msg2.setVisibility(View.GONE);
                        msg3.setVisibility(View.GONE);
                    }
                    else {
                        if(ed5.getText().toString().equals("")){
                            msg2.setVisibility(View.VISIBLE);
                            msg1.setVisibility(View.GONE);
                            msg3.setVisibility(View.GONE);
                        }
                        else {

                            DatabaseReference getCountO = FirebaseDatabase.getInstance().getReference().child("orderDB");
                            getCountO.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    long count1 = 0;
                                    int count2 = 0;
                                    count1 = dataSnapshot.getChildrenCount();
                                    String count = String.valueOf(count1);

                                    count2 = Integer.parseInt(count);

                                    count2 = count2;

                                    deliveryDetails_DB.setCount(String.valueOf(count2));

                                    String all = "Address  :" + ed1.getText().toString() +" , " + ed2.getText().toString() +" , " + ed3.getText().toString() ;
                                    String mo = "Mobile Number  :" + ed4.getText().toString();

                                    pref =  FirebaseDatabase.getInstance().getReference().child("deliveryDetails");
                                    deliveryDetails_DB.setDetails(all);
                                    deliveryDetails_DB.setMobileNu(mo);

                                    pref.push();
                                    pref.child(deliveryDetails_DB.getCount()).setValue(deliveryDetails_DB);

                                    Toast.makeText(address.this, "Order placed Successfully", Toast.LENGTH_SHORT).show();

                                    Intent intent2 = new Intent(address.this , orderSuccessfull .class);
                                    startActivity(intent2);





                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });
                        }
                    }
                }





            }
        });

    }


    public void reWrite1(View view){
        msg1.setVisibility(View.GONE);
    }

    public void reWrite2(View view){
        msg2.setVisibility(View.GONE);
    }
}
