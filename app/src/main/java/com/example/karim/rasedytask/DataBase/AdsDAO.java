package com.example.karim.rasedytask.DataBase;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;


@Dao
public interface AdsDAO {
    //select data from database
    @Query("SELECT * FROM AdsDB")
    LiveData<List<AdsDB>> getAds();
// delete data from database
    @Query("DELETE FROM AdsDB")
    void deleteTable();

    //insert data to database
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAds(AdsDB db);
}
