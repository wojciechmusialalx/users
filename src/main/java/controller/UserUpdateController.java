package controller;

import config.Database;
import dao.UserDao;
import domain.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/users/update")
public class UserUpdateController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));

        UserDao userDao = new UserDao(new Database());

        User user;

        try {
            user = userDao.findById(id);
        } catch (SQLException e) {
            user = null;
        }

        req.setAttribute("user", user);

        RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/user-form.jsp");
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
