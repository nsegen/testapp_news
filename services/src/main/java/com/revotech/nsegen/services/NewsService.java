package com.revotech.nsegen.services;

import com.revotech.nsegen.dao.INewsDAO;
import com.revotech.nsegen.dao.NewsDAO;
import com.revotech.nsegen.entities.News;
import com.revotech.nsegen.exceptions.DAOException;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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

    public void viewNewsById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        News news = null;

        try {
            Integer id = Integer.parseInt(request.getParameter("id"));
            news = daoService.getNewsById(id);
            request.setAttribute("news", news);
            request.getRequestDispatcher("/WEB-INF/view/viewNews.jsp").forward(request, response);
        } catch(NumberFormatException | DAOException e) {
            request.setAttribute("error", "Error 404: page not found");
            request.getRequestDispatcher("/WEB-INF/view/error.jsp").forward(request, response);
        }
    }

    public News getNewsById(Integer id){
        News news = null;

        try {
            news = daoService.getNewsById(id);
        } catch(NumberFormatException | DAOException e) {
            log.info("News don't fined");
        }

        return news;
    }

    public void editNews(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        News news = null;

        try{
            news = createNews(request);
            News oldNews = daoService.getNewsById(news.getId());
            if(daoService.updateEntity(news, oldNews) == 0){
                log.error("NewsService editNews failed: amount of changes rows == 0");
                request.setAttribute("error", "notedited");

            } else {
                response.sendRedirect("controller?action=viewAll");
            }

        } catch (DAOException | IOException e) {
            log.error("NewsService editNews failed " + e);
            response.sendRedirect("controller?action=editNewsPage&id=" + news.getId() + "&error=notedited");
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

            String notDeleted = "";
            String ids[] = request.getParameterValues("ids");

            if(ids != null) {
                for (String i : ids) {

                    Integer id = Integer.valueOf(i);

                    News news = daoService.getNewsById(id);
                    StringBuffer imgUrl = new StringBuffer(news.getImgUrl());
                    int start = news.getImgUrl().indexOf("http://localhost:8083/");
                    imgUrl.delete(start, "http://localhost:8083/".length());
                    File newsImage = new File(imgUrl.toString());
                    if (!newsImage.delete()) {
                        log.warn("image '" + newsImage.getAbsolutePath() + "' don't deleted");
                    }

                    if (daoService.deleteEntity(id) == 0) {
                        notDeleted += id + ", ";
                    }
                }
            }

            if (!notDeleted.equals("")) {
                log.error("NewsService deleteNews failed: amount of changes rows == 0");
                request.setAttribute("error", "notdeleted");
            } else {
                response.sendRedirect("controller?action=viewAll");
            }

        } catch (DAOException e) {
            log.error("NewsService deleteNews failed " + e);
            request.setAttribute("error", "News don't deleted");
        }
    }
    public void addNews(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        News news = createNews(request);

        try{

            if(daoService.addEntity(news) == null){
                log.error("NewsService addNews failed: returned id == null");
                request.setAttribute("error", "notadded");
                request.setAttribute("news", news);
            } else {
                response.sendRedirect("controller?action=viewAll");
            }

        } catch (DAOException | IOException | DateTimeParseException e) {
            log.error("NewsService addNews failed " + e);
            request.setAttribute("news", news);
            request.getRequestDispatcher("controller?action=addNewsPage&error=notadded").forward(request, response);
        }
    }

    private News createNews(HttpServletRequest request) throws IOException, ServletException {
        News news = new News();
        if(!request.getParameter("content").isEmpty()) {
            news.setContent(request.getParameter("content"));
        }

        if (!request.getParameter("id").isEmpty()) {
            news.setId(Integer.valueOf(request.getParameter("id")));
        }

        if(!request.getParameter("author").isEmpty()) {
            news.setAuthor(request.getParameter("author"));
        }

        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date;
        try {
            date = LocalDate.parse(request.getParameter("date"), format);
        } catch (DateTimeParseException e) {
            date = null;
        }
        news.setDate(date);
        if(!request.getParameter("title").isEmpty()) {
            news.setTitle(request.getParameter("title"));
        }
        news.setImgUrl(ImageService.uploadImage(request, news.getTitle()));
        return news;
    }

}
