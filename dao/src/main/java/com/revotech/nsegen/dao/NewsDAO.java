package com.revotech.nsegen.dao;

import com.revotech.nsegen.constants.NewsQueries;
import com.revotech.nsegen.datasource.DataSource;
import com.revotech.nsegen.entities.News;
import com.revotech.nsegen.exceptions.DAOException;
import org.apache.log4j.Logger;

import java.beans.PropertyVetoException;
import java.io.File;
import java.io.IOException;
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
            PreparedStatement prst = connection.prepareStatement(NewsQueries.SQL_GET_NEWS_BY_ID)){

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
     * This method used to generate news id
     * @return Integer news ID
     * @throws DAOException
     */
    private Integer getNextId() throws DAOException{
        Integer id = null;
        try(Connection connection = DataSource.getInstance().getConnection();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(NewsQueries.SQL_GET_NEWS_ID)){
            if(rs.next()){
                id = rs.getInt(1);
            } else {
                id = 1;
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
            PreparedStatement prst = connection.prepareStatement(NewsQueries.SQL_ADD_NEWS)) {

            //int authorID = UserDAO.getInstance().getUserIdByNickname(news.getAuthor());
            prst.setInt(1, id);
            prst.setString(2, news.getTitle());
            Date date = null;
            if(news.getDate() != null){
                date = Date.valueOf(news.getDate());
            }
            prst.setDate(3, date);
            prst.setString(4, news.getContent());
            prst.setString(5, news.getAuthor());
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
            ResultSet resultSet = statement.executeQuery(NewsQueries.SQL_GET_LIST_OF_NEWS)){

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
            PreparedStatement preparedStatement = connection.prepareStatement(NewsQueries.SQL_DELETE_NEWS)){

            preparedStatement.setInt(1, id);

            return preparedStatement.executeUpdate();

        } catch(PropertyVetoException | SQLException | IOException e) {
            log.error("DAO deleteEntity failed " + e);
            throw new DAOException("notdeleted" + e);
        }

    }

    /**
     * Update record in table News in database
     * @param news1 News which need to update
     * @return int Amount of changing row in database
     * @throws DAOException
     */
    public int updateEntity(News news1, News news2) throws DAOException {

        try(Connection connection = DataSource.getInstance().getConnection();
            PreparedStatement prst = connection.prepareStatement(NewsQueries.SQL_UPDATE_NEWS)) {

            prst.setString(1, (news1.getTitle() != null) ? news1.getTitle() : news2.getTitle());
            prst.setDate(2, Date.valueOf((news1.getDate() != null) ? news1.getDate() : news2.getDate()));
            prst.setString(3, (news1.getContent() != null) ? news1.getContent() : news2.getContent());
            prst.setString(4, (news1.getAuthor() != null) ? news1.getAuthor() : news2.getAuthor());
            prst.setString(5, (news1.getImgUrl() != null) ? news1.getImgUrl() : news2.getImgUrl());
            prst.setInt(6, news1.getId());

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
        news.setDate(resultSet.getDate("RELEASE_DATE").toLocalDate());
        news.setImgUrl(resultSet.getString("IMG_URL"));

        return news;
    }

}
