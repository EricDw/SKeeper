package com.publicmethod.owner.skeeper.model;

import com.publicmethod.owner.skeeper.constants.Keys;

import java.util.ArrayList;

/**
 * Created by Eric on 2016-02-16.
 */
public class Player {

    private String mName;
    private int mScore;

    public Player(String name, int score) {

        mName = name;
        mScore = score;
    }

    /**
     *
     * @param numPlayers The amount of players to be added to the Array List.
     * @return An ArrayList with a type of Player.
     */
    public static ArrayList<Player> createPlayersList(int numPlayers) {
        ArrayList<Player> players = new ArrayList<Player>();

        for (int i = 0; i < numPlayers; i++) {

            players.add(new Player(Keys.KEY_DEFAULT_PLAYER_NAME + (i + 1), Keys.KEY_DEFAULT_PLAYER_SCORE));
        }

        return players;
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
