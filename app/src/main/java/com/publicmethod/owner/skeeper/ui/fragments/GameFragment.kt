package com.publicmethod.owner.skeeper.ui.fragments


import android.arch.lifecycle.LifecycleFragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.publicmethod.owner.skeeper.R
import com.publicmethod.owner.skeeper.ui.handlers.ClickHandler


class GameFragment : LifecycleFragment(), ClickHandler {


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
