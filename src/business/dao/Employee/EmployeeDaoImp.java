package business.dao.Employee;

import business.config.ConnectionDB;
import business.modal.*;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
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
                        rs.getDate("birth_date"),
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

    @Override
    public List<Employee> getEmployeeWithPagination(int page, int size) {
        Connection con = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        List<Employee> employeeList = new ArrayList<>();
        try {
            int offset = (page - 1) * size;
            con = ConnectionDB.openConnection();
            cstmt = con.prepareCall("{call get_employee_by_page(?,?)}");
            cstmt.setInt(1, size);
            cstmt.setInt(2, offset);
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
                        rs.getDate("birth_date"),
                        rs.getString("address"),
                        Employee.Status.valueOf(rs.getString("status").toLowerCase()),
                        rs.getInt("department_id")
                );
                employeeList.add(newEmployee);
            }
            return employeeList;
        }catch (Exception e) {
            System.out.println("Lỗi: " + e.getMessage());
        }
        return null;
    }

    @Override
    public boolean addEmployee(Employee employee) {
        Connection con = null;
        CallableStatement cstmt = null;
        try {
            con = ConnectionDB.openConnection();
            cstmt = con.prepareCall("{call add_new_employee(?,?,?,?,?,?,?,?,?,?,?)}");
            cstmt.setString(1, employee.getEmployeeId());
            cstmt.setString(2, employee.getFullName());
            cstmt.setString(3, employee.getEmail());
            cstmt.setString(4, employee.getPhoneNumber());
            cstmt.setString(5, employee.getGender().toString());
            cstmt.setInt(6, employee.getSalaryLevel());
            cstmt.setBigDecimal(7, employee.getSalary());
            cstmt.setDate(8,employee.getBirthDate());
            cstmt.setString(9, employee.getAddress());
            cstmt.setString(10, employee.getStatus().toString());
            cstmt.setInt(11, employee.getDepartmentId());
            cstmt.execute();
            return true;
        } catch (Exception e) {
            System.out.println("Lỗi : " + e.getMessage());
        }
        return false;
    }
    public boolean updateEmployee(Employee employee) {
        Connection con = null;
        CallableStatement cstmt = null;
        try {
            con = ConnectionDB.openConnection();
            cstmt = con.prepareCall("{call update_employee(?,?,?,?,?,?,?,?,?,?,?)}");
            cstmt.setString(1, employee.getEmployeeId());
            cstmt.setString(2, employee.getFullName());
            cstmt.setString(3, employee.getEmail());
            cstmt.setString(4, employee.getPhoneNumber());
            cstmt.setString(5, employee.getGender().toString());
            cstmt.setInt(6, employee.getSalaryLevel());
            cstmt.setBigDecimal(7, employee.getSalary());
            cstmt.setDate(8, employee.getBirthDate());
            cstmt.setString(9, employee.getAddress());
            cstmt.setString(10, employee.getStatus().toString());
            cstmt.setInt(11, employee.getDepartmentId());
            cstmt.execute();
            return true;
        } catch (Exception e) {
            System.out.println("Lỗi: " + e.getMessage());
        }
        return false;
    }
    public boolean deleteEmployee(String employeeId) {
        Connection con = null;
        CallableStatement cstmt = null;
        try {
            con = ConnectionDB.openConnection();
            cstmt = con.prepareCall("{call set_employee_inactive(?)}");
            cstmt.setString(1, employeeId);
            cstmt.execute();
            return true;
        } catch (Exception e) {
            System.out.println("Lỗi: " + e.getMessage());
        }
        return false;
    }

    @Override
    public List<Employee> get_employee_by_name(String name) {
        Connection con = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        List<Employee> employeeList = new ArrayList<>();
        try {
            con = ConnectionDB.openConnection();
            cstmt = con.prepareCall("{call search_employee_by_name(?)}");
            cstmt.setString(1, name);
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
                        rs.getDate("birth_date"),
                        rs.getString("address"),
                        Employee.Status.valueOf(rs.getString("status").toLowerCase()),
                        rs.getInt("department_id")
                );
                employeeList.add(newEmployee);
            }
        } catch (SQLException e) {
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
    @Override
    public List<Employee> get_employee_by_age(int min, int max) {
        Connection con = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        List<Employee> employeeList = new ArrayList<>();
        try {
            con = ConnectionDB.openConnection();
            cstmt = con.prepareCall("{call search_employee_by_age_range(?,?)}");
            cstmt.setInt(1, min);
            cstmt.setInt(2, max);
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
                        rs.getDate("birth_date"),
                        rs.getString("address"),
                        Employee.Status.valueOf(rs.getString("status").toLowerCase()),
                        rs.getInt("department_id")
                );
                employeeList.add(newEmployee);
            }
        } catch (SQLException e) {
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

    @Override
    public List<Employee> sort_employee_by_salary_desc() {
        Connection con = null;
        CallableStatement cstmt = null;
        List<Employee> sortedEmployeeList = new ArrayList<>();

        try {
            con = ConnectionDB.openConnection();
            cstmt = con.prepareCall("{call sort_employees_by_salary_desc()}");
            ResultSet rs = cstmt.executeQuery();

            while (rs.next()) {
                Employee newEmployee = new Employee(
                        rs.getString("employee_id"),
                        rs.getString("full_name"),
                        rs.getString("email"),
                        rs.getString("phone_number"),
                        Employee.Gender.valueOf(rs.getString("gender").toUpperCase()),
                        rs.getInt("salary_level"),
                        rs.getBigDecimal("salary"),
                        rs.getDate("birth_date"),
                        rs.getString("address"),
                        Employee.Status.valueOf(rs.getString("status").toUpperCase()),
                        rs.getInt("department_id")
                );
                sortedEmployeeList.add(newEmployee);
            }
        } catch (Exception e) {
            System.out.println("Lỗi: " + e.getMessage());
        }
        return sortedEmployeeList;
    }
    @Override
    public List<Employee> sort_employee_by_name_asc() {
        Connection con = null;
        CallableStatement cstmt = null;
        List<Employee> sortedEmployeeList = new ArrayList<>();

        try {
            con = ConnectionDB.openConnection();
            cstmt = con.prepareCall("{call sort_employees_by_name_asc()}");
            ResultSet rs = cstmt.executeQuery();

            while (rs.next()) {
                Employee newEmployee = new Employee(
                        rs.getString("employee_id"),
                        rs.getString("full_name"),
                        rs.getString("email"),
                        rs.getString("phone_number"),
                        Employee.Gender.valueOf(rs.getString("gender")),
                        rs.getInt("salary_level"),
                        rs.getBigDecimal("salary"),
                        rs.getDate("birth_date"),
                        rs.getString("address"),
                        Employee.Status.valueOf(rs.getString("status")),
                        rs.getInt("department_id")
                );
                sortedEmployeeList.add(newEmployee);
            }
        } catch (Exception e) {
            System.out.println("Lỗi: " + e.getMessage());
        }
        return sortedEmployeeList;
    }

    @Override
    public List<CountEmployeebyDequartment> array_employee_by_dequartment() {
        Connection con = null;
        CallableStatement cstmt = null;
        List<CountEmployeebyDequartment> sortedEmployeeList = new ArrayList<>();
        try {
            con = ConnectionDB.openConnection();
            cstmt = con.prepareCall("{call calculator_employee_group_by_department()}");
            ResultSet rs = cstmt.executeQuery();
            while (rs.next()) {
                CountEmployeebyDequartment newEmployee = new CountEmployeebyDequartment(
                        rs.getInt("department_id"),
                        rs.getString("department_name"),
                        rs.getInt("employee_count")
                );
                sortedEmployeeList.add(newEmployee);
            }
        } catch (Exception e) {
            System.out.println("Lỗi: " + e.getMessage());
        }
        return sortedEmployeeList;
    }

    @Override
    public int totalEmployee() {
        Connection con = null;
        CallableStatement cstmt = null;
        int totalEmployee = 0;
        try {
            con = ConnectionDB.openConnection();
            cstmt = con.prepareCall("{call count_employee()}");
            ResultSet rs = cstmt.executeQuery();
            while (rs.next()) {
                totalEmployee = rs.getInt(1);
            }
            return totalEmployee;
        }catch (Exception e) {
            System.out.println("Lỗi: " + e.getMessage());
        }
        return totalEmployee;
    }

    @Override
    public Most_emloyee get_most_department_employee() {
        Connection con = null;
        CallableStatement cstmt = null;
        Most_emloyee mostDepartment = new Most_emloyee();
        try {
            con = ConnectionDB.openConnection();
            cstmt = con.prepareCall("{call most_staffed_office()}");
            ResultSet rs = cstmt.executeQuery();
            while (rs.next()) {
                mostDepartment.setDepartmentName(rs.getString("Dapartment_name"));
                mostDepartment.setCountEmployee(rs.getInt("count_employee"));
            }
            return mostDepartment;
        } catch (Exception e) {
            System.out.println("Lỗi: " + e.getMessage());
        }
        return mostDepartment;
    }

    @Override
    public Most_salary get_most_department_salary() {
        Connection con = null;
        CallableStatement cstmt = null;
        Most_salary mostDepartment = new Most_salary();
        try {
            con = ConnectionDB.openConnection();
            cstmt = con.prepareCall("{call most_salary_department()}");
            ResultSet rs = cstmt.executeQuery();
            while (rs.next()) {
                mostDepartment.setDepartmentName(rs.getString("DepartmentName"));
                mostDepartment.setSalary(rs.getBigDecimal("totalSalary"));
            }
            return mostDepartment;
        } catch (Exception e) {
            System.out.println("Lỗi: " + e.getMessage());
        }
        return mostDepartment;
    }
}
