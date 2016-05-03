package com.publicmethod.owner.skeeper.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
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
import com.publicmethod.owner.skeeper.model.Player;
import com.publicmethod.owner.skeeper.model.PlayersHandler;
import com.publicmethod.owner.skeeper.util.Constants;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView mRecyclerView;
    private PlayerScoreCardAdapter mPlayerScoreCardAdapter;
    private LinearLayoutManager mLinearLayoutManager;
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    private FloatingActionButton mFab;

    private List<Player> mPlayerList;

    @SuppressLint("CommitPrefEdits")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_bar_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mSharedPreferences = getSharedPreferences(Constants.getPrefsFile(), MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();
        mPlayerList = new ArrayList<Player>();

        mFab = (FloatingActionButton) findViewById(R.id.fab);

        initializeRecyclerView();



//        Add Listeners
        mFab.setOnClickListener(this);

//        Run Logic
        PlayersHandler.createPlayersList(mSharedPreferences, mPlayerScoreCardAdapter);
        addPlayersToList(PlayersHandler.createPlayersList(mSharedPreferences, mPlayerScoreCardAdapter));
    }

    // TODO: 2016-05-02 Move this to PlayerScoreCardAdapter.class
    private void addPlayersToList(ArrayList<Player> playersList) {
        for (int i = 0; i < playersList.size(); i++) {
            mPlayerList.add(playersList.get(i));
            mPlayerScoreCardAdapter.notifyItemInserted(i);
        }
    }

    private void initializeRecyclerView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mPlayerScoreCardAdapter = new PlayerScoreCardAdapter(mPlayerList);
        mLinearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.setAdapter(mPlayerScoreCardAdapter);
        mRecyclerView.setHasFixedSize(false);

    }

    private void startCalculatorActivity() {
        Intent intent = new Intent(this, CalculatorActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id) {
            case R.id.fab:
                startCalculatorActivity();
            default:
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
//         Handle action bar item clicks here. The action bar will
//         automatically handle clicks on the Home/Up button, so long
//         as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id) {
            case R.id.action_add_player:
                PlayersHandler.addNewPlayers(mPlayerList, mSharedPreferences, mPlayerScoreCardAdapter, 1);
                break;
            case R.id.action_clear_players:
                PlayersHandler.resetPlayersList(mPlayerList, mSharedPreferences, mPlayerScoreCardAdapter);
                break;
            default:
                return true;

        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        PlayersHandler.savePlayerInformation(mPlayerList, mEditor);
    }

    @Override
    protected void onPause() {
        super.onPause();
        PlayersHandler.savePlayerInformation(mPlayerList, mEditor);
    }
}
