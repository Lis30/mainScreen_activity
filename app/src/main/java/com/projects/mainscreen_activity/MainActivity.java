package com.projects.mainscreen_activity;

import android.app.FragmentManager;
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

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import okhttp3.ResponseBody;
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
//        HSInit();
//        BSInit();
    }

public void CatInit(){
    List<MSCategories> catList = new ArrayList<>();
    catList.add(new MSCategories(1, "Phones", "ic_phone", "z_white_rounded", false));
    catList.add(new MSCategories(2, "Computer", "ic_computer", "z_white_rounded", false));
    catList.add(new MSCategories(3, "Health", "ic_health", "z_white_rounded", false));
    catList.add(new MSCategories(4, "Books", "ic_books", "z_white_rounded", false));
    catList.add(new MSCategories(5, "Food", "ic_food", "z_white_rounded", false));

    SetCatSettings (catList);
    }

 public void SetCatSettings(List<MSCategories> catList){
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);

        CatRecycler = findViewById(R.id.categories);
        CatRecycler.setLayoutManager(layoutManager);
        CatAdapter = new MSCatAdapter(this, catList);
        CatRecycler.setAdapter(CatAdapter);

    }


public void GetData(){

    List<HotSales> hotSales = new ArrayList<>();
    List<BestSellers> bestSellers = new ArrayList<>();

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

                List<HotSales> hotSales  = new ArrayList<>(Arrays.asList(MSUnite.getHome_store()));
                List<BestSellers> bestSellers = new ArrayList<>(Arrays.asList(MSUnite.getBest_seller()));

            }

        }

        @Override
        public void onFailure(Call<MainScreenUnite> call, Throwable t) {

            hotSales.add(new HotSales(1, true, "Iphone 12", "Súper. Mega. Rápido.",
             "https://img.ibxk.com.br/2020/09/23/23104013057475.jpg",  true));
            bestSellers.add(new BestSellers(111, true, "Samsung Galaxy s20 Ultra", "1047",
                "1500","https://shop.gadgetufa.ru/images/upload/52534-smartfon-samsung-galaxy-s20-ultra-12-128-chernyj_1024.jpg"));


        }
    });

    SetHSSettings(hotSales);
    SetBSSettings(bestSellers);

}


public void SetHSSettings(List<HotSales> hotSales){
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL,false);
        HSRecycler = findViewById(R.id.commercial);
        HSRecycler.setLayoutManager(layoutManager);
        HS_Adapter = new HSAdapter(this, hotSales);
        HSRecycler.setAdapter(HS_Adapter);
}

//public void HSInit(){
//        List<HotSales> hotSales = new ArrayList<>();
//
//        hotSales.add(new HotSales(1, true, "Iphone 12", "Súper. Mega. Rápido.",
//                "https://img.ibxk.com.br/2020/09/23/23104013057475.jpg",  true));
//        hotSales.add(new HotSales(2,"Samsung Galaxy A71", "Súper. Mega. Rápido.",
//                "https://clck.ru/eoude", true));
//        hotSales.add(new HotSales(3, "Xiaomi Mi 11 ultra", "Súper. Mega. Rápido.",
//                "https://static.digit.in/default/942998b8b4d3554a6259aeb1a0124384dbe0d4d5.jpeg", true));
//
//
//        SetHSSettings(hotSales);
//}

public void SetBSSettings(List<BestSellers> bestSellers){
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        BSRecycler = findViewById(R.id.catalog);
        BSRecycler.setLayoutManager(layoutManager);
        BS_Adapter = new BSAdapter(this, bestSellers);
        BSRecycler.setAdapter(BS_Adapter);

        }

//public void BSInit(){
//        List<BestSellers> bestSellers = new ArrayList<>();
//
//        bestSellers.add(new BestSellers(111, true, "Samsung Galaxy s20 Ultra", "1047",
//                "1500","https://shop.gadgetufa.ru/images/upload/52534-smartfon-samsung-galaxy-s20-ultra-12-128-chernyj_1024.jpg"));
//    bestSellers.add(new BestSellers(222, true, "Xiaomi Mi 10 Pro", "300",
//            "400","https://mi92.ru/wp-content/uploads/2020/03/smartfon-xiaomi-mi-10-pro-12-256gb-global-version-starry-blue-sinij-1.jpg"));
//    bestSellers.add(new BestSellers(3333, true, "Samsung Note 20 Ultra", "1047",
//            "1500","https://opt-1739925.ssl.1c-bitrix-cdn.ru/upload/iblock/c01/c014d088c28d45b606ed8c58e5817172.jpg?160405904823488"));
//    bestSellers.add(new BestSellers(4444, true, "Motorola One Edge", "300",
//            "400","https://www.benchmark.rs/assets/img/news/edge1.jpg"));
//
//    SetBSSettings(bestSellers);
//
//
//}

//public void hideSearch(){
//
//    FragmentManager fm = getFragmentManager();
//    fm.beginTransaction()
//            .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
//            .show(somefrag)
//            .commit();
//
//}

}