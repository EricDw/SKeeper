package com.publicmethod.owner.skeeper.ui.fragments

import android.arch.lifecycle.LifecycleFragment
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.publicmethod.owner.skeeper.R
import com.publicmethod.owner.skeeper.SkeeperApplication
import com.publicmethod.owner.skeeper.dependencyinjection.SkeeperFactory
import com.publicmethod.owner.skeeper.ui.handlers.ClickHandler
import com.publicmethod.owner.skeeper.ui.viewmodels.SharedViewModel

/**
 * Created by Owner on 2017-06-14.
 */
class GameListFragment : LifecycleFragment(), ClickHandler {

    private val skeeperApplication by lazy { activity.application as SkeeperApplication }

    private val sharedViewModel by lazy {
        ViewModelProviders
                .of(activity)
                .get(SkeeperFactory(skeeperApplication).create(SharedViewModel::class.java).javaClass)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = LayoutInflater.from(context).inflate(R.layout.app_bar_main, container)



        return view
    }

    override fun onClick(obj: Any) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}