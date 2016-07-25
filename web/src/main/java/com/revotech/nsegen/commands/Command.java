package com.revotech.nsegen.commands;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Revotech on 23.06.16.
 */
public interface Command {
    void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
