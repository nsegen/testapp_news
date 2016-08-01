package com.revotech.nsegen.entities;

/**
 * Created by Revotech on 05.07.16.
 */

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class News implements Serializable {


    private Integer id;
    private String title;
    private String author;
    private String content;
    private String imgUrl;
    private LocalDate date;

    public News(){
        this.id = null;
        this.title = null;
        this.author = null;
        this.content = null;
        this.imgUrl = null;
        this.date = null;
    }

    public Integer getId() {
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

    public LocalDate getDate() {
        return date;
    }

    public Date getUtilDate() {
        Instant instant = Instant.from(this.date.atStartOfDay(ZoneId.of("GMT")));
        Date date = Date.from(instant);
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

    public void setDate(LocalDate date) {
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

    @Override
    public String toString() {
        return "News{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", content='" + content + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", date=" + date +
                '}';
    }
}
