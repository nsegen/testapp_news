package com.revotech.nsegen.dao;

import com.revotech.nsegen.constants.Queries;
import com.revotech.nsegen.datasource.DataSource;
import com.revotech.nsegen.entities.News;
import com.revotech.nsegen.exceptions.DAOException;
import org.apache.log4j.Logger;

import javax.xml.crypto.Data;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Revotech on 05.07.16.
 * This class working with database and news entity
 */
public class NewsDAO implements INewsDAO {

    private static final Logger log = Logger.getLogger(NewsDAO.class);

    private static NewsDAO newsDAO;

    /**
     * constructor is private, because this class is a singleton
     */
    private NewsDAO(){
        Locale.setDefault(Locale.ENGLISH);
    }

    /**
     * @return instance NewsDAO
     */
    public static NewsDAO getInstance(){
        if(newsDAO == null){
            newsDAO = new NewsDAO();
        }
        return newsDAO;
    }

    /**
     * This method used when we need news and we know their id
     * @param id this is id news which we need
     * @return News which we need
     * @throws DAOException
     */
    public News getNewsById(Integer id) throws DAOException{

        News news = null;

        try(Connection connection = DataSource.getInstance().getConnection();
            PreparedStatement prst = connection.prepareStatement(Queries.SQL_GET_NEWS_BY_ID)){

            prst.setInt(1, id);

            ResultSet rs = prst.executeQuery();
            if(rs.next()){
                news = createNews(rs);
            }
        } catch(IOException | SQLException | PropertyVetoException e) {
            log.error("DAO GetNewsById failed " + e);
            throw new DAOException("DAO GetNewsById failed " + e);
        }
        return news;
    }

    /**
     * This method is used to find user according according to his nickname
     * @param nickName this is author nickname
     * @return user id with given nickname
     * @throws DAOException
     */
    private int getAuthorId(String nickName) throws DAOException{

        int authorID = 0;

        try(Connection connection = DataSource.getInstance().getConnection();
            PreparedStatement prst = connection.prepareStatement(Queries.SQL_GET_AUTHOR_ID)) {

            prst.setString(1, nickName);

            ResultSet resultSet = prst.executeQuery();
            if(resultSet.next()) {
                authorID = resultSet.getInt("ID");
            }
            resultSet.close();

        } catch (IOException | SQLException | PropertyVetoException e) {
            log.error("DAO getAuthorId failed " + e);
            throw new DAOException("DAO getAuthorId failed " + e);
        }
        return authorID;
    }

    private Integer getNextId() throws DAOException{
        Integer id = null;
        try(Connection connection = DataSource.getInstance().getConnection();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(Queries.SQL_GET_NEWS_ID)){
            if(rs.next()){
                id = rs.getInt(1);
            }
        } catch (PropertyVetoException | IOException | SQLException e){
            log.error("DAO addEntity failed " + e);
            throw new DAOException("DAO getNextId failed " + e);
        }

        return id;
    }

    /**
     * This method add record in table NEWS in database
     * @param news This is news which will be add in database
     * @return id news
     * @throws DAOException
     */
    public Integer addEntity(News news) throws DAOException {

        Integer id = getNextId();

        try(Connection connection = DataSource.getInstance().getConnection();
            PreparedStatement prst = connection.prepareStatement(Queries.SQL_ADD_NEWS)) {

            int authorID = getAuthorId(news.getAuthor());
            prst.setInt(1, id);
            prst.setString(2, news.getTitle());
            prst.setDate(3, new Date(news.getDate().getTime()));
            prst.setString(4, news.getContent());
            prst.setInt(5, authorID);
            prst.setString(6, news.getImgUrl());
            prst.executeUpdate();

        } catch (PropertyVetoException | IOException | SQLException e) {
            log.error("DAO addEntity failed " + e);
            throw new DAOException("DAO addEntity failed " + e);
        }

        return id;

    }

    /**
     * This method return list of all news, which contains table NEWS
     * @return List This is list of news
     * @throws DAOException
     */
    public List<News> getEntities() throws DAOException {
        List<News> news = new ArrayList<>();

        try(Connection connection = DataSource.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(Queries.SQL_GET_LIST_OF_NEWS)){

            while (resultSet.next()) {
                news.add(createNews(resultSet));
            }
        } catch (PropertyVetoException | SQLException | IOException e){
            log.error("DAO getEntities failed " + e);
            throw new DAOException("DAO getEntities failed " + e);
        }

        return news;
    }

    /**
     * This method delete records from table News, where id equals given id
     * @param id News id which need to remove
     * @return int Amount of changing row in database
     * @throws DAOException
     */
    public int deleteEntity(int id) throws DAOException {

        try(Connection connection = DataSource.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(Queries.SQL_DELETE_NEWS)){

            preparedStatement.setInt(1, id);

            return preparedStatement.executeUpdate();

        } catch(PropertyVetoException | SQLException | IOException e) {
            log.error("DAO deleteEntity failed " + e);
            throw new DAOException("DAO deleteEntity failed " + e);
        }

    }

    /**
     * Update record in table News in database
     * @param news News which need to update
     * @return int Amount of changing row in database
     * @throws DAOException
     */
    public int updateEntity(News news) throws DAOException {

        try(Connection connection = DataSource.getInstance().getConnection();
            PreparedStatement prst = connection.prepareStatement(Queries.SQL_UPDATE_NEWS)) {

            prst.setString(1, news.getTitle());
            prst.setString(2, news.getContent());
            prst.setString(3, news.getImgUrl());
            prst.setInt(4, news.getId());

            return prst.executeUpdate();

        } catch (PropertyVetoException | IOException | SQLException e) {
            log.error("DAO editEntity failed " + e);
            throw new DAOException("DAO editEntity failed " + e);
        }
    }

    /**
     * This method create News with parameter such as in result set
     * @param resultSet
     * @return News Creating news
     * @throws SQLException
     */
    private News createNews(ResultSet resultSet) throws SQLException {

        News news = new News();

        news.setId(resultSet.getInt("ID"));
        news.setAuthor(resultSet.getString("NICK_NAME"));
        news.setTitle(resultSet.getString("TITLE"));
        news.setContent(resultSet.getString("CONTENT"));
        news.setDate(resultSet.getDate("RELEASE_DATE"));
        news.setImgUrl(resultSet.getString("IMG_URL"));

        return news;
    }

}
