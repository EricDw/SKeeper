package com.publicmethod.owner.skeeper.model;

import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;

import com.publicmethod.owner.skeeper.adapters.PlayerScoreCardAdapter;
import com.publicmethod.owner.skeeper.util.Constants;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Eric on 2016-03-18.
 */
public class PlayersHandler {

    private SharedPreferences mSharedPreferences;

    public PlayersHandler(SharedPreferences sharedPreferences) {
        mSharedPreferences = sharedPreferences;
    }

    /**
     * Returns an ArrayLists of players and sets the scores from the SharedPreferences file.
     *
     * @param sharedPreferences The file where players information is stored.
     * @return An ArrayList with a type of Player.
     */
    public static ArrayList<Player> createPlayersList(SharedPreferences sharedPreferences, RecyclerView.Adapter adapter) {
        ArrayList<Player> playerArrayList = new ArrayList<Player>();

        for (int i = 0; i < getPlayersAmount(sharedPreferences); i++) {

            playerArrayList.add(new Player(Constants.KEY_DEFAULT_PLAYER_NAME + (i + 1),
                    sharedPreferences.getInt(Constants.KEY_PLAYER_SCORE + i, Constants.KEY_DEFAULT_PLAYER_SCORE)));
            adapter.notifyItemInserted(i);
        }

        return playerArrayList;
    }

    public static int getPlayersAmount(SharedPreferences sharedPreferences) {
        return sharedPreferences.getInt(Constants.KEY_PLAYERS_AMOUNT, Constants.KEY_DEFAULT_PLAYERS_AMOUNT);
    }

    /**
     * @param playerArrayList
     * @param sharedPreferences
     * @param playerScoreCardAdapter
     * @param numberOfPlayersToAdd
     */
    // TODO: 2016-03-18 Add documentation
    public static void addNewPlayers(List<Player> playerArrayList, SharedPreferences sharedPreferences,
                                     PlayerScoreCardAdapter playerScoreCardAdapter,
                                     int numberOfPlayersToAdd) {

        for (int i = 0; i < numberOfPlayersToAdd; i++) {

            String name = String.format("%s%s", Constants.KEY_DEFAULT_PLAYER_NAME, playerArrayList.size() + 1);

            Player player = new Player(name, sharedPreferences.getInt(Constants.KEY_PLAYER_SCORE + String.valueOf(i + 1),
                    Constants.KEY_DEFAULT_PLAYER_SCORE));

            playerArrayList.add(playerArrayList.size(), player);
            playerScoreCardAdapter.notifyItemInserted(playerArrayList.size());
        }
    }

    /**
     * Saves the amount of items in the player list as well as the their scores to
     * the SharedPreferences file.
     */
    public static void savePlayerInformation(List<Player> playerList, SharedPreferences.Editor sharedPreferencesEditor) {
        for (int i = 0; i < playerList.size(); i++) {

            sharedPreferencesEditor.putInt(Constants.KEY_PLAYER_SCORE + i, playerList.get(i).getScore());
        }

        sharedPreferencesEditor.putInt(Constants.KEY_PLAYERS_AMOUNT, playerList.size());
        sharedPreferencesEditor.apply();
    }

    public static void resetPlayersList(List<Player> listOfPlayers,
                                        SharedPreferences sharedPreferences,
                                        PlayerScoreCardAdapter playerScoreCardAdapter) {
        if (listOfPlayers != null) {
            sharedPreferences.edit().clear().apply();
            listOfPlayers.clear();
            addNewPlayers(listOfPlayers, sharedPreferences, playerScoreCardAdapter,
                    sharedPreferences.getInt(Constants.KEY_PLAYERS_AMOUNT, Constants.KEY_DEFAULT_PLAYERS_AMOUNT));
            playerScoreCardAdapter.notifyDataSetChanged();
        } else {
            return;
        }
    }

}
