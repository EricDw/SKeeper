package com.publicmethod.owner.skeeper.ui;

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

    private FloatingActionButton mFab;

    private Player[] mPlayers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_keeper);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        Initialize variables

        mPlayers = new Player[getIntent().getIntExtra(Keys.KEY_DEFAULT_PLAYERS_NUMBER,2)];

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
//                Snackbar.make(v, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();

                default:

        }
    }
}
