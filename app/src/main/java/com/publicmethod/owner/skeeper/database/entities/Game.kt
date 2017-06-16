package com.publicmethod.owner.skeeper.database.entities

import android.arch.persistence.room.*

/**
 * Created by Owner on 2017-06-14.
 */

@Entity(tableName = "games")
class Game(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "game_id")
        var id: Int?,
        @ColumnInfo(name = "game_name")
        var name: String?,
        @ColumnInfo(name = "game_rounds")
        var rounds: Int?) {

    constructor() : this(id = 0, name = "", rounds = 0)

}
