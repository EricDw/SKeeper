package com.publicmethod.owner.skeeper.ui

import android.annotation.SuppressLint
import android.content.*
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.publicmethod.owner.skeeper.R
import com.publicmethod.owner.skeeper.adapters.PlayerScoreCardAdapter
import com.publicmethod.owner.skeeper.model.Player
import com.publicmethod.owner.skeeper.model.PlayersHandler
import com.publicmethod.owner.skeeper.util.Constants
import java.util.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    private var mRecyclerView: RecyclerView? = null
    private var mPlayerScoreCardAdapter: PlayerScoreCardAdapter? = null
    private var mLinearLayoutManager: LinearLayoutManager? = null
    private var mSharedPreferences: SharedPreferences? = null
    private var mEditor: SharedPreferences.Editor? = null

    private var mFab: FloatingActionButton? = null

    private var mPlayerList: MutableList<Player>? = null

    @SuppressLint("CommitPrefEdits")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        //        Initialize Views and Variables
        val drawer = findViewById(R.id.drawer_layout) as DrawerLayout
        val toggle = ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer.setDrawerListener(toggle)
        toggle.syncState()

        val navigationView = findViewById(R.id.nav_view) as NavigationView
        navigationView.setNavigationItemSelectedListener(this)

        mSharedPreferences = getSharedPreferences(Constants.getPrefsFile(), Context.MODE_PRIVATE)
        mEditor = mSharedPreferences!!.edit()
        mPlayerList = ArrayList<Player>()

        initializeRecyclerView()
        PlayersHandler.createPlayersList(mSharedPreferences, mPlayerScoreCardAdapter)
        addPlayersToList(PlayersHandler.createPlayersList(mSharedPreferences, mPlayerScoreCardAdapter))


        mFab = findViewById(R.id.fab) as FloatingActionButton

        //        Add Listeners
        mFab!!.setOnClickListener(this)

        //        Run Logic
    }

    private fun addPlayersToList(playersList: ArrayList<Player>) {
        for (i in playersList.indices) {
            mPlayerList!!.add(playersList[i])
            mPlayerScoreCardAdapter!!.notifyItemInserted(i)
        }
    }

    private fun initializeRecyclerView() {
        mRecyclerView = findViewById(R.id.recyclerView) as RecyclerView
        mPlayerScoreCardAdapter = PlayerScoreCardAdapter(mPlayerList)
        mLinearLayoutManager = LinearLayoutManager(this)
        mRecyclerView!!.layoutManager = mLinearLayoutManager
        mRecyclerView!!.adapter = mPlayerScoreCardAdapter
        mRecyclerView!!.setHasFixedSize(false)

    }

    // TODO: 2016-03-21 Convert into dialog themed activity and move to its own class.
    private fun startCalculatorApplication() {
        val intent = Intent()
        intent.action = Intent.ACTION_MAIN
        intent.addCategory(Intent.CATEGORY_LAUNCHER)
        intent.component = ComponentName(Constants.CALCULATOR_PACKAGE_NAME,
                Constants.CALCULATOR_CLASS_NAME)
        try {
            this.startActivity(intent)
        } catch (noSuchActivity: ActivityNotFoundException) {
            // TODO: 2016-04-23 handle exception where calculator intent filter is not registered

        }

    }

    override fun onClick(v: View) {
        val id = v.id

        when (id) {
            R.id.fab -> startCalculatorApplication()
        }


    }

    override fun onBackPressed() {
        val drawer = findViewById(R.id.drawer_layout) as DrawerLayout
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //         Handle action bar item clicks here. The action bar will
        //         automatically handle clicks on the Home/Up button, so long
        //         as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId


        when (id) {
            R.id.action_add_player -> PlayersHandler.addNewPlayers(mPlayerList, mSharedPreferences, mPlayerScoreCardAdapter, 1)

            R.id.action_clear_players -> PlayersHandler.resetPlayersList(mPlayerList, mSharedPreferences, mPlayerScoreCardAdapter)

            else -> return true
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        val id = item.itemId

        when (id) {
            R.id.nav_settings -> {
                val intent = Intent(this, SettingsActivity::class.java)
                startActivity(intent)
            }

            R.id.nav_gallery -> {
            }

            R.id.nav_slideshow -> {
            }

            R.id.nav_manage -> {
            }

            R.id.nav_share -> {
            }

            R.id.nav_send -> {
            }
        }

        val drawer = findViewById(R.id.drawer_layout) as DrawerLayout
        drawer.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        PlayersHandler.savePlayerInformation(mPlayerList, mEditor)
    }

    override fun onPause() {
        super.onPause()
        PlayersHandler.savePlayerInformation(mPlayerList, mEditor)
    }
}
