package com.revotech.nsegen.services;

import com.revotech.nsegen.entities.News;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Revotech on 13.07.16.
 */
public interface INewsService {

    List<News> getListOfNews(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

    void editNews(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

    void deleteNews(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

    void addNews(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

    News getNewsById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

}
