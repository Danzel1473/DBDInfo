package com.example.dbdinfo;

import java.io.Serializable;

public class Perk implements Serializable {
    String name, type, img, forwho, text;
    int[] intforcolor;
    Perk(){
        name="";
        type="";
        img="";
        forwho="";
    }
    public Perk(String name,String forwho,String img,String type,String text, int[] intforcolor){
        this.name= name;
        this.forwho = forwho;
        this.img = img;
        this.type = type;
        this.text = text;
        this.intforcolor = intforcolor;
    }
    public Perk(String name,String img,String text, int[] intforcolor){
        this.name= name;
        this.img = img;
        this.text = text;
        this.intforcolor = intforcolor;
    }
    public String getName() {
        return name;
    }

    public String getImg() {
        return img;
    }

    public String getType() {
        return type;
    }

    public String getForwho() {
        return forwho;
    }

    public String getText() {
        return text;
    }

    public int[] getIntforcolor() {
        return intforcolor;
    }
}
