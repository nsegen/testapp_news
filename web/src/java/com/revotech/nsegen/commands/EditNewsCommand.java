package com.revotech.nsegen.commands;

import com.revotech.nsegen.entities.News;
import com.revotech.nsegen.exceptions.NewsServiceException;
import com.revotech.nsegen.exceptions.NotChangeDataBaseException;
import com.revotech.nsegen.services.ImageService;
import com.revotech.nsegen.services.NewsService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Revotech on 04.07.16.
 */
public class EditNewsCommand implements Command {
    private Logger log = Logger.getLogger(EditNewsCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            News news = createNews(request);
            NewsService.getInstance().editNews(news);
            response.sendRedirect("controller?action=viewAll");
        } catch (NewsServiceException | NotChangeDataBaseException e) {
            log.error("News don't edited " + e);
            request.setAttribute("error", "News don't edited");
            //request.getRequestDispatcher("/WEB-INF/view/error.jsp").forward(request, response);
        }
    }

    private News createNews(HttpServletRequest request) throws ServletException, IOException{
        int id = Integer.valueOf(request.getParameter("id"));
        String content = request.getParameter("content");
        String title = request.getParameter("title");
        return new News(id, title, "", content, ImageService.uploadImage(request.getPart("image"), title), null);
    }
}
