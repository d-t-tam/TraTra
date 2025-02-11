package controller;

import dal.CustomerDAO;
import model.Customer;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "RegisterServlet", urlPatterns = {"/register"})
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        // Lấy dữ liệu từ form đăng ký
        String fullName = request.getParameter("fullName");
        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("phone");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        String address = request.getParameter("address");
        CustomerDAO cdao = new CustomerDAO();

//         Kiểm tra email đã tồn tại chưa
        if (cdao.checkEmailExist(email)) {
            request.setAttribute("registerError", "Email existed");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        // Kiểm tra số điện thoại đã tồn tại chưa
        if (cdao.checkPhoneNumberExist(phoneNumber)) {
            request.setAttribute("registerError", "Phone Number existed");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        // Kiểm tra mật khẩu nhập lại
        if (!password.equals(confirmPassword)) {
            request.setAttribute("registerError", "password and repassword not match");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

//         Kiểm tra mật khẩu phải có cả chữ cái và số
        if (!password.matches(".*[a-zA-Z].*") || !password.matches(".*\\d.*")) {
            request.setAttribute("registerError", "Password must have both letter and number");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        // Kiểm tra định dạng số điện thoại (10 chữ số)
        if (!phoneNumber.matches("[0-9]{10}")) {
            request.setAttribute("registerError", "Phone Number Is not valid!");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }


        // Tạo đối tượng Customer
        Customer customer = new Customer();
        customer.setFullName(fullName);
        customer.setEmail(email);
        customer.setPhoneNumber(phoneNumber);
        customer.setPassword(password);
        customer.setAddress(address);
        customer.setRole("customer"); // Mặc định role là CUSTOMER
        customer.setStatus(1); // Trạng thái mặc định là 1 (active)

        // Thêm vào database
        if (cdao.insertCustomer(customer.getFullName(), customer.getEmail(), customer.getPassword(), customer.getPhoneNumber(), customer.getAddress(),customer.getStatus())) {
            response.sendRedirect("./customer/login");
        } else {
            request.setAttribute("registerError", "RegisterFail!.");
            request.getRequestDispatcher("register").forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "RegisterServlet ";
    }
}
