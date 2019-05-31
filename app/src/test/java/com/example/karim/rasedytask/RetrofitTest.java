package com.example.karim.rasedytask;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;

import com.example.karim.rasedytask.Adapter.rvAdapter;
import com.example.karim.rasedytask.DataBase.AdsDB;
import com.example.karim.rasedytask.Model.AdsData;
import com.example.karim.rasedytask.Network.RetrofitAPI;
import com.example.karim.rasedytask.Root.App;
import com.example.karim.rasedytask.View.MainActivity;
import com.example.karim.rasedytask.ViewModel.AdsViewModel;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.util.ActivityController;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Callback;
import retrofit2.Retrofit;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;
import static org.junit.matchers.JUnitMatchers.*;

@Config(constants = BuildConfig.class,sdk = 18)
@RunWith(RobolectricGradleTestRunner.class)
public class RetrofitTest  {
// get the man activity refernace
    private MainActivity mainActivity;

    //refer to the retrofit api
    @Mock
    private RetrofitAPI mockRetrofitApiImpl;

    @Captor
    private ArgumentCaptor<Callback<List<AdsData>>> callbackArgumentCaptor;
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        ActivityController<MainActivity> controller = Robolectric.buildActivity(MainActivity.class);
        mainActivity = controller.get();

        // Then we need to swap the retrofit api impl. with a mock one
        // I usually store my Retrofit api impl as a static singleton in class RestClient, hence:
        App app=new App();
        Retrofit retrofit=app.getRetrofitAPI();
        controller.create();
    }
    @Test
    public void shouldFillAdapter() throws Exception {
        int cnt=loadData();
        int Quntity=10;
        assertThat(cnt, equalTo(Quntity));
    }
    int cnt=0;
    //get the the data from api and test it compare between 10 and the list count
    private int loadData(){
        AdsViewModel adsViewModel=ViewModelProviders.of(mainActivity).get(AdsViewModel.class);
        adsViewModel.getAllAdas().observe( mainActivity, new Observer<List<AdsDB>>() {
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
           cnt=list.size();
            }
        });
        return cnt;
    }
}
