package com.CarlosJimenez.SlotsApp;

import java.util.ArrayList;
import java.util.List;

public class Category {
    public long id;
    public int iconRes;
    public String category;

    public Category(long id, int iconRes, String category){
        super();
        this.id = id;
        this.iconRes = iconRes;
        this.category = category;
    }

    public static List<Category> generateCategoryList(){
        List<Category> categories = new ArrayList<>();
        String[] programming = {"5", "25", "50", "100", "500", "1000"};

        for(int i = 0; i < programming.length; i++){
            categories.add(new Category(i, R.drawable.money_symbol, programming[i]));
        }
        return categories;
    }
}
