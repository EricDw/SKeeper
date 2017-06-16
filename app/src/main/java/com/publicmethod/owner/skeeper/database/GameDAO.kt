package com.publicmethod.owner.skeeper.database

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.publicmethod.owner.skeeper.database.entities.Game

/**
 * Created by Eric De Wildt on 2017-06-16.
 */
@Dao interface GameDAO {

    @Insert
    fun insertGame(game: Game): Unit

    @Query("SELECT * FROM games WHERE game_id = :gameID")
    fun getGame(gameID: Int): LiveData<Game>

    @Query("SELECT * FROM games")
    fun getGames(): LiveData<List<Game>>

    @Update
    fun updateGame(game: Game): Unit

    @Delete
    fun deleteGame(game: Game): Unit

}