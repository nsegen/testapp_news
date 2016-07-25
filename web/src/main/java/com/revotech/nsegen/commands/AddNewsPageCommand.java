package com.revotech.nsegen.commands;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Revotech on 23.06.16.
 */
public class AddNewsPageCommand implements Command {
    private static final Logger log = Logger.getLogger(AddNewsPageCommand.class);
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        log.info(request.getParameter("error"));
        request.setAttribute("error", request.getParameter("error"));
        request.setAttribute("nextAction", "addNews");
        request.getRequestDispatcher("/WEB-INF/view/addNews.jsp").forward(request, response);

    }
}
