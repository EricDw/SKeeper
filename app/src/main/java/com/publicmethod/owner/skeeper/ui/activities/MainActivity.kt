package com.publicmethod.owner.skeeper.ui.activities

import android.arch.lifecycle.LifecycleActivity
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import com.publicmethod.owner.skeeper.R
import com.publicmethod.owner.skeeper.SkeeperApplication
import com.publicmethod.owner.skeeper.ui.fragments.GameListFragment
import com.publicmethod.owner.skeeper.ui.viewmodels.SharedViewModel

class MainActivity : LifecycleActivity() {

    private var skeeperApplication : SkeeperApplication? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        skeeperApplication = application as SkeeperApplication?


        if (findViewById(R.id.fragment_container) != null) {
            if (savedInstanceState != null) {
                return
            }

            val gameListFragment = GameListFragment()

            val fragmentManager = supportFragmentManager

            fragmentManager.beginTransaction()
                    // The id is where the fragment should be placed
                    .add(R.id.fragment_container, gameListFragment)
                    .commit()
        }
    }
}
