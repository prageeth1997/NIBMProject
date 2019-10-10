package com.example.projectlast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class showOrdersorAdmin extends AppCompatActivity {


    TextView txt1 ;

    TextView msg2;
    ProgressBar prgB;


    setText st;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_ordersor_admin);

        txt1 = findViewById(R.id.textView9);

        msg2 = findViewById(R.id.textView11);
        prgB = findViewById(R.id.progressBar3);

       st  = new setText();



        DatabaseReference getCount = FirebaseDatabase.getInstance().getReference().child("orderDB");
        getCount.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                long count1= dataSnapshot.getChildrenCount();
                String count = String.valueOf(count1);


                txt1.setText(count);

                showElement(count);


                prgB.setVisibility(View.GONE);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        DatabaseReference getDetails = FirebaseDatabase.getInstance().getReference().child("orderDB");
        getDetails.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                long count1= dataSnapshot.getChildrenCount();
                String count = String.valueOf(count1);

                int count2 = Integer.parseInt(count);

                if ( count2 <= 0 ) {
                    prgB.setVisibility(View.GONE);
                    msg2.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }







    //Show Element1
    public void showElement( String s) {

        LinearLayout layout = (LinearLayout) findViewById(R.id.mainLayout);
        for(int cf = 1 ; cf <= Integer.valueOf(s) ; cf++) {
            final int finalCf = cf;
            LinearLayout row = new LinearLayout(this);
            row.setOrientation(LinearLayout.VERTICAL);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT
            );

            LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT
            );



            params.setMargins(16 , 16 ,16 ,16  );
            params2.setMargins(8 , 8 ,8 ,8  );





            final int id = cf + 10;



            TextView text = new TextView(this);
            text.setId(cf);

            TextView text2 = new TextView(this);
            text2.setId(id);

            Button btn1 = new Button(this);
            btn1.setId(cf);
            btn1.setText("Delete Order");
            btn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DatabaseReference pref2 =  FirebaseDatabase.getInstance().getReference().child("orderDB").child(String.valueOf(finalCf));
                    pref2.removeValue();

                    DatabaseReference pref3 =  FirebaseDatabase.getInstance().getReference().child("deliveryDetails").child(String.valueOf(finalCf));
                    pref3.removeValue();

                    Intent intent2 = new Intent(showOrdersorAdmin.this , showOrdersorAdmin.class);
                    startActivity(intent2);


                }
            });



            text.setLayoutParams(params);
            row.addView(text);

            text2.setLayoutParams(params);
            row.addView(text2);

            btn1.setLayoutParams(params);
            btn1.setBackgroundResource(R.drawable.button_radius1);
            row.addView(btn1);


            DatabaseReference getCount2 = FirebaseDatabase.getInstance().getReference().child("orderDB").child(String.valueOf(cf));
            getCount2.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    long count2= dataSnapshot.getChildrenCount();
                    String count3 = String.valueOf(count2);

                    for (int cf2 = 1 ; cf2 <= Integer.valueOf(count3); cf2++) {
                        DatabaseReference getCount3 = FirebaseDatabase.getInstance().getReference().child("orderDB").child(String.valueOf(finalCf)).child(String.valueOf(cf2));

                        getCount3.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                if (dataSnapshot.hasChildren()) {

                                    String name = (dataSnapshot.child("itemName").getValue().toString());
                                    String price = (dataSnapshot.child("itemPrice").getValue().toString());
                                    String qty2 = (dataSnapshot.child("qty").getValue().toString());

                                    String all = name + " x " + qty2 + "  " + price;

                                    //txt4.setText(txt4.getText() + all + Html.fromHtml("<br>"));

                                    TextView txF = findViewById(Integer.valueOf(finalCf));
                                    txF.setText((txF.getText() + all + Html.fromHtml("<br>")));


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


            DatabaseReference getCount3 = FirebaseDatabase.getInstance().getReference().child("deliveryDetails").child(String.valueOf(cf));
            getCount3.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if(dataSnapshot.hasChildren()){

                        TextView txF2 = findViewById(Integer.valueOf(id));
                       String add =  dataSnapshot.child("details").getValue().toString();
                        String mobileNu =  dataSnapshot.child("mobileNu").getValue().toString();

                        txF2.setText((txF2.getText() + add + Html.fromHtml("<br>")));
                        txF2.setText((txF2.getText() + mobileNu + Html.fromHtml("<br>")));
                    }


                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });






            row.setLayoutParams(params2);


            row.setBackgroundResource(R.drawable.order_outline);
            layout.addView(row);

        }



    }


}
