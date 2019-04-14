package com.agelogeo.cookingmaliatsis;

import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class SavedSettings {

    public static String youtube_link = "https://www.youtube.com/watch?v=";
    public static String thumbnail_link1 = "https://img.youtube.com/vi/";
    public static String thumbnail_link2 = "/hqdefault.jpg";
    public static String youtube_timestamp = "&t=";
    public static boolean recentlyToTop = false;
    //public static boolean showThumbnails = true;
    public static RecyclerView recyclerView ;
    public static ArrayList<Episode> staticAllEpisodes = new ArrayList<Episode>();
    public static ArrayList<Episode> staticFavoriteEpisodes = new ArrayList<Episode>();
    public static String API_KEY = "AIzaSyDq05yHLnrEiz7AViCdwxGofiVpIjCxDzk";

    public static boolean isRecentlyToTop() {
        return recentlyToTop;
    }

    public static void setRecentlyToTop(boolean recentlyToTop) {
        SavedSettings.recentlyToTop = recentlyToTop;
    }

    public static ArrayList<Episode> getStaticAllEpisodes() {
        return staticAllEpisodes;
    }

    public static void setStaticAllEpisodes(ArrayList<Episode> staticAllEpisodes) {
        SavedSettings.staticAllEpisodes = staticAllEpisodes;
    }

    public static ArrayList<Episode> getStaticFavoriteEpisodes() {
        return staticFavoriteEpisodes;
    }

    public static void setStaticFavoriteEpisodes(ArrayList<Episode> staticFavoriteEpisodes) {
        SavedSettings.staticFavoriteEpisodes = staticFavoriteEpisodes;
    }

    public static void addOnStaticAllEpisodes(Episode episode){
        staticAllEpisodes.add(episode);
    }

    public static void addOnStaticFavoriteEpisodes(Episode episode){
        staticFavoriteEpisodes.add(episode);
    }

    public static Episode getEpisodeFromAllArray(int i){
        return staticAllEpisodes.get(i);
    }

    public static RecyclerView getRecyclerView() {
        return recyclerView;
    }

    public static void setRecyclerView(RecyclerView recyclerView) {
        SavedSettings.recyclerView = recyclerView;
    }
}
