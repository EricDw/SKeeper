package com.publicmethod.owner.skeeper.dependencyinjection

import android.arch.persistence.room.Room
import android.content.Context
import com.publicmethod.owner.skeeper.SkeeperApplication
import com.publicmethod.owner.skeeper.database.SkeeperDataBase
import com.publicmethod.owner.skeeper.database.GameDAO
import com.publicmethod.owner.skeeper.database.PlayerDAO
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Owner on 2017-06-14.
 */
@Module
class SkeeperModule(val skeeperApplication: SkeeperApplication) {

    @Provides
    @Singleton
    fun providesContext(): Context {
        return skeeperApplication
    }

    @Provides
    @Singleton
    fun providesItemDatabase(skeeperApplication: SkeeperApplication): SkeeperDataBase {
        return Room.databaseBuilder(skeeperApplication, SkeeperDataBase::class.java, "game_database").build()
    }

    @Provides
    @Singleton
    fun providesGameDAO(skeeperDataBase: SkeeperDataBase): GameDAO {
        return skeeperDataBase.gameDAO()
    }

    @Provides
    @Singleton
    fun providesPlayerDAO(skeeperDataBase: SkeeperDataBase): PlayerDAO {
        return skeeperDataBase.playerDAO()
    }

}