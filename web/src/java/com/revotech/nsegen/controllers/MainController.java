package com.revotech.nsegen.controllers;

import com.revotech.nsegen.commands.*;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Map;

/**
 * Created by Revotech on 23.06.16.
 */
@WebServlet("/controller")
@MultipartConfig
public class MainController extends HttpServlet {
    private static final Logger log = Logger.getLogger(MainController.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doProcess(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doProcess(req, resp);
    }

    private void doProcess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

        String action = req.getParameter("action");
        Command command = null;
        if (null != action){
            switch (action){
                case "addNewsPage": command = new AddNewsPageCommand(); break;
                case "delete": command = new DeleteNewsCommand(); break;
                case "viewAll": command = new ViewAllNewsCommand(); break;
                case "addNews": command = new AddNewsCommand(); break;
                case "editNewsPage": command = new EditNewsPageCommand(); break;
                case "editNews": command = new EditNewsCommand(); break;
                default: command = new ErrorPageCommand(); break;
            }

        } else {
            command = new ViewAllNewsCommand();
        }
        try {
            command.execute(req, resp);
        } catch(ServletException | IOException e){
            req.setAttribute("error", "Error 404: page not found");
            req.getRequestDispatcher("/WEB-INF/view/error.jsp").forward(req, resp);
        }
    }
}
