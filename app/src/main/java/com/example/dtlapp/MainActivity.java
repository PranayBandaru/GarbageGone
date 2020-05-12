package com.example.dtlapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    TabLayout mytab;
    public ViewPager mypage;
    EditText emailf;
    EditText passf;

    FirebaseAuth mAuth;

    FirebaseAuth.AuthStateListener mAuthListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setContentView(R.layout.activity_main);
        super.onCreate(savedInstanceState);

        mytab = (TabLayout)findViewById(R.id.tabl);
        mypage = (ViewPager)findViewById(R.id.vpager);

        //final Dialog view = new Dialog(this);
        //view.setContentView(R.layout.activity_main);
        //view.show();
        //emailf = (EditText)findViewById(R.id.userfield);
        //passf = (EditText)findViewById(R.id.passfield);

        mAuth = FirebaseAuth.getInstance();

        mypage.setAdapter(new myadapter(getSupportFragmentManager()));
        mytab.setupWithViewPager(mypage);

        //mypage.setCurrentItem(0);




        mytab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mypage.setCurrentItem(tab.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });



    }

    public void dosomething(View view)
    {

        //FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        //fragmentTransaction.add(R.id.fragcontainer,new addComplaint());
        //fragmentTransaction.commit();
        startActivity(new Intent(this, activity2.class));

    }

    public void opencam(View view)
    {
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        startActivity(intent);
    }

    public void signin(View view) {

        mAuthListener = new FirebaseAuth.AuthStateListener() {

            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                if(firebaseAuth.getCurrentUser() != null){

                    //Toast.makeText(MainActivity.this, "Bleeeeep", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(MainActivity.this,loggedIn.class));
                }
            }
        };
        startSignin();

    }

    @Override
    protected void onStart() {
        super.onStart();

    }


    @Override
    public void onStop() {
        super.onStop();
        if(mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    public void startSignin(){

        emailf = (EditText)findViewById(R.id.userfield);
        passf = (EditText)findViewById(R.id.passfield);
        String email = emailf.getText().toString();
        String pass = passf.getText().toString();
        //emailf.setText("");
        //passf.setText("");
        if(mypage.getCurrentItem() == 1) {
            mAuth.addAuthStateListener(mAuthListener);

        }
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {


                if(firebaseAuth.getCurrentUser() != null  && mypage.getCurrentItem() == 1){
                    //Toast.makeText(MainActivity.this, "Blooop", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(MainActivity.this,loggedIn.class));

                }
            }
        };
        if(TextUtils.isEmpty(email) || TextUtils.isEmpty(email)){

            Toast.makeText(MainActivity.this, "Please enter a username and password", Toast.LENGTH_LONG).show();
        }
        else if(mypage.getCurrentItem() == 1)
        {
        mAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(!task.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "Sign in failed", Toast.LENGTH_LONG).show();
                }
            }
        });

        }
    }


    static class myadapter extends FragmentPagerAdapter
    {
        String[] frags = {"View/Add Reports","Remove Reports"};

        public myadapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull

        @Override
        public Fragment getItem(int position) {
            if(position == 0)
            {
                return new frag1();
            }
            if(position == 1)
            {
                return new frag2();

            }
            else
                {
                return null;
            }
        }

        @Override
        public int getCount() {
            return frags.length;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return frags[position];
        }
    }
}
