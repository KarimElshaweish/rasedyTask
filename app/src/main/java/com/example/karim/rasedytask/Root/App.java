package com.example.karim.rasedytask.Root;

import android.app.Application;

import retrofit2.Retrofit;

// extents from application to setup the dagger2
public class App extends Application {
    AppComponent appComponent;

    public AppComponent getAppComponent() {
        return appComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent=DaggerAppComponent.builder()
                .appModule(new AppModule())
                .build();
    }
    // get the retrofit api
    public Retrofit getRetrofitAPI(){
        return appComponent.getRetrofitApi();
    }
}
