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
import com.publicmethod.owner.skeeper.model.PlayersHandler;

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

        mPlayerList = PlayersHandler.createPlayersList(mSharedPreferences);

        initializeRecyclerView();

        mFab = (FloatingActionButton) findViewById(R.id.fab);

//        Add Listeners
        mFab.setOnClickListener(this);

//        Run Logic
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
                PlayersHandler.addNewPlayers(mPlayerList, mSharedPreferences, mPlayerScoreCardAdapter, 1);
                break;

            case R.id.action_clear_players:
                resetPlayersList();
                break;

            default:
                return true;

        }

        return super.onOptionsItemSelected(item);
    }

    private void resetPlayersList() {
        mEditor.clear().apply();
        mPlayerList.clear();
        PlayersHandler.addNewPlayers(mPlayerList, mSharedPreferences, mPlayerScoreCardAdapter,
                mSharedPreferences.getInt(Keys.KEY_PLAYERS_AMOUNT, Keys.KEY_DEFAULT_PLAYERS_AMOUNT));
        mPlayerScoreCardAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch (id) {
            case R.id.nav_settings:
                Intent intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                break;

            case R.id.nav_gallery:
                break;

            case R.id.nav_slideshow:
                break;

            case R.id.nav_manage:
                break;

            case R.id.nav_share:
                break;

            case R.id.nav_send:
                break;
            default:
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
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
