/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilities;
<<<<<<< HEAD
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
/**
 *
 * @author Admin
=======

import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author ADMIN
>>>>>>> 8a5cbe30e6b37ccf6ae2fa898d1531086e4a8de6
 */
public class DBConnect {

    private static Connection con = null;
    private static PreparedStatement st = null;
    public static final String url = "jdbc:sqlserver://localhost:1433;"
            + "DatabaseName=Toto;encrypt=true;trustServerCertificate=true";

    public static Connection getConnect() {
        Connection connect = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (Exception e) {
            System.out.println("chua co driver");

        }
        try {
<<<<<<< HEAD
            connect = DriverManager.getConnection(url, "sa", "sa");
=======
            connect = DriverManager.getConnection(url, "SA", "18101999");
>>>>>>> 8a5cbe30e6b37ccf6ae2fa898d1531086e4a8de6
            return connect;
        } catch (Exception e) {
            System.out.println("sai ten database hoac pass");
        }
        return connect;
    }
}
