package business.dao.Employee;

import business.modal.*;

import java.util.List;

public interface EmployeeDao<T> {
    List<T> getAllEmployee();
    List<T> getEmployeeWithPagination(int page, int size);
    boolean addEmployee(Employee employee);
    boolean updateEmployee(Employee employee);
    boolean deleteEmployee(String id);
    List<Employee> get_employee_by_name(String name);
    List<Employee> get_employee_by_age(int max,int min);
    List<Employee> sort_employee_by_salary_desc();
    List<Employee> sort_employee_by_name_asc();
    List<CountEmployeebyDequartment> array_employee_by_dequartment();
    int totalEmployee();
    Most_emloyee get_most_department_employee();
    Most_salary get_most_department_salary();
}
