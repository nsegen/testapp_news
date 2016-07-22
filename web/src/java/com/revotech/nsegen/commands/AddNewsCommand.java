package com.revotech.nsegen.commands;

import com.revotech.nsegen.services.NewsService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * Created by Revotech on 06.07.16.
 */

public class AddNewsCommand implements Command {

    private Logger log = Logger.getLogger(AddNewsCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        NewsService.getInstance().addNews(request, response);
    }

}
