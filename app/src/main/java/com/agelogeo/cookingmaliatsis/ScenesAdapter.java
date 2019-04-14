package com.agelogeo.cookingmaliatsis;

import android.app.Activity;
import android.arch.lifecycle.Lifecycle;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.squareup.picasso.Picasso;



public class ScenesAdapter extends RecyclerView.Adapter<ScenesAdapter.DownloadViewHolder>{
    View v;
    int episode_position;
    Lifecycle lifecycle;

    ScenesAdapter(int position,Lifecycle lifecycle){
        episode_position = position;
        this.lifecycle = lifecycle;
    }


    @NonNull
    @Override
    public DownloadViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.scene_custom_layout, viewGroup, false);
        DownloadViewHolder pvh = new DownloadViewHolder(v, SavedSettings.getEpisodeFromAllArray(i));
        return pvh;
    }

    @Override
    public void onBindViewHolder(@NonNull final DownloadViewHolder downloadViewHolder, int i) {
        final int position = i;
        int timestamp = SavedSettings.getEpisodeFromAllArray(episode_position).getEpisodeScenes().get(position).getTimestamp();
        downloadViewHolder.title.setText(SavedSettings.getEpisodeFromAllArray(episode_position).getEpisodeScenes().get(position).getTitle()+" @"+ DateUtils.formatElapsedTime(timestamp));

        try {
            Picasso.get().setIndicatorsEnabled(true);
            Picasso.get().load(SavedSettings.thumbnail_link1+SavedSettings.getEpisodeFromAllArray(i).getVideo_id()+SavedSettings.thumbnail_link2).into(downloadViewHolder.photoWallpaper);

        } catch (Exception e) {
            e.printStackTrace();
        }


        downloadViewHolder.shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(),"Share",Toast.LENGTH_SHORT).show();
            }
        });

        downloadViewHolder.likeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(),"Like",Toast.LENGTH_SHORT).show();
            }
        });

        downloadViewHolder.photoWallpaper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), VideoActivity.class);
                intent.putExtra("episode_position",episode_position);
                intent.putExtra("scene_position",position);
                ActivityCompat.startActivity(v.getContext(), intent, null);
            }
        });


    }


    @Override
    public int getItemCount() {
        return SavedSettings.getEpisodeFromAllArray(episode_position).getEpisodeScenes().size();
    }


    public static class DownloadViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView title;
        ImageView photoWallpaper,shareButton,likeButton;
        Episode episode;

        DownloadViewHolder(final View itemView, final Episode episode) {
            super(itemView);
            this.episode = episode;
            cv = itemView.findViewById(R.id.custom_scene_cv);
            title = itemView.findViewById(R.id.scene_custom_title);
            photoWallpaper = itemView.findViewById(R.id.scene_custom_episode_thumbnail);
            shareButton = itemView.findViewById(R.id.shareSceneBtn);
            likeButton = itemView.findViewById(R.id.likeSceneBtn);



        }
    }




}