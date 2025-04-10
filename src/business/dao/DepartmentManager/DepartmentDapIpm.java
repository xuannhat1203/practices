package business.dao.DepartmentManager;

import business.config.ConnectionDB;
import business.modal.Department;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DepartmentDapIpm implements DepartmentDao {
    @Override
    public List<Department> getAllDepartments() {
        List<Department> departments = new ArrayList<>();
        try (Connection conn = ConnectionDB.openConnection();
             CallableStatement cs = conn.prepareCall("{call get_all_department()}")) {

            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                Department department = new Department();
                department.setDepartmentId(rs.getInt("dp_id"));
                department.setDepartmentName(rs.getString("dp_name"));
                department.setDepartmentDescription(rs.getString("dp_description"));
                department.setDepartmentStatus(rs.getBoolean("dp_status"));
                departments.add(department);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return departments;
    }
    @Override
    public List<Department> getDepartmentsByPage(int page, int size) {
        Connection conn2 = null;
        CallableStatement cs = null;
        ResultSet rs = null;
        List<Department> departments = new ArrayList<>();
        try {
            conn2 = ConnectionDB.openConnection();
            int offset = (page - 1) * size;
            cs = conn2.prepareCall("{call get_department_by_page(?, ?)}");
            cs.setInt(1, size);
            cs.setInt(2, offset);
            rs = cs.executeQuery();
            while (rs.next()) {
                Department department = new Department(
                        rs.getInt("dp_id"),
                        rs.getString("dp_name"),
                        rs.getString("dp_description"),
                        rs.getBoolean("dp_status")
                );
                departments.add(department);
            }
            return departments;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return departments;
    }

    @Override
    public boolean addNewDepartment(Department department) {
        Connection conn = null;
        CallableStatement cs = null;
        try {
            List<Department> existing = getAllDepartments();
           if (!existing.isEmpty()) {
               for (Department d : existing) {
                   if (d.getDepartmentName() != null && department.getDepartmentName() != null &&
                           d.getDepartmentName().equalsIgnoreCase(department.getDepartmentName())) {
                       System.out.println("Không thể thêm: Tên phòng ban đã tồn tại.");
                       return false;
                   }
               }
           }
            conn = ConnectionDB.openConnection();
            cs = conn.prepareCall("{call add_new_department(?, ?, ?)}");
            cs.setString(1, department.getDepartmentName());
            cs.setString(2, department.getDepartmentDescription());
            cs.setBoolean(3, department.isDepartmentStatus());
            int rowsAffected = cs.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateDepartment(Department department) {
        Connection conn = null;
        CallableStatement cs = null;
        try {
            conn = ConnectionDB.openConnection();
            cs = conn.prepareCall("{call update_department(?,?, ?, ?)}");
            cs.setInt(1, department.getDepartmentId());
            cs.setString(2, department.getDepartmentName());
            cs.setString(3, department.getDepartmentDescription());
            cs.setBoolean(4, department.isDepartmentStatus());
            int rowsAffected = cs.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean deleteDepartment(int id) {
        Connection conn = null;
        CallableStatement cs = null;
        try {
            conn = ConnectionDB.openConnection();
            cs = conn.prepareCall("{call delete_department(?)}");
            cs.setInt(1, id);
            int rowsAffected = cs.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public Department getDepartmentByName(String departmentName) {
        Connection con = null;
        CallableStatement cs = null;
        ResultSet rs = null;
        try {
            con = ConnectionDB.openConnection();
            cs = con.prepareCall("{call get_department_by_name(?)}");
            cs.setString(1, departmentName);
            rs = cs.executeQuery();
            if (rs.next()) {
                Department department = new Department();
                department.setDepartmentId(rs.getInt("dp_id"));
                department.setDepartmentName(rs.getString("dp_name"));
                department.setDepartmentDescription(rs.getString("dp_description"));
                department.setDepartmentStatus(rs.getBoolean("dp_status"));
                return department;
            }
        } catch (Exception e) {
            System.out.println("Lỗi: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (cs != null) {
                    cs.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }
}
