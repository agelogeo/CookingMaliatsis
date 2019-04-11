package com.agelogeo.cookingmaliatsis;

import java.util.ArrayList;

public class Episode {
    int id;
    String title;
    String video_id;
    ArrayList<Scene> episodeScenes = new ArrayList<Scene>();

    public Episode(int id, String title, String video_id, ArrayList<Scene> episodeScenes) {
        this.id = id;
        this.title = title;
        this.video_id = video_id;
        this.episodeScenes = episodeScenes;
    }

    public Episode() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVideo_id() {
        return video_id;
    }

    public void setVideo_id(String video_id) {
        this.video_id = video_id;
    }

    public ArrayList<Scene> getEpisodeScenes() {
        return episodeScenes;
    }

    public void setEpisodeScenes(ArrayList<Scene> episodeScenes) {
        this.episodeScenes = episodeScenes;
    }

    public void addOnEpisodeScenes(Scene scene){
        this.episodeScenes.add(scene);
    }
}
