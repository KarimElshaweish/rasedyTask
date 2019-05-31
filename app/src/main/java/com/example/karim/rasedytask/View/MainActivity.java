package com.example.karim.rasedytask.View;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.karim.rasedytask.Adapter.rvAdapter;
import com.example.karim.rasedytask.DataBase.AdsDB;
import com.example.karim.rasedytask.Model.AdsData;
import com.example.karim.rasedytask.R;
import com.example.karim.rasedytask.ViewModel.AdsViewModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
public class MainActivity extends AppCompatActivity {

   public RecyclerView rv;
    rvAdapter adapter;
    List<AdsData>list;
    TextView titleText;
    //init the all tools of the view
    private void __init__(){
        titleText=findViewById(R.id.rasedy);
        Typeface typeface = ResourcesCompat.getFont(getApplicationContext(), R.font.myfont);
        titleText.setTypeface(typeface);
        rv=findViewById(R.id.rv);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        rv.setLayoutManager(mLayoutManager);
                AdsViewModel adsViewModel =ViewModelProviders.of(this).get(AdsViewModel.class);

        adsViewModel.getAds().observe(this, new Observer<List<AdsData>>() {
            @Override
            public void onChanged(@Nullable List<AdsData> adsData) {
                list=adsData;
            }

        });
        loadData();
    }
    //get the data from the api
    private void loadData(){
       AdsViewModel adsViewModel=ViewModelProviders.of(this).get(AdsViewModel.class);
        adsViewModel.getAllAdas().observe( this, new Observer<List<AdsDB>>() {
            @Override
            public void onChanged(@Nullable List<AdsDB> adsDBS) {
                List<AdsData>list=new ArrayList<>();
                for(int i=0;i<adsDBS.size();i++){
                    AdsData d=new AdsData();
                    d.setOrder(adsDBS.get(i).getOrder());
                    d.setPicture(adsDBS.get(i).getPicture());
                    d.setTitle(adsDBS.get(i).getTitle());
                    d.setUrl(adsDBS.get(i).getUrl());
                    list.add(d);
                }
                list=AccesndingSortData(list);
                adapter=new rvAdapter(MainActivity.this,list);
                rv.setAdapter(adapter);
            }
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        __init__();





    }
    private List<AdsData> AccesndingSortData(List<AdsData>list){
        Collections.sort(list, new Comparator<AdsData>() {
            @Override
            public int compare(AdsData o1, AdsData o2) {
                return Integer.valueOf(o1.getOrder())>Integer.valueOf(o2.getOrder()) ?-1 :
                        Integer.valueOf(o1.getOrder())<Integer.valueOf(o2.getOrder())?1:0;
            }
        });
        return list;
    }
    private List<AdsData>DescendingSortData(List<AdsData>list){
        Collections.sort(list, new Comparator<AdsData>() {
            @Override
            public int compare(AdsData o1, AdsData o2) {
                return Integer.valueOf(o1.getOrder())<Integer.valueOf(o2.getOrder()) ?-1 :
                        Integer.valueOf(o1.getOrder())>Integer.valueOf(o2.getOrder())?1:0;
            }
        });
        return list;
    }
}
