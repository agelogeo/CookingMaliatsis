package com.agelogeo.cookingmaliatsis;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class VideoActivity extends YouTubeBaseActivity {

    private YouTubePlayerView youTubePlayerView;
    private YouTubePlayer.OnInitializedListener onInitializedListener;

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_video);
        Intent i = getIntent();
        youTubePlayerView = findViewById(R.id.youtube_view);
        final int episode_position = i.getIntExtra("episode_position",0);
        final int scene_position = i.getIntExtra("scene_position",0);


        onInitializedListener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {

                youTubePlayer.loadVideo(SavedSettings.getEpisodeFromAllArray(episode_position).getVideo_id(),1000*SavedSettings.getEpisodeFromAllArray(episode_position).getEpisodeScenes().get(scene_position).getTimestamp());
                youTubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT);
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        };
        youTubePlayerView.initialize(SavedSettings.API_KEY,onInitializedListener);

    }
}
