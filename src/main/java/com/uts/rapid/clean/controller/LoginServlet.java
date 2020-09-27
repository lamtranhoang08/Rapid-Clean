package com.uts.rapid.clean.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.uts.rapid.clean.model.dao.CustomerDAO;
import com.uts.rapid.clean.model.dao.CleanerDAO;
import com.uts.rapid.clean.model.Customer;
import com.uts.rapid.clean.model.Cleaner;

public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        CustomerDAO customerDAO = new CustomerDAO();
        CleanerDAO cleanerDAO = new CleanerDAO();
        
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        Validator validator = new Validator();
        validator.clear(session);
        
        if (validator.validateEmail(email) && validator.validatePassword(password)) {
            Customer customer = customerDAO.findCustomer(email, password);
            Cleaner cleaner = cleanerDAO.findCleaner(email, password);
            if (customer != null) {
                session.setAttribute("customer", customer);
                request.getRequestDispatcher("home.jsp").include(request, response);
            }
            else if (cleaner != null) {
                request.getRequestDispatcher("/CleanerOrderServlet?cleanerId=5f704dcf71921265c278ada2").include(request, response);
            }
            else {
                session.setAttribute("loginError", "Invalid username or password");
                request.getRequestDispatcher("login.jsp").include(request, response);
            }
        }
        else {
            session.setAttribute("loginError", "Invalid username or password");
            request.getRequestDispatcher("login.jsp").include(request, response);
        }
    }
}
