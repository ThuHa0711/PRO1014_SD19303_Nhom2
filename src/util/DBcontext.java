/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBcontext {

    public static String USER = "sa";
    public static String PASSWORD = "48691412ha";
    public static String URL = "jdbc:sqlserver://localhost:1433;databaseName=QLBanAo;encrypt=true;trustServerCertificate=true;";

    static {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBcontext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Connection getConnection() {
        Connection cn = null;
        try {
            cn = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException ex) {
            Logger.getLogger(DBcontext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cn;
    }

    public static void main(String[] args) {
        Connection  cn = getConnection();
        if (cn!=null) {
            System.out.println("Kết nối thành công");
        } else {
            System.out.println("Lỗi kết nối");
        }
    }

}

