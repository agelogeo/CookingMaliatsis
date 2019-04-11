package com.agelogeo.cookingmaliatsis;

public class Scene {
    String link;
    int timestamp = 0;

    public Scene(String link) {
        this.link = link;
    }

    public Scene() {
    }

    public Scene(int timestamp) {
        this.timestamp = timestamp;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }
}
