package business.service.Employee;

import business.dao.Employee.EmployeeDao;
import business.dao.Employee.EmployeeDaoImp;
import business.modal.*;

import java.util.List;

public class EmployeeServicess implements EmployeeService {
    public final EmployeeDao<Employee> employeeDao;
    public EmployeeServicess() {
        employeeDao = new EmployeeDaoImp();
    }
    @Override
    public List<Employee> getAllEmployee() {
        return employeeDao.getAllEmployee();
    }

    @Override
    public List<Employee> getEmployeeWithPagination(int page, int size) {
        return employeeDao.getEmployeeWithPagination(page, size);
    }

    @Override
    public boolean addEmployee(Employee employee) {
        return employeeDao.addEmployee(employee);
    }

    @Override
    public boolean updateEmployee(Employee employee) {
        return employeeDao.updateEmployee(employee);
    }

    @Override
    public boolean deleteEmployee(String id) {
        return employeeDao.deleteEmployee(id);
    }

    @Override
    public List<Employee> get_employee_by_name(String name) {
        return employeeDao.get_employee_by_name(name);
    }

    @Override
    public List<Employee> get_employee_by_age(int max, int min) {
        return employeeDao.get_employee_by_age(max,min);
    }

    @Override
    public List<Employee> sort_employee_by_salary_desc() {
        return employeeDao.sort_employee_by_salary_desc();
    }

    @Override
    public List<Employee> sort_employee_by_name_asc() {
        return employeeDao.sort_employee_by_name_asc();
    }

    @Override
    public List<CountEmployeebyDequartment> array_employee_by_dequartment() {
        return employeeDao.array_employee_by_dequartment();
    }

    @Override
    public int totalEmployee() {
        return employeeDao.totalEmployee();
    }

    @Override
    public Most_emloyee get_most_department_employee() {
        return employeeDao.get_most_department_employee();
    }

    @Override
    public Most_salary get_most_department_salary() {
        return employeeDao.get_most_department_salary();
    }
}
