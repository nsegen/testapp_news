package com.revotech.nsegen.commands;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Revotech on 07.07.16.
 */
public class ErrorPageCommand implements Command {
    private static final Logger log = Logger.getLogger(ErrorPageCommand.class);
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setAttribute("error", "Error 404: page not found");
        request.getRequestDispatcher("/WEB-INF/view/error.jsp").forward(request, response);

    }
}