/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.adminController;

import dal.AccountDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.User;

/**
 *
 * @author Aus TUF GAMAING
 */
@WebServlet(name = "EditAccount", urlPatterns = {"/admin_page/EditAccount"})
public class EditAccount extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet EditAccount</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EditAccount at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        AccountDAO acc_dao = new AccountDAO();
        String role = request.getParameter("role");
        int id = Integer.parseInt(request.getParameter("id"));
        User user = acc_dao.getChoosedUser(role, id);
        request.setAttribute("email", user.getEmail());
        request.setAttribute("fullname", user.getFullName());
        request.setAttribute("phonenumber", user.getPhoneNumber());
        request.setAttribute("address", user.getAddress());
        request.setAttribute("role", user.getRole());
        request.setAttribute("id", String.valueOf(id));

        request.getRequestDispatcher("EditAccount.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String fullname = request.getParameter("fullname");
        String phonenumber = request.getParameter("phonenumber");
        String role = request.getParameter("role");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        String email_old = request.getParameter("email_old");
        int id = Integer.parseInt(request.getParameter("id"));

        AccountDAO acc_dao = new AccountDAO();
        if (!email.equals(email_old)) {
            List<String> emails = acc_dao.getAllEmail();
            for (String e : emails) {
                if (e.equals(email)) {
                    request.setAttribute("email", email_old);
                    request.setAttribute("fullname", fullname);
                    request.setAttribute("phonenumber", phonenumber);
                    request.setAttribute("address", address);
                    request.setAttribute("role", role);
                    request.setAttribute("id", String.valueOf(id));
                    request.setAttribute("error", "That email is already in use");
                    request.getRequestDispatcher("EditAccount.jsp").forward(request, response);
                    return;
                }
            }
        }

        acc_dao.editAcc(fullname, email, phonenumber, address, role, id);

        response.sendRedirect("AccountList?role=" + role);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
