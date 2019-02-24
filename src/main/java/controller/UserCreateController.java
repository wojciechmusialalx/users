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

@WebServlet("/users/create")
public class UserCreateController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        RequestDispatcher rd =
                req.getRequestDispatcher("/WEB-INF/user-form.jsp");

        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // odczytac informacje z formularza (requestu)
        Integer id = req.getParameter("id") == null ?
                null : Integer.parseInt(req.getParameter("id"));
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String email = req.getParameter("email");

        // stworzyc obiekt User
        User user;
        if (id == null) {
            user = new User(firstName, lastName, email);
        } else {
            user = new User(id, firstName, lastName, email);
        }
        // zapisac go do bazy danych
        UserDao userDao = new UserDao(new Database());
        try {
            if (id == null) {
                userDao.save(user);
            } else {
                userDao.update(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // przekieruj uzytkownika do listy
        resp.sendRedirect("/users");
    }
}
