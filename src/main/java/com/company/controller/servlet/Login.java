package com.company.controller.servlet;//package com.company.controller.servlet;

import com.company.model.dao.UserDAO;
import com.company.model.encryption.CryptPassword;
import com.company.model.entity.user.User;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

import static com.company.controller.servlet.Constants.*;

public class Login extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/internet_provider/login.jsp");
        dispatcher.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        User user;
        String errorMessage = null;
        String active;
        try {
            UserDAO dao = new UserDAO();
            user = dao.getUser(login);
            try {
                if(CryptPassword.check(req.getParameter("password"),user.getPassword())){
                    active = ACTIVE;
                    req.getServletContext().setAttribute(ACTIVE,active);
                    HttpSession userSession = req.getSession();
                    userSession.setAttribute(USER,user);
                    resp.sendRedirect("/internet");
                    return;
                }
                else{
                    errorMessage = "Incorrect password";
                }
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
        catch (SQLException e){
            errorMessage = "Incorrect login";
        }
        req.setAttribute("errorMessage",errorMessage);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/internet_provider/login.jsp");
        requestDispatcher.forward(req,resp);
    }
}
//