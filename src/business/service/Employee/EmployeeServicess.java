package business.service.Employee;

import business.dao.Employee.EmployeeDao;
import business.dao.Employee.EmployeeDaoImp;
import business.modal.Employee;

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
}
