package com.publicmethod.owner.skeeper.ui;

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

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    private RecyclerView mRecyclerView;
    private PlayerScoreCardAdapter mPlayerScoreCardAdapter;
    private LinearLayoutManager mLinearLayoutManager;
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    private FloatingActionButton mFab;

    private Player[] mPlayers;

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

        mPlayers = new Player[2];

        for (int i = 0; i < mPlayers.length; i++) {
            final String name = String.format("%s %s",
                    Keys.KEY_DEFAULT_PLAYER_NAME, (i + 1));

            final int score = Keys.KEY_DEFAULT_PLAYER_SCORE;
            mPlayers[i] = new Player(name, score);
        }

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mPlayerScoreCardAdapter = new PlayerScoreCardAdapter(mPlayers);
        mLinearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.setAdapter(mPlayerScoreCardAdapter);
        mSharedPreferences = getSharedPreferences(Keys.getPrefsFile(), MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();
        mFab = (FloatingActionButton) findViewById(R.id.fab);

//        Add Listeners
        mFab.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id) {
            case R.id.fab:
//                TODO: playLastGame();
                startCalculatorApplication();
            default:
        }


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
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

//        TODO: make this a switch case statement.
        if (id == R.id.nav_camera) {
            // Handle the camera action
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
}
