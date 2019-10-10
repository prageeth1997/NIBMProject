package com.example.projectlast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.ViewFlipper;

public class MainActivity extends AppCompatActivity {


    Animation fade_in, fade_out;
    ViewFlipper viewFlipper;

    TextView txt1 , txt2 , txt3 , txt4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt1 = findViewById(R.id.textView3);
        txt2 = findViewById(R.id.textView4);
        txt3 = findViewById(R.id.textView5);
        txt4 = findViewById(R.id.textView6);
        viewFlipper = (ViewFlipper) this.findViewById(R.id.bckgrndViewFlipper1);

        fade_in = AnimationUtils.loadAnimation(this,
                android.R.anim.fade_in);
        fade_out = AnimationUtils.loadAnimation(this,
                android.R.anim.fade_out);

        viewFlipper.setInAnimation(fade_in);
        viewFlipper.setOutAnimation(fade_out);

        //sets auto flipping
        viewFlipper.setAutoStart(true);
        viewFlipper.setFlipInterval(5000);
        viewFlipper.startFlipping();
    }



    public void changeFragment(View view){
        Fragment fragment;
        if (view == findViewById(R.id.textView3)){
            fragment = new cat1();
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.defultFragment,fragment);
            ft.commit();
            txt1.setBackgroundResource(R.drawable.labl_round_selected);
            txt2.setBackgroundResource(R.drawable.lbl_round_notselected);
            txt3.setBackgroundResource(R.drawable.lbl_round_notselected);
            txt4.setBackgroundResource(R.drawable.lbl_round_notselected);

        }
        if (view == findViewById(R.id.textView4)){
            fragment = new cat2();
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.defultFragment,fragment);
            ft.commit();

            txt2.setBackgroundResource(R.drawable.labl_round_selected);
            txt1.setBackgroundResource(R.drawable.lbl_round_notselected);
            txt3.setBackgroundResource(R.drawable.lbl_round_notselected);
            txt4.setBackgroundResource(R.drawable.lbl_round_notselected);
        }
        if (view == findViewById(R.id.textView5)){
            fragment = new cat3();
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.defultFragment,fragment);
            ft.commit();

            txt3.setBackgroundResource(R.drawable.labl_round_selected);
            txt1.setBackgroundResource(R.drawable.lbl_round_notselected);
            txt2.setBackgroundResource(R.drawable.lbl_round_notselected);
            txt4.setBackgroundResource(R.drawable.lbl_round_notselected);
        }
        if (view == findViewById(R.id.textView6)){
            fragment = new cat4();
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.defultFragment,fragment);
            ft.commit();

            txt4.setBackgroundResource(R.drawable.labl_round_selected);
            txt2.setBackgroundResource(R.drawable.lbl_round_notselected);
            txt3.setBackgroundResource(R.drawable.lbl_round_notselected);
            txt1.setBackgroundResource(R.drawable.lbl_round_notselected);
        }
    }

    public void goToCart(View view) {
        Intent intent1 = new Intent(MainActivity.this , cart.class);
        startActivity(intent1);
    }
}
