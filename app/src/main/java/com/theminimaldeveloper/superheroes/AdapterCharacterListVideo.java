package com.theminimaldeveloper.superheroes;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AdapterCharacterListVideo extends RecyclerView.Adapter<AdapterCharacterListVideo.viewHolder> {


    int[] character_icon_ids;
    String[] charNames;
    Context context;

    public AdapterCharacterListVideo(Context context, int[] character_icon_ids, String[] charNames) {
        this.character_icon_ids = character_icon_ids;
        this.charNames = charNames;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.char_video_item, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final viewHolder holder, final int position) {

        holder.img_char_icon_video.setImageResource(character_icon_ids[position]);
        holder.txt_char_name_video.setText(charNames[position]);


        holder.img_char_icon_video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, YoutubeVideosListActivity.class);
                intent.putExtra("characterName", charNames[holder.getAdapterPosition()]);
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return character_icon_ids.length;
    }

    public class viewHolder extends RecyclerView.ViewHolder {

        ImageView img_char_icon_video;
        TextView txt_char_name_video;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            img_char_icon_video = itemView.findViewById(R.id.img_char_icon_video);
            txt_char_name_video = itemView.findViewById(R.id.txt_char_name_video);
        }
    }
}
