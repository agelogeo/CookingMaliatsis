package com.agelogeo.cookingmaliatsis;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


public class RVAdapter extends RecyclerView.Adapter<RVAdapter.DownloadViewHolder>{
    View v;

    RVAdapter(){
    }


    @NonNull
    @Override
    public DownloadViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if(SavedSettings.isShowThumbnails())
            v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_custom_layout, viewGroup, false);
        else
            v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.simple_list_custom_layout, viewGroup, false);
        DownloadViewHolder pvh = new DownloadViewHolder(v, SavedSettings.getEpisodeFromAllArray(i));
        return pvh;
    }

    @Override
    public void onBindViewHolder(@NonNull final DownloadViewHolder downloadViewHolder, int i) {
        final int position = i;
        downloadViewHolder.title.setText(SavedSettings.getEpisodeFromAllArray(i).getTitle());

        try {
            Picasso.get().setIndicatorsEnabled(true);
            if(SavedSettings.isShowThumbnails())
                Picasso.get().load(SavedSettings.getEpisodeFromAllArray(i).getThumbnail()).into(downloadViewHolder.photoWallpaper);

        } catch (Exception e) {
            e.printStackTrace();
        }

        if(SavedSettings.isShowThumbnails()){
            downloadViewHolder.photoWallpaper.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    /*Intent intent = new Intent(v.getContext(), PostActivity.class);
                    intent.putExtra("position",position);

                    ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                            // the context of the activity
                            (Activity)v.getContext(),

                            // For each shared element, add to this method a new Pair item,
                            // which contains the reference of the view we are transitioning *from*,
                            // and the value of the transitionName attribute
                            new Pair<View, String>(v.findViewById(R.id.custom_photoWallpaper),
                                    v.getContext().getString(R.string.transition_string))
                    );
                    ActivityCompat.startActivity(v.getContext(), intent, options.toBundle());*/
                }
            });
        }


    }


    @Override
    public int getItemCount() {
        return SavedSettings.getStaticAllEpisodes().size();
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
            if(SavedSettings.isShowThumbnails())
                photoWallpaper = itemView.findViewById(R.id.list_custom_episode_thumbnail);



        }
    }




}