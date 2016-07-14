package com.revotech.nsegen.services;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Revotech on 14.07.16.
 */
public interface IUserService {

    void registerUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
    void loginUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
    void updateUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
    void deleteUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

}
