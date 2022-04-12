package com.projects.mainscreen_activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.projects.mainscreen_activity.adapters.BSAdapter;
import com.projects.mainscreen_activity.adapters.HSAdapter;
import com.projects.mainscreen_activity.adapters.MSCatAdapter;
import com.projects.mainscreen_activity.model.BestSellers;
import com.projects.mainscreen_activity.model.HotSales;
import com.projects.mainscreen_activity.model.MSCategories;
import com.projects.mainscreen_activity.network.MainScreenAPI;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    RecyclerView CatRecycler, HSRecycler, BSRecycler;
    MSCatAdapter CatAdapter;
    HSAdapter HS_Adapter;
    BSAdapter BS_Adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CatInit();
        GetData();
    }

    public void CatInit() {
        List<MSCategories> catList = new ArrayList<>();
        catList.add(new MSCategories(1, "Phones", "ic_phone", "z_white_rounded", false));
        catList.add(new MSCategories(2, "Computer", "ic_computer", "z_white_rounded", false));
        catList.add(new MSCategories(3, "Health", "ic_health", "z_white_rounded", false));
        catList.add(new MSCategories(4, "Books", "ic_books", "z_white_rounded", false));
        catList.add(new MSCategories(5, "Food", "ic_food", "z_white_rounded", false));

        SetCatSettings(catList);
    }

    public void SetCatSettings(List<MSCategories> catList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);

        CatRecycler = findViewById(R.id.categories);
        CatRecycler.setLayoutManager(layoutManager);
        CatAdapter = new MSCatAdapter(this, catList);
        CatRecycler.setAdapter(CatAdapter);

    }


    public void GetData(){
         Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://run.mocky.io/v3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

        MainScreenAPI msAPI = retrofit.create(MainScreenAPI.class);

        Call<MainScreenUnite> call = msAPI.getInfo();

        call.enqueue(new Callback<MainScreenUnite>() {
            @Override
            public void onResponse(Call<MainScreenUnite> call, Response<MainScreenUnite> response) {
                if (response.isSuccessful()) {
                    MainScreenUnite MSUnite = response.body();

                    HS_Adapter.addItems(MSUnite.getHome_store());
                    BS_Adapter.addItems(MSUnite.getBest_seller());
                }
            }

            @Override
            public void onFailure(Call<MainScreenUnite> call, Throwable t) {
                List<HotSales> hotSales = new ArrayList<>();
                List<BestSellers> bestSellers = new ArrayList<>();

                hotSales.add(new HotSales(1, true, "Iphone 12", "Súper. Mega. Rápido.",
                        "https://img.ibxk.com.br/2020/09/23/23104013057475.jpg", true));
                bestSellers.add(new BestSellers(111, true, "Samsung Galaxy s20 Ultra", "1047",
                        "1500", "https://shop.gadgetufa.ru/images/upload/52534-smartfon-samsung-galaxy-s20-ultra-12-128-chernyj_1024.jpg"));


                HS_Adapter.addItems(hotSales);
                BS_Adapter.addItems(bestSellers);
            }
        });

        SetHSSettings();
        SetBSSettings();
    }


    public void SetHSSettings() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        HSRecycler = findViewById(R.id.commercial);
        HSRecycler.setLayoutManager(layoutManager);
        HS_Adapter = new HSAdapter(this);
        HSRecycler.setAdapter(HS_Adapter);
    }

    public void SetBSSettings() {
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        BSRecycler = findViewById(R.id.catalog);
        BSRecycler.setLayoutManager(layoutManager);
        BS_Adapter = new BSAdapter(this);
        BSRecycler.setAdapter(BS_Adapter);
    }
}