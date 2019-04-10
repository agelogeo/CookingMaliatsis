package com.agelogeo.cookingmaliatsis;

import java.util.ArrayList;

public class Episode {
    int id;
    String title;
    String thumbnail;
    ArrayList<Scene> episodeScenes = new ArrayList<Scene>();

    public Episode(int id, String title, String thumbnail, ArrayList<Scene> episodeScenes) {
        this.id = id;
        this.title = title;
        this.thumbnail = thumbnail;
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

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
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
