package dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBContext {

    protected Connection connection;

    public DBContext() {

        try {
            String url = "jdbc:mysql://localhost:3306/vetau?zeroDateTimeBehavior=CONVERT_TO_NULL";
            String user = "root";
            String password = "thanhtam1004";
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        System.out.println(new DBContext().connection);
    }
}
