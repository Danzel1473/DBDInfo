package com.example.dbdinfo;

public class Addon {
    String name, image, forwho, rare,  text;
    public Addon(String name, String image, String forwho, String rare, String text){
        this.name = name;
        this.image = image;
        this.forwho = forwho;
        this.rare = rare;
        this. text = text;
    }
    public Addon(String name, String image, String rare, String text){
        this.name = name;
        this.image = image;
        this.rare = rare;
        this. text = text;
    }

    public String getName() {
        return name;
    }

    public String getForwho() {
        return forwho;
    }

    public String getImage() {
        return image;
    }

    public String getRare() {
        return rare;
    }

    public String getText() {
        return text;
    }
}
