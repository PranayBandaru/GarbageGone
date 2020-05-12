package com.example.dtlapp;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class loggedIn extends AppCompatActivity {

    private static final boolean USER_IS_GOING_TO_EXIT = false ;
    int backpress = 0;

    private Toast backtoast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_in);
        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                // Handle the back button event

                //startActivity(new Intent(this, MainActivity.class));
                finish();
                //Toast.makeText(this,"BACK HIT",Toast.LENGTH_LONG).show();
            }
        };
        new MainActivity().getOnBackPressedDispatcher().addCallback(this, callback);


    }







    @Override

    public void onBackPressed(){
         backpress = (backpress + 1);
    Toast.makeText(getApplicationContext(), " Press Back again to Log Out ", Toast.LENGTH_SHORT).show();

    if (backpress>1) {
        this.finish();
    }
}

}
