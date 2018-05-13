package com.pansoft.lvzp.librarymanageclient.bean;

/**
 * 作者：吕振鹏
 * E-mail:lvzhenpeng@pansoft.com
 * 创建时间：2018年05月13日
 * 时间：14:45
 * 版本：v1.0.0
 * 类描述：
 * 修改时间：
 */
public class OverdueStudentItemBean {

    private String name;
    private String time;
    private String actualReturnTime;
    private String book;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getActualReturnTime() {
        return actualReturnTime;
    }

    public void setActualReturnTime(String actualReturnTime) {
        this.actualReturnTime = actualReturnTime;
    }

    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
    }
}
