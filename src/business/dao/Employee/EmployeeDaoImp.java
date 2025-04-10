package business.dao.Employee;

import business.config.ConnectionDB;
import business.modal.Employee;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDaoImp implements EmployeeDao<Employee> {

    @Override
    public List<Employee> getAllEmployee() {
        Connection con = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        List<Employee> employeeList = new ArrayList<>();
        try {
            con = ConnectionDB.openConnection();
            cstmt = con.prepareCall("{call get_all_employees()}");
            rs = cstmt.executeQuery();
            while (rs.next()) {
                Employee newEmployee = new Employee(
                        rs.getString("employee_id"),
                        rs.getString("full_name"),
                        rs.getString("email"),
                        rs.getString("phone_number"),
                        Employee.Gender.valueOf(rs.getString("gender").toLowerCase()),
                        rs.getInt("salary_level"),
                        rs.getBigDecimal("salary"),
                        rs.getDate("birth_date").toLocalDate(),
                        rs.getString("address"),
                        Employee.Status.valueOf(rs.getString("status").toLowerCase()),
                        rs.getInt("department_id")
                );
                employeeList.add(newEmployee);
            }
            return employeeList;
        } catch (Exception e) {
            System.out.println("Lỗi: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (cstmt != null) cstmt.close();
                if (con != null) con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return employeeList;
    }

}
