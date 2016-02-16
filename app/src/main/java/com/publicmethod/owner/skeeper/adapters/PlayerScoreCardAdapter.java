package com.publicmethod.owner.skeeper.adapters;

import android.support.v7.widget.RecyclerView;
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

    private TextView mPlayerName;
    private EditText mPlayerScore;
    private Button mMinusButton, mAddButton;

    public PlayerScoreCardAdapter(Player[] players) {
        mPlayers = players;

    }

    @Override
    public PlayerScoreCardAdapter.ScoreViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(PlayerScoreCardAdapter.ScoreViewHolder holder, int position) {

        holder.bindPlayers(mPlayers[position]);

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ScoreViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

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
            mPlayerScore.setText(player.getScore());
        }

        @Override
        public void onClick(View v) {

//            TODO: Add switch case statement to handle button clicks.

        }
    }
}
