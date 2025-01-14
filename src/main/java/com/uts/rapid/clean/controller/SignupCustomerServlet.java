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

public class SignupCustomerServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        CustomerDAO customerDAO = (CustomerDAO) session.getAttribute("customerDAO");
        CleanerDAO cleanerDAO = (CleanerDAO) session.getAttribute("cleanerDAO");
        
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        String phoneNumber = request.getParameter("phoneNumber");
        
        Validator validator = new Validator();
        validator.clear(session);
        int validationTestPassed = 0;
        
        if (validator.validateName(firstName))
            validationTestPassed++;
        else
            session.setAttribute("firstNameError", "Invalid name");
        
        if (validator.validateName(lastName))
            validationTestPassed++;
        else
            session.setAttribute("lastNameError", "Invalid name");
        
        if (validator.validateEmail(email))
            validationTestPassed++;
        else
            session.setAttribute("emailError", "Invalid email address");
        
        if (validator.validatePassword(password))
            validationTestPassed++;
        else
            session.setAttribute("passwordError", "Invalid password");
        
        if (password.equals(confirmPassword))
            validationTestPassed++;
        else
            session.setAttribute("confirmPasswordError", "Passwords do not match");
        
        if (validator.validatePhoneNumber(phoneNumber))
            validationTestPassed++;
        else
            session.setAttribute("phoneNumberError", "Invalid phone number");
        
        if (validationTestPassed == 6) {
            if (!customerDAO.hasCustomer(email) && !cleanerDAO.hasCleaner(email)) {
                customerDAO.createCustomer(firstName, lastName, email, password, phoneNumber);
                Customer customer = customerDAO.findCustomer(email, password);
                session.setAttribute("customer", customer);
                request.getRequestDispatcher("home.jsp").forward(request, response);
            }
            else {
                session.setAttribute("emailError", "Email address already in use");
                request.getRequestDispatcher("signup-customer.jsp").forward(request, response);
            }
        }
        else {
            request.getRequestDispatcher("signup-customer.jsp").forward(request, response);
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
