package com.theminimaldeveloper.superheroes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;


public class AdapterComicsRecycler extends RecyclerView.Adapter<AdapterComicsRecycler.viewHolder> {

    ArrayList<String> ItemsList ;


    public AdapterComicsRecycler(ArrayList<String> ItemsList) {
        this.ItemsList = ItemsList;

    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater= LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.comic_item, parent,false);
        return new viewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        holder.textView.setText(ItemsList.get(position));
    }

    @Override
    public int getItemCount() {
        return ItemsList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{

        TextView textView;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.txt_item_name);
        }
    }
}
