package com.agelogeo.cookingmaliatsis;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


public class ScenesAdapter extends RecyclerView.Adapter<ScenesAdapter.DownloadViewHolder>{
    View v;

    ScenesAdapter(){
    }


    @NonNull
    @Override
    public DownloadViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_custom_layout, viewGroup, false);
        DownloadViewHolder pvh = new DownloadViewHolder(v, SavedSettings.getEpisodeFromAllArray(i));
        return pvh;
    }

    @Override
    public void onBindViewHolder(@NonNull final DownloadViewHolder downloadViewHolder, int i) {
        final int position = i;
        downloadViewHolder.title.setText(SavedSettings.getEpisodeFromAllArray(i).getTitle());

        try {
            Picasso.get().setIndicatorsEnabled(true);
            Picasso.get().load(SavedSettings.thumbnail_link1+SavedSettings.getEpisodeFromAllArray(i).getVideo_id()+SavedSettings.thumbnail_link2).into(downloadViewHolder.photoWallpaper);

        } catch (Exception e) {
            e.printStackTrace();
        }


        downloadViewHolder.photoWallpaper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), EpisodeActivity.class);
                intent.putExtra("position",position);

                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                        // the context of the activity
                        (Activity)v.getContext(),

                        // For each shared element, add to this method a new Pair item,
                        // which contains the reference of the view we are transitioning *from*,
                        // and the value of the transitionName attribute
                        new Pair<View, String>(v.findViewById(R.id.list_custom_episode_thumbnail),
                                v.getContext().getString(R.string.transition_string))
                );
                ActivityCompat.startActivity(v.getContext(), intent, options.toBundle());
            }
        });



    }


    @Override
    public int getItemCount() {
        return 8;
    }


    public static class DownloadViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView title;
        ImageView photoWallpaper;
        Episode episode;

        DownloadViewHolder(final View itemView, final Episode episode) {
            super(itemView);
            this.episode = episode;
            cv = itemView.findViewById(R.id.custom_cv);
            title = itemView.findViewById(R.id.list_custom_title);
            photoWallpaper = itemView.findViewById(R.id.list_custom_episode_thumbnail);



        }
    }




}