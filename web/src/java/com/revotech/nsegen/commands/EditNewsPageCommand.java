package com.revotech.nsegen.commands;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Revotech on 07.07.16.
 */
public class EditNewsPageCommand implements Command {
    private static final Logger log = Logger.getLogger(EditNewsPageCommand.class);
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("/WEB-INF/view/editNews.jsp").forward(request, response);

    }
}