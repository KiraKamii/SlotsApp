package com.CarlosJimenez.SlotsApp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class CategoryDropdownMenu extends PopupWindow {
    private Context context;
    private RecyclerView rvCategory;
    private CategoryDropdownAdapter dropdownAdapter;

    public CategoryDropdownMenu(Context context){
        super(context);
        this.context = context;
        setupView();
    }

    public void setCategorySelectedListener(CategoryDropdownAdapter.CategorySelectedListener categorySelectedListener) {
        dropdownAdapter.setCategorySelectedListener(categorySelectedListener);
    }

    private void setupView() {
        View view = LayoutInflater.from(context).inflate(R.layout.popout_category, null);

        rvCategory = view.findViewById(R.id.rvCategory);
        rvCategory.setHasFixedSize(true);
        rvCategory.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        rvCategory.addItemDecoration(new DividerItemDecoration(context, LinearLayoutManager.VERTICAL));

        dropdownAdapter = new CategoryDropdownAdapter(Category.generateCategoryList());
        rvCategory.setAdapter(dropdownAdapter);

        setContentView(view);
    }
}
