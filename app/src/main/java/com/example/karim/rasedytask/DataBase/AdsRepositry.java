package com.example.karim.rasedytask.DataBase;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

import io.reactivex.Flowable;

public class AdsRepositry {
    private AdsDAO mAdsDAO;
    private LiveData<List<AdsDB>> mAllAds;


    public AdsRepositry(Application application){
        AdsRoomDataBase db=AdsRoomDataBase.getDatabase(application);
        mAdsDAO=db.adsDAO();
        mAllAds=mAdsDAO.getAds();

    }
    public void deleteTable(){mAdsDAO.deleteTable();}
    public LiveData<List<AdsDB>>getmAllAds(){return mAllAds;}
    public void insert(AdsDB db){
        new insertAsyncTask(mAdsDAO).execute(db);
    }
    //insert asyncTask to database
    private static class insertAsyncTask extends AsyncTask<AdsDB,Void,Void>{

        private AdsDAO mAsyncTaskDao;
        insertAsyncTask(AdsDAO dao){mAsyncTaskDao=dao;}
        @Override
        protected Void doInBackground(AdsDB... adsDBS) {
            mAsyncTaskDao.insertAds(adsDBS[0]);
            return null;
        }
    }


}
