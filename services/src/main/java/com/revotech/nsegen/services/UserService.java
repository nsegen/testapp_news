package com.revotech.nsegen.services;

import com.revotech.nsegen.constants.Const;
import com.revotech.nsegen.dao.IUserDAO;
import com.revotech.nsegen.dao.UserDAO;
import com.revotech.nsegen.entities.User;
import com.revotech.nsegen.exceptions.DAOException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Revotech on 14.07.16.
 */
public class UserService implements IUserService {

    private static IUserService userService;
    private IUserDAO userDAO;

    private static final Logger log = Logger.getLogger(UserService.class);

    private UserService(){
        userDAO = UserDAO.getInstance();
    }

    public static IUserService getInstance(){
        if(userService == null){
            userService = new UserService();
        }
        return userService;
    }

    @Override
    public void registerUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User user = createUser(request);
        try {
            userDAO.addEntity(user);
            HttpSession session = request.getSession();
            session.setMaxInactiveInterval(Const.SESSION_LIFETIME);
            session.setAttribute("nickname", user.getNickname());
            response.sendRedirect("controller?action=viewAll");
        } catch(DAOException e) {
            request.setAttribute("error", "User with nickname '" + user.getNickname() + "' is exist");
            request.getRequestDispatcher("/WEB-INF/view/register.jsp").forward(request, response);
        }

    }

    @Override
    public void loginUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User user = createUser(request);
        try {
            Integer id = userDAO.getUserIdByNickname(user.getNickname());
            //if(id != null) {
                User tempUser = userDAO.getUserById(id);
                if (user.getPassword().equals(tempUser.getPassword())) {
                    HttpSession session = request.getSession();
                    session.setMaxInactiveInterval(Const.SESSION_LIFETIME);
                    session.setAttribute("nickname", tempUser.getNickname());
                } else {
                    request.setAttribute("error", "Wrong password");
                    request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
                }
            /*} else {
                request.setAttribute("error", "User with nickname '" + user.getNickname() + "' don't exist");
                //request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
            }*/
        } catch(DAOException e) {
            request.setAttribute("error", "User with nickname '" + user.getNickname() + "' don't exist");
            request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
        }

    }

    @Override
    public void updateUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void deleteUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private User createUser(HttpServletRequest request){

        User user = new User();

        Integer id = null;
        if(request.getParameter("id") != null){
            id = Integer.valueOf(request.getParameter("id"));
        }
        user.setId(id);
        user.setNickname(request.getParameter("nickname"));
        user.setFirstName(request.getParameter("firstName"));
        user.setLastName(request.getParameter("lastName"));
        user.setPassword(request.getParameter("password"));

        return user;
    }

}
