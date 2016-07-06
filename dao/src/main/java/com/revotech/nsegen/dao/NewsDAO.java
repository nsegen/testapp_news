package com.revotech.nsegen.dao;

import com.revotech.nsegen.constants.Queries;
import com.revotech.nsegen.datasource.DataSource;
import com.revotech.nsegen.entities.News;
import com.revotech.nsegen.exceptions.DAOException;
import org.apache.log4j.Logger;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Revotech on 05.07.16.
 */
public class NewsDAO implements INewsDAO {

    private static final Logger log = Logger.getLogger(NewsDAO.class);

    private static NewsDAO newsDAO;

    private NewsDAO(){
        Locale.setDefault(Locale.ENGLISH);
    }

    public static NewsDAO getInstance(){
        if(newsDAO == null){
            newsDAO = new NewsDAO();
        }
        return newsDAO;
    }

    private int getAuthorId(String nickName) throws DAOException{

        int authorID = 0;

        try(Connection connection = DataSource.getInstance().getConnection();
            PreparedStatement prst = connection.prepareStatement(Queries.SQL_GET_AUTHOR_ID)) {

            prst.setString(1, nickName);

            try(ResultSet resultSet = prst.executeQuery()){
                if(resultSet.next()) {
                    authorID = resultSet.getInt("ID");
                }
            }

        } catch (IOException | SQLException | PropertyVetoException e) {
            log.error("DAO getAuthorId failed " + e);
            throw new DAOException("DAO getAuthorId failed " + e);
        }
        return authorID;
    }

    public int addEntity(News news) throws DAOException {
        try(Connection connection = DataSource.getInstance().getConnection();
            PreparedStatement prst = connection.prepareStatement(Queries.SQL_ADD_NEWS)) {

            int authorID = getAuthorId(news.getAuthor());

            prst.setString(1, news.getTitle());
            prst.setDate(2, new Date(news.getDate().getTime()));
            byte[] content = news.getContent().getBytes("utf-8");
            prst.setBytes(3, content);
            prst.setInt(4, authorID);
            prst.setString(5, news.getImgUrl());

            return prst.executeUpdate();

        } catch (PropertyVetoException | IOException | SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public List<News> getEntities() throws DAOException {
        List<News> news = new ArrayList<>();

        try(Connection connection = DataSource.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(Queries.SQL_GET_LIST_OF_NEWS)){

            while (resultSet.next()) {
                news.add(createNews(resultSet));
            }
        } catch (PropertyVetoException | SQLException | IOException e){
            e.printStackTrace();
            throw new DAOException(e);
        }

        return news;
    }

    public int deleteEntity(int id) throws DAOException {

        int amtDelete = 0;

        try(Connection connection = DataSource.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(Queries.SQL_DELETE_NEWS)){

            preparedStatement.setInt(1, id);
            amtDelete = preparedStatement.executeUpdate();

        } catch(PropertyVetoException | SQLException | IOException e) {

            throw new DAOException("DAO DeleteEntity failed " + e);
        }
        return amtDelete;
    }

    public int updateEntity(News entity) throws DAOException {
        return 0;
    }

    private News createNews(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("ID");
        String nickName = resultSet.getString("NICK_NAME");
        String title = resultSet.getString("TITLE");
        Blob blobContent = resultSet.getBlob("CONTENT");
        byte[] byteContent = blobContent.getBytes(1, (int)blobContent.length());
        String content = new String(byteContent);
        Date date = resultSet.getDate("RELEASE_DATE");
        String imgUrl = resultSet.getString("IMG_URL");

        return new News(id, title, nickName, content, imgUrl, date);
    }

}
