package com.uts.rapid.clean.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.uts.rapid.clean.model.Customer;
import com.uts.rapid.clean.model.dao.AddressDAO;

public class CreateAddressServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");
        AddressDAO addressDAO = (AddressDAO) session.getAttribute("addressDAO");
        
        String streetAddress = request.getParameter("streetAddress");
        String suburb = request.getParameter("suburb");
        String state = request.getParameter("state");
        String postcode = request.getParameter("postcode");
        
        Validator validator = new Validator();
        validator.clear(session);
        int validationTestPassed = 0;
        
        if (validator.validateStreetAddress(streetAddress))
            validationTestPassed++;
        else
            session.setAttribute("streetAddressError", "Invalid address");
        
        if (validator.validateSuburb(suburb))
            validationTestPassed++;
        else
            session.setAttribute("suburbError", "Invalid suburb");
        
        if (validator.validatePostcode(postcode))
            validationTestPassed++;
        else
            session.setAttribute("postcodeError", "Invalid postcode");
        
        if (validationTestPassed == 3) {
            if (!addressDAO.hasAddress(customer.getId(), streetAddress, suburb, state, Integer.parseInt(postcode))) {
                addressDAO.createAddress(customer.getId(), streetAddress, suburb, state, Integer.parseInt(postcode));
                session.setAttribute("actionResult", "Address added");
                request.getRequestDispatcher("my-addresses.jsp").forward(request, response);
            }
            else {
                session.setAttribute("addressExistError", "This address has already been added");
                request.getRequestDispatcher("create-address.jsp").forward(request, response);
            }
        }
        else {
            request.getRequestDispatcher("create-address.jsp").forward(request, response);
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