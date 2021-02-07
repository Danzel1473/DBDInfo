package com.example.dbdinfo;

public class Item {
    String name, image, type, rare, text;

    public Item(String name, String image, String type, String rare, String text){
        this.name = name;
        this.image = image;
        this.type = type;
        this.rare = rare;
        this.text= text;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public String getType() {
        return type;
    }

    public String getRare() {
        return rare;
    }

    public String getText() {
        return text;
    }
}
