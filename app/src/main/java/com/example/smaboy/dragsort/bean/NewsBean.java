package com.example.smaboy.dragsort.bean;

/**
 * 类名: NewsBean
 * 类作用描述: java类作用描述
 * 作者: Smaboy
 * 创建时间: 2018/10/26 14:03
 */
public class NewsBean {

    private String title;
    private String url;
    private String img;

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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public NewsBean(String title, String url, String img) {
        this.title = title;
        this.url = url;
        this.img = img;
    }
}
