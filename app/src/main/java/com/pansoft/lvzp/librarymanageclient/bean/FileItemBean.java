package com.pansoft.lvzp.librarymanageclient.bean;

/**
 * 作者：吕振鹏
 * E-mail:lvzhenpeng@pansoft.com
 * 创建时间：2018年04月23日
 * 时间：13:54
 * 版本：v1.0.0
 * 类描述：
 * 修改时间：
 */
public class FileItemBean {

    private String name;//文件的名称
    private String parentPath;//文件所在的文件夹
    private String size;//文件大小
    private String path;//文件的绝对路径

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentPath() {
        return parentPath;
    }

    public void setParentPath(String parentPath) {
        this.parentPath = parentPath;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
