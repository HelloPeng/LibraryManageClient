package com.pansoft.lvzp.librarymanageclient.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.pansoft.lvzp.librarymanageclient.dao.LoginDao;
import com.pansoft.lvzp.librarymanageclient.db.bean.LoginUser;

/**
 * Created by lv_zhp on 2018/4/29.
 */
@Database(entities = {LoginUser.class}, version = 1,exportSchema = false)
public abstract class SqlDatabase extends RoomDatabase {

    private static SqlDatabase sInstance;

    public abstract LoginDao getLoginDao();

    public static SqlDatabase getInstance(Context context) {
        if (sInstance == null) {
            sInstance = Room.databaseBuilder(context, SqlDatabase.class, "library.db")
                    .allowMainThreadQueries()//允许在主线程执行
                    .build();
        }
        return sInstance;
    }

}
