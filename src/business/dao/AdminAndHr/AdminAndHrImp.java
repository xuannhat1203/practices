package business.dao.AdminAndHr;

import business.config.ConnectionDB;
import business.modal.Account;
import business.modal.HrOrAdmin;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
public class AdminAndHrImp implements AdminAndHrDao {
    public static Account currentUser;
    public static void setCurrentUser(Account account) {
        currentUser = account;
    }
    @Override
    public boolean login(String username, String password) {
        if (currentUser != null) {
            System.out.println("Đã có người đăng nhập: " + currentUser.getUsername() + ". Vui lòng đăng xuất trước.");
            return false;
        }

        Connection conn = null;
        CallableStatement cs = null;
        ResultSet rs = null;
        try {
            conn = ConnectionDB.openConnection();
            cs = conn.prepareCall("{call isLogin(?, ?)}");
            cs.setString(1, username);
            cs.setString(2, password);
            rs = cs.executeQuery();
            if (rs.next()) {
                Account account = new Account();
                account.setUsername(rs.getString(1));
                account.setPassword(rs.getString(2));
                account.setStatus(rs.getBoolean(3));
                account.setRole(HrOrAdmin.valueOf(rs.getString(4)));
                setCurrentUser(account);
                System.out.println("Đăng nhập thành công");
                return true;
            } else {
                System.out.println("Sai tên tài khoản hoặc mật khẩu");
            }
        } catch (SQLException sql) {
            System.out.println("Lỗi truy vấn: " + sql.getMessage());
        } catch (Exception e) {
            System.out.println("Lỗi hệ thống: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (cs != null) cs.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.out.println("Lỗi đóng kết nối: " + e.getMessage());
            }
        }
        return false;
    }
    @Override
    public boolean logout() {
        if (currentUser != null) {
            System.out.println("Tạm biệt " + currentUser.getUsername());
            currentUser = null;
            return true;
        } else {
            System.out.println("Chưa đăng nhập!");
        }
        return false;
    }
}

