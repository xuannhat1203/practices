package business.dao.Employee;

import java.util.List;

public interface EmployeeDao<T> {
    List<T> getAllEmployee();
}
