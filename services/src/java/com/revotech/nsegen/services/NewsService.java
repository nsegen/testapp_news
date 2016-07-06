package com.revotech.nsegen.services;

import com.revotech.nsegen.dao.NewsDAO;
import com.revotech.nsegen.entities.News;
import com.revotech.nsegen.exceptions.DAOException;
import com.revotech.nsegen.exceptions.NewsServiceException;
import com.revotech.nsegen.exceptions.NotChangeDataBaseException;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Created by Revotech on 06.07.16.
 */
public class NewsService {

    private NewsDAO daoService;

    private static NewsService newsService;

    private static final Logger log = Logger.getLogger(NewsService.class);

    public List<News> getListOfNews() throws NewsServiceException {
        List<News> news = null;
        try {
            news = daoService.getEntities();
        } catch(DAOException e) {
            e.printStackTrace();
            throw new NewsServiceException("News don't deleted", e);
        }
        return news;
    }

    private NewsService(){
        daoService = NewsDAO.getInstance();
    }

    public static NewsService getInstance(){
        if(newsService == null){
            newsService = new NewsService();
        }
        return newsService;
    }

    public void deleteNews(int id) throws NewsServiceException, NotChangeDataBaseException{
        try{
            if(daoService.deleteEntity(id)==0){
                throw new NotChangeDataBaseException("News " + id + " don't deleted");
            }

        } catch (DAOException e) {
            e.printStackTrace();
            throw new NewsServiceException("News don't deleted", e);
        }
    }
    public void addNews(News news) throws NewsServiceException, NotChangeDataBaseException{

        try{
            if(daoService.addEntity(news)==0){
                throw new NotChangeDataBaseException("News " + news.getTitle() + " don't added");
            }

        } catch (DAOException e) {
            e.printStackTrace();
            throw new NewsServiceException("News don't deleted", e);
        }
    }

}
