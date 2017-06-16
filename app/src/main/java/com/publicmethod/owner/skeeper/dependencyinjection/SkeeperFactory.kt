package com.publicmethod.owner.skeeper.dependencyinjection

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.publicmethod.owner.skeeper.SkeeperApplication
import javax.inject.Inject

/**
 * Created by Owner on 2017-06-14.
 */
class SkeeperFactory
@Inject
constructor(private val skeeperApplication: SkeeperApplication) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>?): T {
        val t = super.create(modelClass)

        if (t is SkeeperComponent.Injectable) {
            t.inject(skeeperApplication.getSkeeperComponent())
        }
        return t
    }
}