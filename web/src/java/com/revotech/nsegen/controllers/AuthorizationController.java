package com.revotech.nsegen.controllers;

import com.revotech.nsegen.commands.*;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Revotech on 14.07.16.
 */
public class AuthorizationController extends HttpServlet {
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
        log.info(action);


        Command command = null;
        if (null != action){
            switch (action){
                case "signinpage": command = new SignInPageCommand(); break;
                case "signin": command = new SignInCommand(); break;
                case "signuppage": command = new SignUpPageCommand(); break;
                case "signup": command = new SignUpCommand(); break;
                case "logout": command = new LogOutCommand(); break;
                default: command = new SignInPageCommand(); break;
            }

        } else {
            command = new SignInPageCommand();
        }
        try {
            command.execute(req, resp);
        } catch(ServletException | IOException e){
            req.setAttribute("error", "Error 404: page not found");
            req.getRequestDispatcher("/WEB-INF/view/error.jsp").forward(req, resp);
        }
    }
}