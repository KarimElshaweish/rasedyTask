package com.example.karim.rasedytask.Root;

import com.example.karim.rasedytask.Network.RetrofitAPI;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

// provide the data of the url of retrofit
@Module
public class AppModule {
  @Provides
  Retrofit getRetrofitProivder(){
    return new Retrofit.Builder()
            .baseUrl(RetrofitAPI.BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build();
  }
}
