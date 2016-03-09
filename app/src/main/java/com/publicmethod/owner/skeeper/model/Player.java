package com.publicmethod.owner.skeeper.model;

import com.publicmethod.owner.skeeper.constants.Keys;

import java.util.ArrayList;

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

    public static ArrayList<Player> createContactsList(int numContacts) {
        ArrayList<Player> contacts = new ArrayList<Player>();

        for (int i = 0; i < numContacts; i++) {

            contacts.add(new Player(Keys.KEY_DEFAULT_PLAYER_NAME + (i + 1), Keys.KEY_DEFAULT_PLAYER_SCORE));
        }

        return contacts;
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
