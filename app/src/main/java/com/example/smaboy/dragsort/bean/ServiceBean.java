package com.example.smaboy.dragsort.bean;

/**
 * 类名: ServiceBean
 * 类作用描述: java类作用描述
 * 作者: Smaboy
 * 创建时间: 2018/10/30 16:53
 */
public class ServiceBean {

    /**
     * title : 校园行
     * url : https://qqe2.com
     * imgUrl :
     */


    private String id;
    private Boolean added;
    private String title;
    private String url;
    private String imgUrl;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getAdded() {
        return added;
    }

    public void setAdded(Boolean added) {
        this.added = added;
    }
}
