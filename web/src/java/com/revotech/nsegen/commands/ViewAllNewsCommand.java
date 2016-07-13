package com.revotech.nsegen.commands;

import com.revotech.nsegen.entities.News;
import com.revotech.nsegen.exceptions.NewsServiceException;
import com.revotech.nsegen.services.NewsService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Revotech on 27.06.16.
 */
public class ViewAllNewsCommand implements Command {

    private Logger log = Logger.getLogger(DeleteNewsCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        NewsService.getInstance().getListOfNews(request, response);
    }
}
