package com.example.projectlast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class cart extends AppCompatActivity {


    cartDB cart_DB;
    ordersDB orders_DB;
    DatabaseReference readref;

    Button btnSend;

    TextView msg , msg2;
    ProgressBar prg;
    Button shpN;
    ConstraintLayout cl;
    ImageView img;

    float price = 0;
    int itemCount = 1 ;
    int innerOrderCount = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        btnSend = findViewById(R.id.button5);

        msg = findViewById(R.id.textView10);
        prg = findViewById(R.id.progressBar);
        shpN = findViewById(R.id.button3);
        cl = findViewById(R.id.cL1);
        img = findViewById(R.id.imageView27);
        msg2 = findViewById(R.id.textView12);
        cart_DB = new cartDB();

        DatabaseReference getCount = FirebaseDatabase.getInstance().getReference().child("cartDB");
        getCount.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                long count1= dataSnapshot.getChildrenCount();
                String count = String.valueOf(count1);
                for ( int c = 1 ; c <=  Integer.parseInt(count) ; c++){
                    DatabaseReference getDetails = FirebaseDatabase.getInstance().getReference().child("cartDB").child(String.valueOf(c));
                    getDetails.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if(dataSnapshot.hasChildren()){
                               String name = (dataSnapshot.child("itemName").getValue().toString());
                                String price = (dataSnapshot.child("itemPrice").getValue().toString());
                                String qty2 = (dataSnapshot.child("qty").getValue().toString());
                                showOrderElement(  name , price , qty2);
                                prg.setVisibility(View.GONE);
                                cl.setVisibility(View.VISIBLE);

                            }
                            else {
                                prg.setVisibility(View.GONE);
                                msg.setVisibility(View.VISIBLE);
                                shpN.setVisibility(View.VISIBLE);

                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        DatabaseReference getDetails = FirebaseDatabase.getInstance().getReference().child("cartDB");
        getDetails.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                long count1= dataSnapshot.getChildrenCount();
                String count = String.valueOf(count1);

                int count2 = Integer.parseInt(count);

                if ( count2 <= 0 ) {
                    prg.setVisibility(View.GONE);
                    img.setVisibility(View.VISIBLE);
                    msg2.setVisibility(View.VISIBLE);
                    msg.setVisibility(View.VISIBLE);
                    shpN.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        btnSend.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

                orders_DB = new ordersDB();
                DatabaseReference getCountO = FirebaseDatabase.getInstance().getReference().child("orderDB");
                getCountO.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        long count1 = 0;
                        int count2 = 0;
                        count1= dataSnapshot.getChildrenCount();
                        String count = String.valueOf(count1);

                        count2 = Integer.parseInt(count);

                        count2 = count2 + 1;

                        orders_DB.setCout(String.valueOf(count2));
                        DatabaseReference getCount2 = FirebaseDatabase.getInstance().getReference().child("cartDB");
                        getCount2.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                long count1= dataSnapshot.getChildrenCount();
                                String count = String.valueOf(count1);
                                for ( int c = 1 ; c <=  Integer.parseInt(count) ; c++){
                                    DatabaseReference getDetails2 = FirebaseDatabase.getInstance().getReference().child("cartDB").child(String.valueOf(c));
                                    getDetails2.addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                            if(dataSnapshot.hasChildren()){
                                                String name2 = (dataSnapshot.child("itemName").getValue().toString());
                                                String price2 = (dataSnapshot.child("itemPrice").getValue().toString());
                                                String qty22 = (dataSnapshot.child("qty").getValue().toString());
                                                writeOrderElement( orders_DB.getCout() ,  name2 , price2 , qty22);

                                            }
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {

                                        }
                                    });

                                }


                                Intent intent = new Intent(cart.this , address.class);
                                startActivity(intent);
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


            }
        });





    }

    public void showOrderElement( String name , String price , String qty1) {

     /*   float p = Float.parseFloat(price);*/
        //this.price = this.price + Float.parseFloat(price);

        LinearLayout layout = (LinearLayout) findViewById(R.id.mainLayout2);


        LinearLayout.LayoutParams paramsM = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT
        );

        paramsM.setMargins(8 , 8 ,8 ,64  );

        layout.setLayoutParams(paramsM);


            LinearLayout row = new LinearLayout(this);
            row.setOrientation(LinearLayout.VERTICAL);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT
            );

            LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT
            );



            params.setMargins(8 , 8 ,8 ,16  );
            params2.setMargins(8 , 8 ,8 ,8  );



            TextView txt1 = new TextView(this);
            TextView txt2 = new TextView(this);
            TextView txt3 = new TextView(this);
            TextView txt4 = new TextView(this);










            txt1.setText("Item " + this.itemCount );
            itemCount++;
            txt2.setText( name);
            txt3.setText(price);
            txt4.setText(qty1);




            row.addView(txt1);
            txt1.setLayoutParams(params2);

            row.addView(txt2);
            txt2.setLayoutParams(params2);

            row.addView(txt3);
            txt3.setLayoutParams(params2);

            row.addView(txt4);
            txt4.setLayoutParams(params);


            row.setLayoutParams(params2);
            row.setBackgroundResource(R.drawable.border_bottom);
            layout.addView(row);



    }



    public void writeOrderElement(String countOrder , String nameOrder , String priceOrder , String qtyOrder) {
        DatabaseReference pref2 =  FirebaseDatabase.getInstance().getReference().child("orderDB").child(countOrder);
        orders_DB.setItemName(nameOrder);
        orders_DB.setItemPrice(priceOrder);
        orders_DB.setQty(qtyOrder);


        pref2.push();
        pref2.child(String.valueOf(this.innerOrderCount)).setValue(orders_DB);


        deleteOrderFromCart(String.valueOf(this.innerOrderCount));
        this.innerOrderCount++;




    }



    public void deleteOrderFromCart(String c){

        DatabaseReference pref2 =  FirebaseDatabase.getInstance().getReference().child("cartDB").child(c);
        pref2.removeValue();
    }


    public void shopNow(View view){
        Intent intnt = new Intent(cart.this , MainActivity.class);
        startActivity(intnt);
    }

}
