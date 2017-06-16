package com.publicmethod.owner.skeeper.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.publicmethod.owner.skeeper.database.entities.Game
import com.publicmethod.owner.skeeper.database.entities.Player

/**
 * Created by Owner on 2017-06-14.
 */
@Database(entities = arrayOf(Game::class, Player::class), version = 1)
abstract class SkeeperDataBase : RoomDatabase() {

    abstract fun gameDAO(): GameDAO

    abstract fun playerDAO(): PlayerDAO

}