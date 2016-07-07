package com.revotech.nsegen.commands;

import com.revotech.nsegen.dao.NewsDAO;
import com.revotech.nsegen.entities.News;
import com.revotech.nsegen.exceptions.DAOException;
import com.revotech.nsegen.exceptions.NewsServiceException;
import com.revotech.nsegen.exceptions.NotChangeDataBaseException;
import com.revotech.nsegen.services.ImageService;
import com.revotech.nsegen.services.NewsService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * Created by Revotech on 06.07.16.
 */

public class AddNewsCommand implements Command {

    private Logger log = Logger.getLogger(AddNewsCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        try {
            News news = createNews(request);
            NewsService.getInstance().addNews(news);
            response.sendRedirect("controller?action=viewAll");
        } catch (NewsServiceException | NotChangeDataBaseException e) {
            log.error("News don't added " + e);
            request.setAttribute("error", "News don't added");
            //request.getRequestDispatcher("/WEB-INF/view/error.jsp").forward(request, response);
        }
    }

    private News createNews(HttpServletRequest request) throws ServletException, IOException{
        String content = request.getParameter("content");
        String nickName = request.getParameter("author");
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = format.parse(request.getParameter("date"));
        } catch(ParseException e) {
            log.info("Date can't be parsed");
        }
        String title = request.getParameter("title");
        return new News(0, title, nickName, content, ImageService.uploadImage(request.getPart("image"), title), date);
    }

}
