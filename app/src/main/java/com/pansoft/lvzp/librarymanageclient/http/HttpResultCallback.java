package com.pansoft.lvzp.librarymanageclient.http;

/**
 * 作者：吕振鹏
 * E-mail:lvzhenpeng@pansoft.com
 * 创建时间：2018年05月20日
 * 时间：22:04
 * 版本：v1.0.0
 * 类描述：
 * 修改时间：
 */
public interface HttpResultCallback {

    void onSuccess(Object data);

    void onError(String msg);

}
