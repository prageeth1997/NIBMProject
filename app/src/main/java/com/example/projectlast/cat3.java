package com.example.projectlast;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class cat3 extends Fragment {

    TextView txt11Name , txt12Name , txt21Name , txt22Name , txt31Name , txt32Name ;
    TextView txt11Price , txt12Price , txt21Price , txt22Price , txt31Price , txt32Price ;

    Button button11 , button12 , button21 , button22 , button31 , button32 ;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View View1 = inflater.inflate(R.layout.fragment_cat3, container, false);

        txt11Name = (TextView)  View1.findViewById(R.id.t11Name);
        txt12Name = (TextView)  View1.findViewById(R.id.t12Name);
        txt21Name = (TextView)  View1.findViewById(R.id.t21Name);
        txt22Name = (TextView)  View1.findViewById(R.id.t22Name);
        txt31Name = (TextView)  View1.findViewById(R.id.t31Name);
        txt32Name = (TextView)  View1.findViewById(R.id.t32Name);

        txt11Price = (TextView)  View1.findViewById(R.id.t11Price);
        txt12Price = (TextView)  View1.findViewById(R.id.t12Price);
        txt21Price = (TextView)  View1.findViewById(R.id.t21Price);
        txt22Price = (TextView)  View1.findViewById(R.id.t22Price);
        txt31Price = (TextView)  View1.findViewById(R.id.t31Price);
        txt32Price = (TextView)  View1.findViewById(R.id.t32Price);

        button11 = (Button) View1.findViewById(R.id.button11);
        button12 = (Button) View1.findViewById(R.id.button12);
        button21 = (Button) View1.findViewById(R.id.button21);
        button22 = (Button) View1.findViewById(R.id.button22);
        button31 = (Button) View1.findViewById(R.id.button31);
        button32 = (Button) View1.findViewById(R.id.button32);


        button11.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intOpenOrder = new Intent(getActivity() , popup.class);
                intOpenOrder.putExtra("ItemName" , txt11Name.getText().toString());
                intOpenOrder.putExtra("ItemPrice" , txt11Price.getText().toString());

                startActivity(intOpenOrder);

            }
        });

        button12.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intOpenOrder = new Intent(getActivity() , popup.class);
                intOpenOrder.putExtra("ItemName" , txt12Name.getText().toString());
                intOpenOrder.putExtra("ItemPrice" , txt12Price.getText().toString());

                startActivity(intOpenOrder);

            }
        });

        button21.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intOpenOrder = new Intent(getActivity() , popup.class);
                intOpenOrder.putExtra("ItemName" , txt21Name.getText().toString());
                intOpenOrder.putExtra("ItemPrice" , txt21Price.getText().toString());

                startActivity(intOpenOrder);

            }
        });

        button22.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intOpenOrder = new Intent(getActivity() , popup.class);
                intOpenOrder.putExtra("ItemName" , txt22Name.getText().toString());
                intOpenOrder.putExtra("ItemPrice" , txt22Price.getText().toString());

                startActivity(intOpenOrder);

            }
        });
        button31.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intOpenOrder = new Intent(getActivity() , popup.class);
                intOpenOrder.putExtra("ItemName" , txt31Name.getText().toString());
                intOpenOrder.putExtra("ItemPrice" , txt31Price.getText().toString());

                startActivity(intOpenOrder);

            }
        });
        button32.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intOpenOrder = new Intent(getActivity() , popup.class);
                intOpenOrder.putExtra("ItemName" , txt32Name.getText().toString());
                intOpenOrder.putExtra("ItemPrice" , txt32Price.getText().toString());

                startActivity(intOpenOrder);

            }
        });

        return View1;
    }


}
