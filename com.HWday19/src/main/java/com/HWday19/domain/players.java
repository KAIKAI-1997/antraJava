package com.HWday19.domain;

import lombok.Data;

import javax.persistence.*;
@Data
@Entity
@Table(name = "player")
public class players {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long ID;

    @Column(name = "Occupation")
    private String Occupation;

    @Column(name = "Level")
    private String Level;

    @Column(name = "Attack")
    private String Attack;

    @Column(name = "Defence")
    private String Defence;

    public players(long ID, String occupation, String level, String attack, String defence) {
        this.ID = ID;
        Occupation = occupation;
        Level = level;
        Attack = attack;
        Defence = defence;
    }

    public players() {

    }

    @Override
    public String toString() {
        return "players{" +
                "ID=" + ID +
                ", Occupation='" + Occupation + '\'' +
                ", Level='" + Level + '\'' +
                ", Attack='" + Attack + '\'' +
                ", Defence='" + Defence + '\'' +
                '}';
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public void setOccupation(String occupation) {
        Occupation = occupation;
    }

    public void setLevel(String level) {
        Level = level;
    }

    public void setAttack(String attack) {
        Attack = attack;
    }

    public void setDefence(String defence) {
        Defence = defence;
    }

    public long getID() {
        return ID;
    }

    public String getOccupation() {
        return Occupation;
    }

    public String getLevel() {
        return Level;
    }

    public String getAttack() {
        return Attack;
    }

    public String getDefence() {
        return Defence;
    }
}
