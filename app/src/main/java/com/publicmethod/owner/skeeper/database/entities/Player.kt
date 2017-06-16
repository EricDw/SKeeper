package com.publicmethod.owner.skeeper.database.entities

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by Eric on 2016-02-16.
 */
@Entity(tableName = "players")
data class Player(
        @PrimaryKey()
        @ColumnInfo(name = "player_id")
        var id: Int,
        @ColumnInfo(name = "player_name")
        var name: String?,
        @ColumnInfo(name = "player_score")
        var score: Int?) {

    constructor() : this(id = 0, name = "", score = 0)
}
