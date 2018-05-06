package com.pansoft.lvzp.librarymanageclient.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.pansoft.lvzp.librarymanageclient.db.bean.LoginUser;

import java.util.List;

/**
 * Created by lv_zhp on 2018/4/29.
 */
@Dao
public interface LoginDao {

    @Query("SELECT * FROM P_LOGIN")
    List<LoginUser> getLoginUserList();

    @Query("SELECT * FROM P_LOGIN WHERE oid IN (:oid)")
    LoginUser getLoginUser(String oid);

    @Insert
    void saveLoginUsers(LoginUser... users);

    @Update
    void udpateLoginUsers(LoginUser... user);

    @Delete
    void deleteLoginUsers(LoginUser... user);
}
