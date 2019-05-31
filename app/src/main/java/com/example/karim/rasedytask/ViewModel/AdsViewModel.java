package com.example.karim.rasedytask.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Handler;
import android.util.Log;

import com.example.karim.rasedytask.DataBase.AdsDB;
import com.example.karim.rasedytask.DataBase.AdsRepositry;
import com.example.karim.rasedytask.Model.AdsData;
import com.example.karim.rasedytask.Network.RetrofitAPI;
import com.example.karim.rasedytask.Root.App;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observer;
import io.reactivex.Scheduler;
public class AdsViewModel extends AndroidViewModel {
    private AdsRepositry mAdsRepositry;
    private LiveData<List<AdsDB>>allAdas;
    //init the room
    public AdsViewModel(Application application) {
        super(application);
        mAdsRepositry=new AdsRepositry(application);

        allAdas=mAdsRepositry.getmAllAds();

    }
    //get all the data
   public LiveData<List<AdsDB>>getAllAdas(){
        return allAdas;
    }
    public void deleteTable(){mAdsRepositry.deleteTable();}
    public void insert(AdsDB db){mAdsRepositry.insert(db);}
    CompositeDisposable compositeDisposable=new CompositeDisposable();
    private MutableLiveData<List<AdsData>>adsList;
    public LiveData<List<AdsData>>getAds(){
        if(adsList==null){
            adsList=new MutableLiveData<>();
            loadAdsData();

        }
        return adsList;
    }

    private void loadAdsData() {
        App app=getApplication();
        Retrofit retrofit= app.getRetrofitAPI();

        RetrofitAPI retrofitAPI=retrofit.create(RetrofitAPI.class);
        compositeDisposable.add(retrofitAPI.getAds()
        .subscribeOn(Schedulers.io())
        .subscribeWith(new DisposableObserver<List<AdsData>>(){
                           @Override
                           public void onNext(List<AdsData> list) {
                               deleteTable();
                               for (int i=0;i<list.size();i++){
                    String picture=list.get(i).getPicture();
                    String title=list.get(i).getTitle();
                    String order=list.get(i).getOrder();
                    String url=list.get(i).getUrl();
                    AdsDB db=new AdsDB(picture,title,url,order);
                    insert(db);
                }

                           }

                           @Override
                           public void onError(Throwable e) {

                           }

                           @Override
                           public void onComplete() {

                           }
                       }
        ));


    }

}

