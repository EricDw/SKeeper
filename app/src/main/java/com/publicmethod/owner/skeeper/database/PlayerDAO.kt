package com.publicmethod.owner.skeeper.database

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.publicmethod.owner.skeeper.database.entities.Player

/**
 * Created by Eric De Wildt on 2017-06-15.
 */
@android.arch.persistence.room.Dao
interface PlayerDAO {


    @android.arch.persistence.room.Insert
    fun insertPlayer(player: com.publicmethod.owner.skeeper.database.entities.Player): Unit

    @android.arch.persistence.room.Query("SELECT * FROM players WHERE player_id = :arg0")
    fun getPlayers(gameID: Int): android.arch.lifecycle.LiveData<List<Player>>

    @android.arch.persistence.room.Update
    fun updatePlayer(player: com.publicmethod.owner.skeeper.database.entities.Player): Unit

    @android.arch.persistence.room.Delete
    fun deletePlayer(player: com.publicmethod.owner.skeeper.database.entities.Player): Unit


}