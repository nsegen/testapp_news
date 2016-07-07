package com.revotech.nsegen.commands;

import com.revotech.nsegen.exceptions.NewsServiceException;
import com.revotech.nsegen.exceptions.NotChangeDataBaseException;
import com.revotech.nsegen.services.NewsService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Revotech on 23.06.16.
 */
public class DeleteNewsCommand implements Command {

    private Logger log = Logger.getLogger(DeleteNewsCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

        int id = Integer.valueOf(request.getParameter("id"));

        try {
            NewsService.getInstance().deleteNews(id);
            response.sendRedirect("controller?action=viewAll");
        } catch (NewsServiceException | NotChangeDataBaseException e) {
            log.error("News don't deleted " + e);
            request.setAttribute("error", "News don't deleted");
        }
    }
}
