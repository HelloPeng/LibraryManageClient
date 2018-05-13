package com.pansoft.lvzp.librarymanageclient.bean;

/**
 * 作者：吕振鹏
 * E-mail:lvzhenpeng@pansoft.com
 * 创建时间：2018年05月13日
 * 时间：19:49
 * 版本：v1.0.0
 * 类描述：
 * 修改时间：
 */
public class SearchStudentItemBean {

    private String name;//姓名
    private String college;//学院
    private String lastBorrowTime;//最后借阅时间
    private String avatar;//学生头像

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getLastBorrowTime() {
        return lastBorrowTime;
    }

    public void setLastBorrowTime(String lastBorrowTime) {
        this.lastBorrowTime = lastBorrowTime;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
