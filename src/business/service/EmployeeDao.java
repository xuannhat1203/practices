package business.service;

import java.util.List;

public interface EmployeeDao<T> {
    List<T> getAllEmployee();
}
