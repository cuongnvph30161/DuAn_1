/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilities;

import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author ADMIN
 */
public class DBConnect {

    private static Connection con = null;
    private static PreparedStatement st = null;
    public static final String url = "jdbc:sqlserver://localhost:1433;"
            + "DatabaseName=ToTo;encrypt=true;trustServerCertificate=true";

    public static Connection getConnect() {
        Connection connect = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (Exception e) {
            System.out.println("chua co driver");

        }
        try {

            connect = DriverManager.getConnection(url, "sa", "sa");

            return connect;
        } catch (Exception e) {
            System.out.println("sai ten database hoac pass");
        }
        return connect;
    }
}
