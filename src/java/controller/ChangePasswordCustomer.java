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

@WebServlet(name = "ChangePasswordCustomer", urlPatterns = {"/change-password"})
public class ChangePasswordCustomer extends HttpServlet {

    private final CustomerDAO customerDAO = new CustomerDAO();

    @Override

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        request.getRequestDispatcher("changeCustomerPassword.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        HttpSession session = request.getSession();
        String message;

        try {
            String id = request.getParameter("id");
            String oldPassword = request.getParameter("oldPassword");
            String newPassword = request.getParameter("newPassword");

            if (id.isBlank() || oldPassword.isBlank() || newPassword.isBlank()) {
                throw new Exception("All input must be filled!");
            }

            String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
            if (!newPassword.matches(passwordRegex)) {
                throw new Exception("Wrong password format!");
            }

            int iD = Integer.parseInt(id);
            int n = customerDAO.changePasswordCustomer(oldPassword, newPassword, iD);
            if (n < 1) {
                throw new Exception("Old password is wrong");
            }
            session.removeAttribute("customer");

            message = "Change password successfull.";
            session.setAttribute("message", message);
            response.sendRedirect("/tratra/customer/login");
        } catch (Exception e) {
            message = e.getMessage();
            session.setAttribute("message", message);
            response.sendRedirect("/tratra/change-password");
        }
    }
}
