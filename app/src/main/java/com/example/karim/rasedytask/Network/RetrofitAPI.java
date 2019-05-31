package com.example.karim.rasedytask.Network;

import com.example.karim.rasedytask.Model.AdsData;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;

// interface fot the retrofit url and func
public interface RetrofitAPI {
     static final String BASE_URL = "https://simswitch.bit68.com";
    @GET("/get_ad/?solo=false")
    Observable<List<AdsData>> getAds();
}
