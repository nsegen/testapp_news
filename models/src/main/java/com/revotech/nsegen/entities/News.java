package com.revotech.nsegen.entities;

/**
 * Created by Revotech on 05.07.16.
 */

import java.io.Serializable;
import java.util.Date;

public class News implements Serializable {


    private Integer id;
    private String title;
    private String author;
    private String content;
    private String imgUrl;
    private Date date;

    public News(Integer id, String title, String nickName, String content, String imgUrl, Date date) {
        this.id = id;
        this.title = title;
        this.author = nickName;
        this.content = content;
        this.imgUrl = imgUrl;
        this.date = date;
    }

    public News(String title, String nickName, String content, String imgUrl, Date date) {
        this.id = 0;
        this.title = title;
        this.author = nickName;
        this.content = content;
        this.imgUrl = imgUrl;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public Date getDate() {
        return date;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof News)) return false;

        News news = (News) o;

        return getId() == news.getId() && getTitle().equals(news.getTitle()) && getAuthor().equals(news.getAuthor()) && getContent().equals(news.getContent()) && getImgUrl().equals(news.getImgUrl()) && getDate().equals(news.getDate());

    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + title.hashCode();
        result = 31 * result + author.hashCode();
        result = 31 * result + content.hashCode();
        result = 31 * result + imgUrl.hashCode();
        result = 31 * result + date.hashCode();
        return result;
    }
}
