package com.theminimaldeveloper.superheroes;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class AdapterCharacterVideosList extends RecyclerView.Adapter<AdapterCharacterVideosList.viewHolder> {

    ArrayList<ModelVideoDetails> videoDetails = new ArrayList<>();
    Context context;

    public AdapterCharacterVideosList(ArrayList<ModelVideoDetails> videoDetails, Context context) {
        this.videoDetails = videoDetails;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.video_item, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final viewHolder holder, int position) {

        Glide.with(context).load(videoDetails.get(position).getThumbnailUrl()).placeholder(R.drawable.loading).into(holder.img_video_thumbnail);

        holder.txt_video_title.setText(videoDetails.get(position).getTitle());

        String channel = "Channel : "+videoDetails.get(position).getChannelName();
        holder.txt_video_channel_name.setText(channel);


        holder.img_video_thumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, VideoPlayerActivity.class);
                intent.putExtra("videoId", videoDetails.get(holder.getAdapterPosition()).getVideoID());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return videoDetails.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{

        ImageView img_video_thumbnail;
        TextView txt_video_title, txt_video_channel_name;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            img_video_thumbnail = itemView.findViewById(R.id.img_video_thumbnail);
            txt_video_title = itemView.findViewById(R.id.txt_video_title);
            txt_video_channel_name = itemView.findViewById(R.id.txt_video_channel_name);
        }
    }
}
