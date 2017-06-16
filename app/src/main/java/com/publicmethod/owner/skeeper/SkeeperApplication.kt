package com.publicmethod.owner.skeeper

import android.app.Application
import com.publicmethod.owner.skeeper.dependencyinjection.DaggerSkeeperComponent
import com.publicmethod.owner.skeeper.dependencyinjection.SkeeperComponent
import com.publicmethod.owner.skeeper.dependencyinjection.SkeeperModule

/**
 * Created by Owner on 2017-06-14.
 */
class SkeeperApplication : Application() {

    private val skeeperComponent = createSkeeperComponent()


    private fun createSkeeperComponent(): SkeeperComponent {

        return DaggerSkeeperComponent
                .builder()
                .skeeperModule(SkeeperModule(this))
                .build()
    }

    fun getSkeeperComponent(): SkeeperComponent {
        return skeeperComponent
    }

}