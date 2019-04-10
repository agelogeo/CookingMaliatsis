package com.agelogeo.cookingmaliatsis;

import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class SavedSettings {

    public static boolean recentlyToTop = false;
    public static boolean showThumbnails = true;
    public static RecyclerView recyclerView ;
    public static ArrayList<Episode> staticAllEpisodes = new ArrayList<Episode>();
    public static ArrayList<Episode> staticFavoriteEpisodes = new ArrayList<Episode>();

    public static boolean isShowThumbnails() {
        return showThumbnails;
    }

    public static void setShowThumbnails(boolean showThumbnails) {
        SavedSettings.showThumbnails = showThumbnails;
    }

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
