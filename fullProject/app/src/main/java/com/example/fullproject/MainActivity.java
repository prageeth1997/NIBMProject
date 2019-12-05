package com.example.fullproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    LinearLayout waiting;
    TextView msg , msg2;
    EditText ed1 , ed2;
    Button login;
    ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        waiting = findViewById(R.id.waitLayout);
        login = findViewById(R.id.button);
        msg = findViewById(R.id.textView3);
        msg2 = findViewById(R.id.textView2);
        img = findViewById(R.id.imageView2);

        ed1 = findViewById(R.id.editText);
        ed2 = findViewById(R.id.editText2);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                msg.setVisibility(View.GONE);
                waiting.setVisibility(View.VISIBLE);

                String usrname = ed1.getText().toString();
                String pass = ed2.getText().toString();

                final String usrnameF = usrname;
                final String passwordF = pass;
                DatabaseReference getDetails = FirebaseDatabase.getInstance().getReference().child("user").child(usrname);
                getDetails.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChildren()) {
                            String username = (dataSnapshot.child("username").getValue().toString());
                            String password = (dataSnapshot.child("password").getValue().toString());

                            if (usrnameF.equals(username) && passwordF.equals(password)) {
                                ed1.setVisibility(View.GONE);
                                ed2.setVisibility(View.GONE);
                                login.setVisibility(View.GONE);
                                msg2.setText("QR කේතය පූරණය වෙමින් පවතී...");
                                msg2.setTextSize(16);

                                StorageReference rootRef = FirebaseStorage.getInstance().getReference();

                                rootRef.child(ed1.getText().toString()).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                    @Override
                                    public void onSuccess(Uri uri) {

                                        String url = String.valueOf(uri);
                                        Picasso.with(MainActivity.this).load(url).into(img);

                                        waiting.setVisibility(View.GONE);
                                        img.setVisibility(View.VISIBLE);

                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception exception) {

                                    }
                                });

                            } else {
                                msg.setVisibility(View.VISIBLE);
                                waiting.setVisibility(View.GONE);

                            }

                        }
                        else {
                            msg.setVisibility(View.VISIBLE);
                            waiting.setVisibility(View.GONE);
                        }


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


            }
        });
    }





}
