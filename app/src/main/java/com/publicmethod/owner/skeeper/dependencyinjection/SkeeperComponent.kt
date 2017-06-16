package com.publicmethod.owner.skeeper.dependencyinjection

import com.publicmethod.owner.skeeper.ui.viewmodels.SharedViewModel
import dagger.Component
import javax.inject.Singleton

/**
 * Created by Owner on 2017-06-14.
 */
@Singleton
@Component(modules = arrayOf(SkeeperModule::class))
interface SkeeperComponent {

    fun inject(sharedViewModel: SharedViewModel): Unit

    interface Injectable {
        fun inject(skeeperComponent: SkeeperComponent): Unit
    }
}