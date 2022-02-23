package com.CarlosJimenez.SlotsApp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class SetBetDropdownMenu extends PopupWindow {
    private Context context;
    private RecyclerView rvCategory;
    private SetBetDropdownAdapter dropdownAdapter;

    public SetBetDropdownMenu(Context context){
        super(context);
        this.context = context;
        setupView();
    }

    public void setBetSelectedListener(SetBetDropdownAdapter.BetSelectedListener betSelectedListener) {
        dropdownAdapter.setBetSelectedListener(betSelectedListener);
    }

    private void setupView() {
        View view = LayoutInflater.from(context).inflate(R.layout.popout_category, null);

        rvCategory = view.findViewById(R.id.rvBets);
        rvCategory.setHasFixedSize(true);
        rvCategory.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        rvCategory.addItemDecoration(new DividerItemDecoration(context, LinearLayoutManager.VERTICAL));

        dropdownAdapter = new SetBetDropdownAdapter(SetBet.generateBetsList());
        rvCategory.setAdapter(dropdownAdapter);

        setContentView(view);
    }
}
