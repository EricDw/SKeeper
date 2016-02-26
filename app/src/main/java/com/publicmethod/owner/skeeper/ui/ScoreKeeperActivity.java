package com.publicmethod.owner.skeeper.ui;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.publicmethod.owner.skeeper.R;
import com.publicmethod.owner.skeeper.adapters.PlayerScoreCardAdapter;
import com.publicmethod.owner.skeeper.constants.Keys;
import com.publicmethod.owner.skeeper.model.Player;

public class ScoreKeeperActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView mRecyclerView;
    private PlayerScoreCardAdapter mPlayerScoreCardAdapter;
    private LinearLayoutManager mLinearLayoutManager;
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    private FloatingActionButton mFab;

    private Player[] mPlayers;
    private Player[] mDefaultPlayersArray;

    @SuppressLint("CommitPrefEdits")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_keeper);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        Initialize variables

        mSharedPreferences = getSharedPreferences(Keys.getPrefsFile(), MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();

        mPlayers = new Player[getNumberOfPlayers()];
        mDefaultPlayersArray = new Player[2];

        setPlayerInformation(mDefaultPlayersArray, true);
        savePlayerInformation();

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mPlayerScoreCardAdapter = new PlayerScoreCardAdapter(mPlayers);
        mLinearLayoutManager = new LinearLayoutManager(this);
        mFab = (FloatingActionButton) findViewById(R.id.fab);


//        Set listeners/adapters/managers
        mFab.setOnClickListener(this);
        mRecyclerView.setAdapter(mPlayerScoreCardAdapter);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);

        mRecyclerView.setHasFixedSize(true);


    }

    private void setPlayerInformation(Player[] players, boolean isDefaultPlayerArray) {

        if (isDefaultPlayerArray) {

            for (int i = 0; i < players.length; i++) {
                players[i] = new Player(Keys.KEY_PLAYER_NAME + String.valueOf(i + 1),
                        Keys.KEY_DEFAULT_PLAYER_SCORE);
            }

        } else {

            for (int i = 0; i < players.length; i++) {
                String playerName = mSharedPreferences.getString(Keys.KEY_PLAYER_NAME + String.valueOf(i + 1),
                        Keys.KEY_DEFAULT_PLAYER_NAME + " " + String.valueOf(i + 1));

                int playerScore = mSharedPreferences.getInt(Keys.KEY_PLAYER_SCORE + String.valueOf(i),
                        Keys.KEY_DEFAULT_PLAYER_SCORE);

                players[i] = new Player(playerName, playerScore);
            }
        }
    }

    private void savePlayerInformation() {
        for (int i = 0; i < mPlayers.length; i++) {

            mEditor.putString(Keys.KEY_PLAYER_NAME + String.valueOf(i + 1),
                    mPlayers[i].getName()).
                    apply();

            mEditor.putInt(Keys.KEY_PLAYER_SCORE + String.valueOf(i + 1),
                    mPlayers[i].getScore()).
                    apply();

        }
        mEditor.putInt(Keys.KEY_PLAYERS_AMOUNT,
                getNumberOfPlayers()).
                apply();
    }

    /**
     * Returns the current amount of players stored in Shared Preferences.
     *
     * @return int
     */
    private int getNumberOfPlayers() {
        return mSharedPreferences.getInt(Keys.KEY_PLAYERS_AMOUNT, Keys.KEY_DEFAULT_PLAYERS_AMOUNT);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.score_keeper_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

//        TODO: Refactor into switch case statement.
        if (id == R.id.action_add_player) {
            mEditor.putInt(Keys.KEY_PLAYERS_AMOUNT, getNumberOfPlayers() + 1);
            //savePlayerInformation();
            //setPlayerInformation(mPlayers);

            this.mPlayerScoreCardAdapter.notifyItemInserted(getNumberOfPlayers());


//            TODO: Add new player to list.
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id) {
            case R.id.fab:

                mEditor.clear().apply();
                mPlayerScoreCardAdapter.notifyDataSetChanged();

            default:

        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        savePlayerInformation();


    }
}
