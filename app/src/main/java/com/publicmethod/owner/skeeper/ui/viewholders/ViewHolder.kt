package com.publicmethod.owner.skeeper.ui.viewholders

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toolbar
import com.publicmethod.owner.skeeper.R
import com.publicmethod.owner.skeeper.database.entities.Player
import com.publicmethod.owner.skeeper.ui.handlers.ClickHandler

/**
 * Created by Owner on 2017-06-14.
 */
class ViewHolder(itemView: View, clickHandler: ClickHandler) : RecyclerView.ViewHolder(itemView) {

    var cardView: CardView
    var toolbar: Toolbar
    var playerScore: EditText
    var minusButton: Button
    var addButton: Button
    var clickHandler: ClickHandler


    init {

        this.clickHandler = clickHandler
        cardView = itemView.findViewById(R.id.cardView) as CardView
        toolbar = itemView.findViewById(R.id.list_item_toolbar) as Toolbar
        playerScore = itemView.findViewById(R.id.editText_player_score) as EditText
        minusButton = itemView.findViewById(R.id.button_minus) as Button
        addButton = itemView.findViewById(R.id.button_add) as Button

    }

    /**
     * Binds the data from the player to the appropriate views.

     * @param player The player data to be bound.
     */
    fun bind(player: Player) {

//        toolbar.setTitle(player.name)
//        playerScore.setText(player.score.toString())

    }

}