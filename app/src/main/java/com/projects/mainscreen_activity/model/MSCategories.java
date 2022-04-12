package com.projects.mainscreen_activity.model;

public class MSCategories {
    int Id;
    String Name;
    String Picture;
    String Color;
    boolean IsPicket;

    public MSCategories(int id, String name, String picture, String color, boolean isPicket) {
        Id = id;
        Name = name;
        Picture = picture;
        Color = color;
        IsPicket = isPicket;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPicture() {
        return Picture;
    }

    public void setPicture(String picture) {
        Picture = picture;
    }

    public String getColor() {
        return Color;
    }

    public void setColor(String color) {
        Color = color;
    }

    public boolean isPicket() {
        return IsPicket;
    }

    public void setPicket(boolean picket) {
        IsPicket = picket;
    }
}
