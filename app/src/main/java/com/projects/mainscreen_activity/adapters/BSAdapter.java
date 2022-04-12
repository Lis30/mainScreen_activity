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
import com.projects.mainscreen_activity.model.BestSellers;
import com.projects.mainscreen_activity.model.HotSales;

import java.util.List;

public class BSAdapter extends RecyclerView.Adapter<BSAdapter.BSViewHolder>{

    Context context;
    List<BestSellers> bestSellers;

    public BSAdapter(Context context, List<BestSellers> bestSellers) {
        this.context = context;
        this.bestSellers = bestSellers;
    }

    @NonNull
    @Override
    public BSViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View BSItem = LayoutInflater.from(context).inflate(R.layout.viewholder_catalog, parent, false);

        return new BSAdapter.BSViewHolder(BSItem);
    }

    @Override
    public void onBindViewHolder(@NonNull BSViewHolder holder, int position) {

        holder.title.setText(bestSellers.get(position).getTitle());
        holder.price_without_discount.setText(bestSellers.get(position).getPrice_without_discount());
        holder.discount_price.setText(bestSellers.get(position).getDiscount_price());
        Glide.with(context).load(bestSellers.get(position).getPicture()).into(holder.picture);

    }

    @Override
    public int getItemCount() {
        return bestSellers.size();
    }

    public static class BSViewHolder extends RecyclerView.ViewHolder{

        TextView title;
        TextView price_without_discount;
        TextView discount_price;
        ImageView picture;


        public BSViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.CatalogName);
            price_without_discount = itemView.findViewById(R.id.oldPrice);
            discount_price = itemView.findViewById(R.id.totalPrice);
            picture = itemView.findViewById(R.id.pic);
        }
    }

}
