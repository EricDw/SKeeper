package com.publicmethod.owner.skeeper.ui.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

import com.publicmethod.owner.skeeper.R
import com.publicmethod.owner.skeeper.database.entities.Player
import com.publicmethod.owner.skeeper.ui.handlers.ClickHandler
import com.publicmethod.owner.skeeper.ui.viewholders.ViewHolder

/**
 * Created by Eric on 2016-02-16.
 */
class ItemAdapter(private val itemList: MutableList<Player>, val clickHandler: ClickHandler) : RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.player_list_item, parent, false)

        return ViewHolder(view, clickHandler)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bind(itemList[position])

    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}
