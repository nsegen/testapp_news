package com.revotech.nsegen.services;

import com.revotech.nsegen.dao.INewsDAO;
import com.revotech.nsegen.dao.NewsDAO;
import com.revotech.nsegen.entities.News;
import com.revotech.nsegen.exceptions.DAOException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Revotech on 06.07.16.
 */
public class NewsService implements INewsService{

    private INewsDAO daoService;

    private static INewsService newsService;

    private static final Logger log = Logger.getLogger(NewsService.class);

    public List<News> getListOfNews(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<News> news = null;
        try {
            news = daoService.getEntities();
            request.setAttribute("newsList", news);
            request.getRequestDispatcher("/WEB-INF/view/viewallnews.jsp").forward(request, response);
        } catch(DAOException e) {
            log.error("NewsService getListOfNews failed " + e);
            request.setAttribute("error", "Error 500");
            request.getRequestDispatcher("/WEB-INF/view/error.jsp").forward(request, response);
        }
        return news;
    }

    public void editNews(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        News news = null;

        try{
            news = createNews(request);
            if(daoService.updateEntity(news) == 0){
                log.error("NewsService editNews failed: amount of changes rows == 0");
                request.setAttribute("error", "News don't edited");

            } else {
                response.sendRedirect("controller?action=viewAll");
            }

        } catch (DAOException | IOException e) {
            log.error("NewsService editNews failed " + e);
            request.setAttribute("error", "News don't edited");
        }

    }

    private NewsService(){
        daoService = NewsDAO.getInstance();
    }

    public static INewsService getInstance(){
        if(newsService == null){
            newsService = new NewsService();
        }
        return newsService;
    }

    public void deleteNews(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        try{
            Integer id = Integer.valueOf(request.getParameter("id"));
            if(daoService.deleteEntity(id) == 0){
                log.error("NewsService deleteNews failed: amount of changes rows == 0");
                request.setAttribute("error", "News don't deleted");
            } else {
                response.sendRedirect("controller?action=viewAll");
            }

        } catch (DAOException e) {
            log.error("NewsService deleteNews failed " + e);
            request.setAttribute("error", "News don't deleted");
        }
    }
    public void addNews(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        News news = null;

        try{
            news = createNews(request);
            if(daoService.addEntity(news) == null){
                log.error("NewsService addNews failed: returned id == null");
                request.setAttribute("error", "News don't added");
            } else {
                response.sendRedirect("controller?action=viewAll");
            }

        } catch (DAOException | IOException e) {
            log.error("NewsService editNews failed " + e);
            request.setAttribute("error", "News don't added");
        }
    }

    private News createNews(HttpServletRequest request) throws IOException, ServletException {
        News news = new News();
        //if(request.getParameter("content") != null) {
            news.setContent(request.getParameter("content"));
        //}

        if (request.getParameter("id") != null) {
            news.setId(Integer.valueOf(request.getParameter("id")));
        }

        news.setAuthor(request.getParameter("author"));
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            if(request.getParameter("date") != null) {
                date = format.parse(request.getParameter("date"));
            }
        } catch(ParseException e) {
            log.info("Date can't be parsed");
        }
        news.setDate(date);
        news.setTitle(request.getParameter("title"));
        news.setImgUrl(ImageService.uploadImage(request.getPart("image"), news.getTitle()));
        return news;
    }

}
