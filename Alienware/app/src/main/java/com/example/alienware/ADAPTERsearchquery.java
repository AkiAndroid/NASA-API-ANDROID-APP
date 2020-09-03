package com.example.alienware;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.internal.bind.ReflectiveTypeAdapterFactory;

public class ADAPTERsearchquery extends RecyclerView.Adapter<ADAPTERsearchquery.MyViewHolder> {

    @Override
    public MyViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

 public static class MyViewHolder extends RecyclerView.ViewHolder {
     public MyViewHolder(@NonNull View itemView) {
         super(itemView);
     }
 }
}
