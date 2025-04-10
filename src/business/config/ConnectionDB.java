package business.config;

import java.sql.*;

public class ConnectionDB {
    public static String URL = "jdbc:mysql://localhost:3306/jdbc_practice";
    public static String USER = "root";
    public static String PASS = "123456$";
    public static Connection openConnection() {
        Connection con = null;
        CallableStatement cs = null;
        try {
            con = DriverManager.getConnection(URL, USER, PASS);
            return con;
        }catch (SQLException sql){
            System.out.println("Có lỗi trong quá trình kết nối với database");
        } catch (Exception e) {
            System.out.println("Có lỗi không xác định trong quá trình kết nối với database");
        }
        return con;
    }
    public static void closeConnection(Connection con,CallableStatement cstm) {
        if (con != null) {
            try {
                con.close();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        if (cstm != null) {
            try {
                cstm.close();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
