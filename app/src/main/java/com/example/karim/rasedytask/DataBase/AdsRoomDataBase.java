package com.example.karim.rasedytask.DataBase;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;

@Database(entities = {AdsDB.class},version = 1)
public abstract class AdsRoomDataBase extends RoomDatabase {
    public abstract AdsDAO adsDAO();
    private static AdsRoomDataBase INSTANCE;
    static AdsRoomDataBase getDatabase(final Context _ctx){
        if(INSTANCE==null){
            synchronized (AdsDB.class){
                if(INSTANCE==null){
                    INSTANCE= Room.databaseBuilder(_ctx.getApplicationContext(),
                            AdsRoomDataBase.class,"ads")
                            .fallbackToDestructiveMigration()
                            .addCallback(nRCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }
    private static RoomDatabase.Callback nRCallback=new Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
        }
    };
    @Override
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration config) {
        return null;
    }

    @Override
    protected InvalidationTracker createInvalidationTracker() {
        return null;
    }
}
