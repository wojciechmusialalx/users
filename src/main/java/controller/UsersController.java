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
import java.util.ArrayList;
import java.util.List;

@WebServlet("/users")
public class UsersController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        UserDao userDao = new UserDao(new Database());
        List<User> usersList;

        try {
            usersList = userDao.findAll();
        } catch (SQLException e) {
            usersList = new ArrayList<>();
        }

        req.setAttribute("listaPobranych", usersList);

        RequestDispatcher rd =
                req.getRequestDispatcher("/WEB-INF/users.jsp");

        rd.forward(req, resp);
    }
}
