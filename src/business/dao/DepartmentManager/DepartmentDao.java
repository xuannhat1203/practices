package business.dao.DepartmentManager;

import business.modal.Department;

import java.util.List;

public interface DepartmentDao<T> {
    List<T> getAllDepartments();
    List<T> getDepartmentsByPage(int page, int size);
    boolean addNewDepartment(Department department);
    boolean updateDepartment(Department department);
    boolean deleteDepartment(int id);
    Department getDepartmentByName(String departmentName);
}
