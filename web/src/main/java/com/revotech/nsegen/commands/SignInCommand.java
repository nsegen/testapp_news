package com.revotech.nsegen.commands;

import com.revotech.nsegen.services.IUserService;
import com.revotech.nsegen.services.UserService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Revotech on 14.07.16.
 */
public class SignInCommand implements Command {
    private static final Logger log = Logger.getLogger(SignInPageCommand.class);
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        IUserService userService = UserService.getInstance();
        userService.loginUser(request, response);

    }
}