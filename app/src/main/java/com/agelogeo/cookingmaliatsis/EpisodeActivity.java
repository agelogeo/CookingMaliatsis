package com.agelogeo.cookingmaliatsis;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.net.Uri;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class EpisodeActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ScenesAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_episode);
        recyclerView = findViewById(R.id.scenes_Recycler);
        adapter = new ScenesAdapter();
        recyclerView.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(layoutManager);
        Intent intent = getIntent();
        final int position = intent.getIntExtra("position",0);

        FloatingActionButton playEpisode = findViewById(R.id.playEpisode);
        playEpisode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(SavedSettings.youtube_link+SavedSettings.getEpisodeFromAllArray(position).getVideo_id())));
            }
        });

        FloatingActionButton requestScene = findViewById(R.id.requestScene);
        requestScene.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Feature is unavailable.",Toast.LENGTH_SHORT).show();
            }
        });

        ImageView bgheader = findViewById(R.id.bgheader);
        Picasso.get().load(SavedSettings.thumbnail_link1+SavedSettings.getEpisodeFromAllArray(position).getVideo_id()+SavedSettings.thumbnail_link2).into(bgheader);
        bgheader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(SavedSettings.youtube_link+SavedSettings.getEpisodeFromAllArray(position).getVideo_id())));
            }
        });

        final Toolbar toolbar = findViewById(R.id.MyToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.drawable.baseline_keyboard_backspace_white_24);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapse_toolbar);
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.TextAppearance_MaterialComponents_Headline6);
        collapsingToolbarLayout.setTitle(SavedSettings.getEpisodeFromAllArray(position).getTitle().substring(20));
        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.rgb(255,255,0));
        collapsingToolbarLayout.setExpandedTitleColor(Color.rgb(255,255,255));
        collapsingToolbarLayout.setContentScrimColor(getResources().getColor(R.color.colorPrimary));
        collapsingToolbarLayout.setScrimAnimationDuration(100);


    }
}
