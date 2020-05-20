package com.example.dtlapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.List;

public class loginImageAdapter extends RecyclerView.Adapter<loginImageAdapter.LImageViewHolder> {
    private Context mcontext;
    private List<Upload> mupload;
    loginImageAdapter.onItemClickL mListener;
    String cordinatesObtained;


    FirebaseStorage mStrorage;
    DatabaseReference mdatabaseref;

    public Button mapbutton;
    public Button deletebutton;

    public interface OnItemClickListener{
        void OnMapB(int position);
    }

    public loginImageAdapter(Context context, List<Upload> uploads){
        mcontext = context;
        mupload = uploads;
    }

    @NonNull
    @Override
    public LImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mcontext).inflate(R.layout.image_login,parent,false);

        mapbutton = v.findViewById(R.id.loginmapsb);
        deletebutton = v.findViewById(R.id.loginclearedb);

        mStrorage = FirebaseStorage.getInstance();

        mdatabaseref = FirebaseDatabase.getInstance().getReference("Photos");
        return new LImageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull LImageViewHolder holder, final int position) {
        Upload currentupload1 = mupload.get(position);
        holder.cordstext.setText("Type: "+currentupload1.getselectedRB());
        cordinatesObtained = currentupload1.getcords();
        holder.remarkstext1.setText("Remarks: "+currentupload1.getremarks());
        holder.timetext.setText(currentupload1.gettime());
        mapbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Uri gmmIntentUri = Uri.parse(cordinatesObtained);
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                mcontext.startActivity(mapIntent);

            }
        });

        deletebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Upload selecteditem = mupload.get(position);
                final String selectedkey = selecteditem.getKey();

                StorageReference imageref = mStrorage.getReferenceFromUrl(selecteditem.getImageURL());
                imageref.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        mdatabaseref.child(selectedkey).removeValue();
                        //Toast.makeText(g,"Report Cleared",Toast.LENGTH_LONG).show();
                    }
                });

                /*

                Uri gmmIntentUri = Uri.parse(cordinatesObtained);
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                mcontext.startActivity(mapIntent);

                 */

            }
        });



        Picasso.get().load(currentupload1.getImageURL()).into(holder.imgview);


        //fit().centerCrop().
    }

    @Override
    public int getItemCount() {
        return mupload.size();
    }

    public class LImageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener {

        public TextView cordstext;
        public TextView timetext;
        public TextView remarkstext1;
        public ImageView imgview;



        public LImageViewHolder(@NonNull View itemView) {
            super(itemView);

            cordstext = itemView.findViewById(R.id.cordstext);
            timetext = (TextView) itemView.findViewById(R.id.timelogin);
            remarkstext1 = (TextView) itemView.findViewById(R.id.remarkslogin);
            imgview =  (ImageView) itemView.findViewById(R.id.loginimage);
            itemView.setOnClickListener(this);
            //itemView.setOnCreateContextMenuListener(this);



        }

        @Override
        public void onClick(View v) {
            if(mListener != null)
            {
                int position = getAdapterPosition();
                if(position != RecyclerView.NO_POSITION){
                    mListener.onbclick(position);
                }

            }
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            menu.setHeaderTitle("Select Action");
            MenuItem OpenMaps = menu.add(Menu.NONE,1,1,"Open MAPS");
            OpenMaps.setOnMenuItemClickListener(this);

        }

        @Override
        public boolean onMenuItemClick(MenuItem item) {
            if(mListener != null)
            {
                int position = getAdapterPosition();
                if(position != RecyclerView.NO_POSITION){
                    if(item.getItemId() == 1)
                    {
                        mListener.onbclick(1);
                        return true;
                    }
                }

            }

            return false;
        }
    }
    public interface onItemClickL{
        void onbclick(int position);
    }
    public void setOnItemClickListener (loginImageAdapter.onItemClickL listener)
    {
        mListener = listener;
    }



}



