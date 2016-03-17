package com.publicmethod.owner.skeeper.ui;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
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

import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

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
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        Initialize Views and Variables
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mSharedPreferences = getSharedPreferences(Keys.getPrefsFile(), MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();

        mPlayerList = Player.createPlayersList(getPlayersAmount(),mSharedPreferences);

        initializeRecyclerView();

        mFab = (FloatingActionButton) findViewById(R.id.fab);

//        Add Listeners
        mFab.setOnClickListener(this);

//        Run Logic
    }


    private int getPlayersAmount() {
        return mSharedPreferences.getInt(Keys.KEY_PLAYERS_AMOUNT, Keys.KEY_DEFAULT_PLAYERS_AMOUNT);
    }

    private void initializeRecyclerView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mPlayerScoreCardAdapter = new PlayerScoreCardAdapter(mPlayerList);
        mLinearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.setAdapter(mPlayerScoreCardAdapter);
        mRecyclerView.setHasFixedSize(false);

    }


    private void startCalculatorApplication() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        intent.setComponent(new ComponentName(Keys.CALCULATOR_PACKAGE_NAME,
                Keys.CALCULATOR_CLASS_NAME));
        try {
            this.startActivity(intent);
        } catch (ActivityNotFoundException noSuchActivity) {
            // handle exception where calculator intent filter is not registered
        }
    }

    private void addNewPlayer() {

        final String name = String.format("%s%s",
                Keys.KEY_DEFAULT_PLAYER_NAME, (mPlayerList.size() + 1));

        Player player = new Player(name, mSharedPreferences.getInt(Keys.KEY_PLAYER_SCORE + String.valueOf(mPlayerList.size() + 1),
                Keys.KEY_DEFAULT_PLAYER_SCORE));

        mPlayerList.add(mPlayerList.size(), player);
        mPlayerScoreCardAdapter.notifyItemInserted(mPlayerList.size());

    }

    /**
     * Saves the amount of items in the player list as well as the their scores to
     * the SharedPreferences file.
     */
    private void savePlayerInformation() {
        for (int i = 0; i < mPlayerList.size(); i++) {

            mEditor.putInt(Keys.KEY_PLAYER_SCORE + i, mPlayerList.get(i).getScore());
        }

        mEditor.putInt(Keys.KEY_PLAYERS_AMOUNT,mPlayerList.size());
        mEditor.apply();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id) {
            case R.id.fab:
                startCalculatorApplication();
            default:
        }


    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
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

//        noinspection SimplifiableIfStatement
        switch (id) {
            case R.id.action_add_player:
                addNewPlayer();
                break;

            case R.id.action_clear_players:
                mEditor.clear().apply();
                mPlayerList.clear();
                addNewPlayer();
                addNewPlayer();
                mPlayerScoreCardAdapter.notifyDataSetChanged();
                break;

            default:
                return true;

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

//        TODO: make this a switch case statement.
        if (id == R.id.nav_settings) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        savePlayerInformation();
    }

    @Override
    protected void onPause() {
        super.onPause();
        savePlayerInformation();
    }
}
