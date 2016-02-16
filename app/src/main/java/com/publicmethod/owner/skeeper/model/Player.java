package com.publicmethod.owner.skeeper.model;

/**
 * Created by Owner on 2016-02-16.
 */
public class Player {

    private String mName;
    private int mScore;

    public Player(String name, int score) {

        mName = name;
        mScore = score;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public int getScore() {
        return mScore;
    }

    public void setScore(int score) {
        mScore = score;
    }
}
