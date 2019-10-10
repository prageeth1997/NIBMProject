package com.example.projectlast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class AdminLogin extends AppCompatActivity {

    TextView username , password;
    Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        username = findViewById(R.id.editText);
        password = findViewById(R.id.editText2);
        login = findViewById(R.id.button);



        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                 String usrname = username.getText().toString();
                 String pass = password.getText().toString();
                showAdminPage( usrname, pass);
            }
        });
    }

    public void showAdminPage(String uname , String password1){

       // uname = "admin";
        if((uname.equals("admin")) && (password1.equals("1234"))  ){
            Intent intentAdmin = new Intent(AdminLogin.this , showOrdersorAdmin.class);
            startActivity(intentAdmin);
        }else {
            username.setText("");
            password.setText("");
            Toast.makeText(getApplicationContext(),"Invalid Login" , Toast.LENGTH_LONG).show();
        }

    }
}
