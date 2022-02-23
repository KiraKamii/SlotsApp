package com.CarlosJimenez.SlotsApp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SetBetDropdownAdapter extends RecyclerView.Adapter<SetBetDropdownAdapter.BetViewHolder> {

    private List<SetBet> bets;
    private BetSelectedListener betSelectedListener;

    public SetBetDropdownAdapter(List<SetBet> categories){
        super();
        this.bets = categories;
    }


    public void setBetSelectedListener(BetSelectedListener betSelectedListener) {
        this.betSelectedListener = betSelectedListener;
    }

    @NonNull
    @Override
    public BetViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BetViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BetViewHolder holder, final int position) {
        final SetBet bet = bets.get(position);
        holder.ivIcon.setImageResource(bet.iconRes);
        holder.tvCategory.setText(bet.bet);

        holder.itemView.setOnClickListener(view -> {
            if(betSelectedListener != null){
                betSelectedListener.onBetSelected(position, bet);
            }
        });
    }

    @Override
    public int getItemCount() {
        return bets.size();
    }

    static class BetViewHolder extends RecyclerView.ViewHolder{
        AppCompatTextView tvCategory;
        AppCompatImageView ivIcon;

        public BetViewHolder(View itemView) {
            super(itemView);
            tvCategory = itemView.findViewById(R.id.tvCategory);
            ivIcon = itemView.findViewById(R.id.ivIcon);
        }
    }

    interface BetSelectedListener {
        void onBetSelected(int position, SetBet setBet);
    }
}
