package com.projects.mainscreen_activity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.projects.mainscreen_activity.model.BestSellers;
import com.projects.mainscreen_activity.model.HotSales;


public class MainScreenUnite {

    @SerializedName("home_store")
    @Expose
    HotSales[] home_store;

    @SerializedName("best_seller")
    @Expose
    BestSellers[] best_seller;


    public HotSales[] getHome_store() {
        return home_store;
    }

    public void setHome_store(HotSales[] home_store) {
        this.home_store = home_store;
    }

    public BestSellers[] getBest_seller() {
        return best_seller;
    }

    public void setBest_seller(BestSellers[] best_seller) {
        this.best_seller = best_seller;
    }


}
