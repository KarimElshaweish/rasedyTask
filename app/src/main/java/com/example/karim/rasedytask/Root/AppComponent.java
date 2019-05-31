package com.example.karim.rasedytask.Root;

import dagger.Component;
import retrofit2.Retrofit;

@Component(modules = AppModule.class)
public interface AppComponent {
    Retrofit getRetrofitApi();
}
