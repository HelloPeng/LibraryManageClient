package com.pansoft.lvzp.librarymanageclient.bean;

/**
 * 作者：吕振鹏
 * E-mail:lvzhenpeng@pansoft.com
 * 创建时间：2018年05月09日
 * 时间：11:46
 * 版本：v1.0.0
 * 类描述：
 * 修改时间：
 */
public class BookListItemBean {

    private String imgUrl;
    private String name;
    private String publishHouse;//出版社
    private String publishTime;//出版时间

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPublishHouse() {
        return publishHouse;
    }

    public void setPublishHouse(String publishHouse) {
        this.publishHouse = publishHouse;
    }

    public String getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }
}
