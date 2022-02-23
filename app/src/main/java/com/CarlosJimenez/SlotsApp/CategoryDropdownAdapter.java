package com.CarlosJimenez.SlotsApp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CategoryDropdownAdapter extends RecyclerView.Adapter<CategoryDropdownAdapter.CategoryViewHolder> {

    private List<Category> categories;
    private CategorySelectedListener categorySelectedListener;

    public CategoryDropdownAdapter(List<Category> categories){
        super();
        this.categories = categories;
    }


    public void setCategorySelectedListener(CategoryDropdownAdapter.CategorySelectedListener categorySelectedListener) {
        this.categorySelectedListener = categorySelectedListener;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CategoryViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, final int position) {
        final Category category = categories.get(position);
        holder.ivIcon.setImageResource(category.iconRes);
        holder.tvCategory.setText(category.category);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(categorySelectedListener != null){
                    categorySelectedListener.onCategorySelected(position, category);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    static class CategoryViewHolder extends RecyclerView.ViewHolder{
        AppCompatTextView tvCategory;
        AppCompatImageView ivIcon;

        public CategoryViewHolder(View itemView) {
            super(itemView);
            tvCategory = itemView.findViewById(R.id.tvCategory);
            ivIcon = itemView.findViewById(R.id.ivIcon);
        }
    }

    interface CategorySelectedListener {
        void onCategorySelected(int position, Category category);
    }
}
