package com.example.dtlapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


/**
 * A simple {@link Fragment} subclass.
 */
public class frag2 extends Fragment {

    EditText emailf;
    EditText passf;

    FirebaseAuth mAuth;

    FirebaseAuth.AuthStateListener mAuthListener;

    public frag2() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //super.onCreateView();
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_frag2, container, false);

        emailf = view.findViewById(R.id.userfield);
        passf = view.findViewById(R.id.passfield);
        //emailf = (EditText) getView().findViewById(R.id.userfield);
        //passf = (EditText) getView().findViewById(R.id.passfield);


        mAuth = FirebaseAuth.getInstance();



        return view;
    }


    public void signin(View view) {

        Toast.makeText(getActivity(), "Bleeeeep", Toast.LENGTH_LONG).show();

        mAuthListener = new FirebaseAuth.AuthStateListener() {

            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                if(firebaseAuth.getCurrentUser() != null){

                    Toast.makeText(getActivity(), "Bleeeeep", Toast.LENGTH_LONG).show();

                    startSignin();
                }
            }
        };

    }


    @Override
    public void onStart() {
        super.onStart();

        FirebaseUser currentUser = mAuth.getCurrentUser();
        //mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if(mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    public void startSignin(){

        String email = emailf.getText().toString();
        String pass = emailf.getText().toString();


        mAuth.addAuthStateListener(mAuthListener);

        if(TextUtils.isEmpty(email) || TextUtils.isEmpty(email)){

            Toast.makeText(getActivity(), "Please enter a username and password", Toast.LENGTH_LONG).show();
        }
        else
        {
            mAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()) {
                        startActivity(new Intent(getActivity(),loggedIn.class));

                    }
                    else
                    {
                        Toast.makeText(getActivity(), "Sign in failed", Toast.LENGTH_LONG).show();
                    }
                }
            });

        }
    }


}
