package com.revotech.nsegen.commands;

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

        NewsService.getInstance().editNews(request, response);

    }

}
