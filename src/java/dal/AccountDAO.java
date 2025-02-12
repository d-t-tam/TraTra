/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;

/**
 *
 * @author Aus TUF GAMAING
 */
public class AccountDAO extends DBContext {

    public List<User> getAllChoosedAcc(String role) {
        List<User> users = new ArrayList<>();
        String sql = "select * from " + role;
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String fullName = rs.getString("full_name");
                String email = rs.getString("email");
                String phoneNumber = rs.getString("phone_number");
                String address = rs.getString("address");
                int status = rs.getInt("status");

                User user = new User(id, fullName, email, phoneNumber, address, role, status, "1");
                users.add(user);
            }

        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return users;
    }

    public List<String> getAllEmail() {
        List<String> emails = new ArrayList<>();
        String sql = """
                     SELECT email FROM seller
                     UNION ALL
                     SELECT email FROM manager
                     UNION ALL
                     SELECT email FROM staff
                     UNION ALL
                     SELECT email FROM customer
                     UNION ALL
                     SELECT email FROM admin;""";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                String email = rs.getString("email");
                emails.add(email);
            }

        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return emails;
    }

    public User getChoosedUser(String role, int id) {
        User user = new User();
        String sql = "select * from " + role + " where id = " + id;
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                String fullName = rs.getString("full_name");
                String email = rs.getString("email");
                String phoneNumber = rs.getString("phone_number");
                String address = rs.getString("address");
                int status = rs.getInt("status");

                user = new User(id, fullName, email, phoneNumber, address, role, status, "1");
            }

        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return user;
    }

    public void changeStatus(int id, String role) {
        String sql = "UPDATE " + role + "\n"
                + "SET status = CASE WHEN status = 0 THEN 1 ELSE 0 END\n"
                + "WHERE id = ?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, id);
            pre.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void editAcc(String fullname, String email, String phonenumber, String address, String role, int id) {
        String sql = "UPDATE " + role + "\n"
                + "SET full_name = ?, email = ?, phone_number = ?, address = ?, role = ? \n"
                + "WHERE id = ?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, fullname);
            pre.setString(2, email);
            pre.setString(3, phonenumber);
            pre.setString(4, address);
            pre.setString(5, role);
            pre.setInt(6, id);

            pre.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addNewAcc(String fullname, String email, String phonenumber, String password, String role) {
        String sql;
        if (role.equalsIgnoreCase("Seller")) {
            sql = "INSERT INTO " + role + " (full_name, img_url, email, phone_number, password, address, status) VALUES (?, 'aaa', ?, ?, ?, 'aaa', 1)";
        } else {
            sql = "INSERT INTO " + role + " (full_name, email, phone_number, password, address, status) VALUES (?, ?, ?, ?, 'aaa', 1)";
        }
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, fullname);
            pre.setString(2, email);
            pre.setString(3, phonenumber);
            pre.setString(4, password);
            pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
