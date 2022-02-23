package com.CarlosJimenez.SlotsApp;

import java.util.ArrayList;
import java.util.List;

public class SetBet {
    public long id;
    public int iconRes;
    public String bet;

    public SetBet(long id, int iconRes, String category){
        super();
        this.id = id;
        this.iconRes = iconRes;
        this.bet = category;
    }

    public static List<SetBet> generateBetsList(){
        List<SetBet> bets = new ArrayList<>();
        String[] dollarBets = {"5", "25", "50", "100", "500", "1000"};

        for(int i = 0; i < dollarBets.length; i++){
            bets.add(new SetBet(i, R.drawable.money_symbol, dollarBets[i]));
        }
        return bets;
    }
}
