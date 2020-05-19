package com.example.dtlapp;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.List;

public class loggedIn extends AppCompatActivity {

    private static final boolean USER_IS_GOING_TO_EXIT = false ;
    int backpress = 0;

    private Toast backtoast;
    public RecyclerView recycler;
    private loginImageAdapter madapter;

    FirebaseStorage mStrorage;
    DatabaseReference mdatabaseref;
    ValueEventListener DBVEL;
    private List<Upload> muploads;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //View v = inflater.inflate(R.layout.fragment_frag1, container, false);

        setContentView(R.layout.activity_logged_in);

        recycler = findViewById(R.id.loginRecycler);
        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new LinearLayoutManager(loggedIn.this));


        muploads = new ArrayList<>();

        madapter = new loginImageAdapter(loggedIn.this,muploads);
        madapter.notifyDataSetChanged();
        recycler.setAdapter(madapter);

        mStrorage = FirebaseStorage.getInstance();
        mdatabaseref = FirebaseDatabase.getInstance().getReference("Photos");
        DBVEL = mdatabaseref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                muploads.clear();
                for(DataSnapshot postSnapshot : dataSnapshot.getChildren())
                {
                    Upload upload = postSnapshot.getValue(Upload.class);
                    muploads.add(upload);
                    upload.setKey(postSnapshot.getKey());
                }

                madapter.notifyDataSetChanged();
            }



            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(loggedIn.this,databaseError.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });


        //return v;








    }







    @Override

    public void onBackPressed(){
         backpress = (backpress + 1);
    Toast.makeText(getApplicationContext(), " Press Back again to Log Out ", Toast.LENGTH_SHORT).show();

    if (backpress>1) {
        this.finish();
    }
}

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mdatabaseref.removeEventListener(DBVEL);
    }
}
