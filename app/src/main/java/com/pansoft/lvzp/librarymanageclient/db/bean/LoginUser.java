package com.pansoft.lvzp.librarymanageclient.db.bean;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by lv_zhp on 2018/4/29.
 */
@Entity(tableName = "p_login")
public class LoginUser {

    @PrimaryKey(autoGenerate = true)
    private long oid;
    private String username;
    private String password;

    public long getOid() {
        return oid;
    }

    public void setOid(long oid) {
        this.oid = oid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginUser{" +
                "oid=" + oid +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
