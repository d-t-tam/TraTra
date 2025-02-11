/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.CustomerDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Customer;

@WebServlet(name = "CustomerProfileUpdate", urlPatterns = {"/update-profile"})
public class CustomerProfileUpdate extends HttpServlet {

    private final CustomerDAO customerDAO = new CustomerDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        request.getRequestDispatcher("updateCustomerProfile.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        HttpSession session = request.getSession();
        String message = "";

        try {
            String id = request.getParameter("id");
            String fullName = request.getParameter("fullName");
            String email = request.getParameter("email");
            String phoneNumber = request.getParameter("phoneNumber");
            String address = request.getParameter("address");

            if (id.isBlank() || fullName.isBlank() || email.isBlank() || phoneNumber.isBlank() || address.isBlank()) {
                throw new Exception("All input must be filled!");
            }

            String fullNameRegex = "\"^[\\\\p{L}]+( [\\\\p{L}]+)*$";
            if (!fullName.matches(fullNameRegex) || fullName.length() < 2 || fullName.length() > 50) {
                throw new Exception("""
                                    Full Name must > 2 characters and < 50 characters.
                                    Full name only containts alphabetic character and blank space""");
            }

            String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
            if (!email.matches(emailRegex)) {
                throw new Exception("""
                                    Invalid email address!
                                    """);
            }

            String phoneNumberRegex = "^(0\\d{9}|\\+84\\d{9})$";
            if (!phoneNumber.matches(phoneNumberRegex) || phoneNumber.length() < 10 || phoneNumber.length() > 11) {
                throw new Exception("""
                                    Phone must containts 10-11 number!
                                    """);
            }

            String addressRegex = "^[a-zA-Z0-9\\s,/#-]+$";
            if (!address.matches(addressRegex) || address.length() < 5 || address.length() > 100) {
                throw new Exception("""
                                    Address must containts 5-100 characters
                                    and only accept alphabetic, number, space and comma!""");
            }

            int iD = Integer.parseInt(id);

            Customer customer = Customer.builder()
                .id(iD)
                .fullName(fullName)
                .email(email)
                .phoneNumber(phoneNumber)
                .address(address)
                .build();

            int n = customerDAO.updateProfileCustomer(customer);
            if (n > 0) {
                customer = customerDAO.getCustomerById(iD);
                session.setAttribute("customer", customer);
                message = "Update profile successful!";
            }
        } catch (Exception e) {
            message = e.getMessage();
        }

        session.setAttribute("message", message);
        response.sendRedirect("update-profile");
    }
}
