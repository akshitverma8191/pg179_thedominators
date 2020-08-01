package com.example.thedominators;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class AdapterExplore extends RecyclerView.Adapter<AdapterExplore.ViewHolder>{
    private ArrayList<place> listdata;

    // RecyclerView recyclerView;
    public AdapterExplore(ArrayList<place> listdata) {
        this.listdata = listdata;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.item_explore, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final place ld = listdata.get(position);
        holder.textView.setText(ld.getName());
        holder.imageView.setImageResource(ld.getImgid());
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(),"click on item: "+ld.getName(),Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return listdata.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView textView;
        public RelativeLayout relativeLayout;
        public ViewHolder(View itemView) {
            super(itemView);
            this.imageView = (ImageView) itemView.findViewById(R.id.image_explore);
            this.textView = (TextView) itemView.findViewById(R.id.text_explore);
            relativeLayout = (RelativeLayout)itemView.findViewById(R.id.relative);
        }
    }
}