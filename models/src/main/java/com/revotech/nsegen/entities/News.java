package com.revotech.nsegen.entities;

/**
 * Created by Revotech on 05.07.16.
 */

import java.io.Serializable;
import java.util.Date;

public class News implements Serializable {
    private int id;
    private String title;
    private String author;
    private String content;
    private String imgUrl;
    private Date date;

    public News(int id, String title, String nickName, String content, String imgUrl, Date date) {
        this.id = id;
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

}
