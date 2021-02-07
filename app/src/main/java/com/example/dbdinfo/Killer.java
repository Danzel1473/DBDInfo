package com.example.dbdinfo;

import java.io.Serializable;

public class Killer implements Serializable {
    String nick, name, speed, abilspeed, heartbeat, weapon, dlc, abiltextdefault, abiltext1, abiltext2, adv, disadv, tiptext, killerimg, abilimg, abiltitle1, abiltitle2, abilname;
    public Killer(String killerimg, String nick, String name, String speed, String abilspeed, String heartbeat, String weapon, String dlc, String abiltextdefault, String abiltext1, String abiltext2, String adv,
           String disadv, String tiptext,  String abilimg, String abilname, String abiltitle1, String abiltitle2){
        this.nick = nick;
        this.name = name;
        this.speed = speed;
        this.abilspeed = abilspeed;
        this.heartbeat = heartbeat;
        this.weapon = weapon;
        this.dlc = dlc;
        this.abiltextdefault = abiltextdefault;
        this.abiltext1 = abiltext1;
        this.abiltext2 = abiltext2;
        this.adv = adv;
        this.disadv = disadv;
        this.tiptext = tiptext;
        this.killerimg = killerimg;
        this.abilimg = abilimg;
        this.abilname = abilname;
        this.abiltitle1 = abiltitle1;
        this.abiltitle2 = abiltitle2;
    }

    public Killer(String killerimg, String nick){
        this.nick = nick;
        this.killerimg = killerimg;
    }

    public String getName() {
        return name;
    }

    public String getNick() {
        return nick;
    }

    public String getSpeed() {
        return speed;
    }

    public String getAbilspeed() {
        return abilspeed;
    }

    public String getHeartbeat() {
        return heartbeat;
    }

    public String getWeapon() {
        return weapon;
    }

    public String getDlc() {
        return dlc;
    }

    public String getAbiltextdefault() {
        return abiltextdefault;
    }

    public String getAbiltext1() {
        return abiltext1;
    }

    public String getAbiltext2() {
        return abiltext2;
    }

    public String getAdv() {
        return adv;
    }

    public String getDisadv() {
        return disadv;
    }

    public String getTiptext() {
        return tiptext;
    }

    public String getKillerimg() {
        return killerimg;
    }

    public String getAbilimg() {
        return abilimg;
    }

    public String getAbiltitle1() {
        return abiltitle1;
    }

    public String getAbiltitle2() {
        return abiltitle2;
    }

    public String getAbilname() {
        return abilname;
    }
}
