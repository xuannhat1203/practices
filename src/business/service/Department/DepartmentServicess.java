package business.service.Department;

import business.dao.DepartmentManager.DepartmentDao;
import business.dao.DepartmentManager.DepartmentDapIpm;
import business.modal.Department;

import java.util.List;

public class DepartmentServicess implements DepartmentServices{
    private final DepartmentDao departmentDao;
    public DepartmentServicess() {
        departmentDao = new DepartmentDapIpm();
    }
    @Override
    public List<Department> getAllDepartments() {
        return departmentDao.getAllDepartments();
    }

    @Override
    public List<Department> getDepartmentsByPage(int page, int size) {
       return departmentDao.getDepartmentsByPage(page, size);
    }

    @Override
    public boolean addNewDepartment(Department department) {
        return departmentDao.addNewDepartment(department);
    }

    @Override
    public boolean updateDepartment(Department department) {
        return departmentDao.updateDepartment(department);
    }

    @Override
    public boolean deleteDepartment(int id) {
        return departmentDao.deleteDepartment(id);
    }

    @Override
    public Department getDepartmentByName(String departmentName) {
        return departmentDao.getDepartmentByName(departmentName);
    }

}
