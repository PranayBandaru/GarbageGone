package com.example.dtlapp;

import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {

    private Context mcontext;
    private List<Upload> mupload;
    onItemClickL mListener;

    public ImageAdapter(Context context, List<Upload> uploads){
        mcontext = context;
        mupload = uploads;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mcontext).inflate(R.layout.image_item,parent,false);
        return new ImageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        Upload currentupload = mupload.get(position);
        holder.cordstext.setText(currentupload.getcords());
        holder.remarkstext.setText(currentupload.getremarks());
        holder.timetext.setText(currentupload.gettime());
        Picasso.get().load(currentupload.getImageURL()).into(holder.imgview);

        //fit().centerCrop().
    }

    @Override
    public int getItemCount() {
        return mupload.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener {

        public TextView cordstext;
        public TextView timetext;
        public TextView remarkstext;
        public ImageView imgview;


        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);

            cordstext = itemView.findViewById(R.id.Cordstext);
            timetext = itemView.findViewById(R.id.Timetext);
            remarkstext = itemView.findViewById(R.id.Remarkstext);
            imgview = itemView.findViewById(R.id.ImageCardV);
            itemView.setOnClickListener(this);
            itemView.setOnCreateContextMenuListener(this);
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
    public void setOnItemClickListener (onItemClickL listener)
    {
        mListener = listener;
    }
}
