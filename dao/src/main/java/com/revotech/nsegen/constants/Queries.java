package com.revotech.nsegen.constants;

/**
 * Created by Revotech on 05.07.16.
 */
public class Queries {
    public static final String SQL_GET_LIST_OF_NEWS = "SELECT NIKITASEGEN.News.ID, NICK_NAME, TITLE, CONTENT, " +
            "RELEASE_DATE, IMG_URL FROM NIKITASEGEN.News left join NIKITASEGEN.USERS on NIKITASEGEN.News.author_id = " +
            "NIKITASEGEN.USERS.id";

    public static final String SQL_ADD_NEWS = "INSERT INTO NIKITASEGEN.NEWS (ID, TITLE, RELEASE_DATE, CONTENT, " +
            "AUTHOR_id, IMG_URL) VALUES (news_seq.nextval, ?, ?, ?, ?, ?)";

    public static final String SQL_GET_AUTHOR_ID = "select ID from NIKITASEGEN.USERS where NICK_NAME = ?";

    public static final String SQL_DELETE_NEWS = "DELETE FROM NIKITASEGEN.News WHERE ID = ?";

    private Queries(){}
}
