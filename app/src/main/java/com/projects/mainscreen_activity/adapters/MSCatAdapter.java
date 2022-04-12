package com.projects.mainscreen_activity.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.projects.mainscreen_activity.R;
import com.projects.mainscreen_activity.model.MSCategories;

import java.util.List;

public class MSCatAdapter extends RecyclerView.Adapter<MSCatAdapter.CategoriesViewHolder> {

    Context context;
    List<MSCategories> msCategories;

    public MSCatAdapter(Context context, List<MSCategories> msCategories) {
        this.context = context;
        this.msCategories = msCategories;
    }

    @NonNull
    @Override
    public CategoriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View CatItem = LayoutInflater.from(context).inflate(R.layout.viewholder_category, parent, false);
        return new CategoriesViewHolder(CatItem);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoriesViewHolder holder, int position) {

        holder.categoryName.setText(msCategories.get(position).getName());
        holder.categoryBack.setTag(msCategories.get(position).getColor()); // if msCategories.get(position).isPicket =>
        // set categoryBack "z_orange_rounded" && TextColor == orange + отрисовка в Glide

        String Picture = "";

        //если не задать возможность отображения иконок, то будет меняться только название

        for (int i = 0; i < msCategories.size(); i++) {
            Picture = msCategories.get(position).getPicture();
            int ic_res = holder.itemView.getContext().getResources().getIdentifier(Picture,
                    "drawable", holder.itemView.getContext().getPackageName());
            Glide.with(holder.itemView.getContext()).load(ic_res).into(holder.categoryPic);
        }

    }

    @Override
    public int getItemCount() {
        return msCategories.size();
    }

    public static class CategoriesViewHolder extends RecyclerView.ViewHolder{

        TextView categoryName;
        ImageView categoryPic;
        ImageView categoryBack;


        public CategoriesViewHolder(@NonNull View itemView) {

            super(itemView);

            categoryName = itemView.findViewById(R.id.categoryName);
            categoryPic = itemView.findViewById(R.id.categoryPic);
            categoryBack = itemView.findViewById(R.id.categoryBack);


        }
    }

}
