package com.pansoft.lvzp.librarymanageclient.http;

/**
 * Created by lv_zhp on 2018/4/1.
 */

public interface ApiUrl {
    //用于验证ip是否可正常使用的接口
    String BASE_CHECK = "/api/base/check";
    //登录的接口
    String LOGIN = "/api/login";
    //添加学生
    String ADD_STUDENT = "/api/student/add";
    //添加图书
    String ADD_BOOK = "/api/book/add";
    String SEARCH_DATA = "/api/search";
}
