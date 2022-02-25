package com.example.movielistviewer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.text.BreakIterator;
import java.util.List;

public class adapterForRecycle extends RecyclerView.Adapter<adapterForRecycle.MyViewHolder> {

    private Context mc;
    private List<model> li;

    public adapterForRecycle(List<model> li,Context mc) {
        this.li = li;
        this.mc=mc;
    }

    public adapterForRecycle() {

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View a;
        LayoutInflater Lf = LayoutInflater.from(mc);
        a=Lf.inflate(R.layout.layou1,parent,false);

        return new MyViewHolder(a);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.rating.setText(li.get(position).getRating());
        holder.name.setText(li.get(position).getName());

        Glide.with(mc).load(li.get(position).getImg()).into(holder.img);


    }

    @Override
    public int getItemCount() {
        return li.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name ;
        TextView rating;
        ImageView img;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.textView);
            rating=itemView.findViewById(R.id.textView2);
            img=itemView.findViewById(R.id.imageView);

        }
    }
}
