package com.publicmethod.owner.skeeper.adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.publicmethod.owner.skeeper.R;
import com.publicmethod.owner.skeeper.model.Player;

import java.util.List;

/**
 * Created by Eric on 2016-02-16.
 */
public class PlayerScoreCardAdapter extends RecyclerView.Adapter<PlayerScoreCardAdapter.ScoreViewHolder> {

    private List<Player> mPlayerList;

    public PlayerScoreCardAdapter(List<Player> playerList) {
        mPlayerList = playerList;

    }

    @Override
    public ScoreViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.player_score_list_item, parent, false);

        return new ScoreViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ScoreViewHolder holder, int position) {

        holder.bindPlayers(mPlayerList.get(position));

    }

    @Override
    public int getItemCount() {
        return mPlayerList.size();
    }

    public class ScoreViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        public CardView mCardView;
        public TextView mPlayerName;
        public EditText mPlayerScore;
        public Button mMinusButton, mAddButton;

        public ScoreViewHolder(View itemView) {
            super(itemView);

            mCardView = (CardView) itemView.findViewById(R.id.cardView);
            mPlayerName = (TextView) itemView.findViewById(R.id.textView_player_name);
            mPlayerScore = (EditText) itemView.findViewById(R.id.editText_player_score);
            mMinusButton = (Button) itemView.findViewById(R.id.button_minus);
            mAddButton = (Button) itemView.findViewById(R.id.button_add);

            mMinusButton.setOnClickListener(this);
            mAddButton.setOnClickListener(this);

            mPlayerName.setOnLongClickListener(this);


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

            int id = v.getId();
            int updatedScoreCount = mPlayerList.get(getAdapterPosition()).getScore();

            switch (id) {
                case R.id.button_minus:
                    updatedScoreCount--;
                    if (updatedScoreCount < 0) {
                        Toast.makeText(itemView.getContext(), R.string.toast_text_score_to_low, Toast.LENGTH_LONG).show();
                        return;
                    } else {
                        updatePlayerScore(updatedScoreCount);
                        notifyItemChanged(getAdapterPosition());
                    }
                    break;
                case R.id.button_add:
                    updatedScoreCount++;
                    if (updatedScoreCount > 9999) {
                        Toast.makeText(itemView.getContext(), R.string.toast_text_score_to_high, Toast.LENGTH_LONG).show();
                        return;
                    } else {
                        updatePlayerScore(updatedScoreCount);
                        notifyItemChanged(getAdapterPosition());
                    }
                    break;
                default:
            }

        }

        @Override
        public boolean onLongClick(View v) {
            removePlayer(getAdapterPosition());
            return true;
        }

        public void removePlayer(int positionInArrayList) {
            mPlayerList.get(positionInArrayList).setScore(0);
            mPlayerList.remove(positionInArrayList);
            notifyItemRemoved(positionInArrayList);

        }

        private void updatePlayerScore(int updatedScoreCount) {
            mPlayerList.get(getAdapterPosition()).setScore(updatedScoreCount);
        }
    }
}
