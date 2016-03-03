package com.publicmethod.owner.skeeper.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.publicmethod.owner.skeeper.R;
import com.publicmethod.owner.skeeper.model.Player;

/**
 * Created by Owner on 2016-02-16.
 */
public class PlayerScoreCardAdapter extends RecyclerView.Adapter<PlayerScoreCardAdapter.ScoreViewHolder> {

    private Player[] mPlayers;

    public PlayerScoreCardAdapter(Player[] players) {
        mPlayers = players;

    }

    @Override
    public ScoreViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.player_score_list_item, parent, false);

        return new ScoreViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ScoreViewHolder holder, int position) {

        holder.bindPlayers(mPlayers[position]);

    }

    @Override
    public int getItemCount() {
        return mPlayers.length;
    }

    public class ScoreViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        public TextView mPlayerName;
        public EditText mPlayerScore;
        public Button mMinusButton, mAddButton;

        public ScoreViewHolder(View itemView) {
            super(itemView);

            mPlayerName = (TextView) itemView.findViewById(R.id.textView_player_name);
            mPlayerScore = (EditText) itemView.findViewById(R.id.editText_player_score);
            mMinusButton = (Button) itemView.findViewById(R.id.button_minus);
            mAddButton = (Button) itemView.findViewById(R.id.button_add);

            mMinusButton.setOnClickListener(this);
            mAddButton.setOnClickListener(this);


        }

        /**
         * Binds the data from the player to the appropriate views.
         *
         * @param player The player data to be bound.
         */
        public void bindPlayers(Player player) {

            mPlayerName.setText(player.getName());
            mPlayerScore.setText(String.valueOf(player.getScore()));
        }

        @Override
        public void onClick(View v) {

//            TODO: Add switch case statement to handle button clicks.

        }

        @Override
        public boolean onLongClick(View v) {
//            TODO: Remove player from the array and notify data set.

            return false;
        }
    }
}
