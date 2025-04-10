package business.modal;

import validate.validateDepartment;

public class Department {
    private int departmentId; // thêm dòng này
    private String departmentName;
    private String departmentDescription;
    private boolean departmentStatus;

    public Department() {
    }

    public Department(int departmentId, String departmentName, String departmentDescription, boolean departmentStatus) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.departmentDescription = departmentDescription;
        this.departmentStatus = departmentStatus;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getDepartmentDescription() {
        return departmentDescription;
    }

    public void setDepartmentDescription(String departmentDescription) {
        this.departmentDescription = departmentDescription;
    }

    public boolean isDepartmentStatus() {
        return departmentStatus;
    }

    public void setDepartmentStatus(boolean departmentStatus) {
        this.departmentStatus = departmentStatus;
    }
    public void inputData(){
        this.departmentName = validateDepartment.validateDepartmentName("Nhập tên phòng ban");
        this.departmentDescription = validateDepartment.validateDepartmentDescription("Nhập mô tả phòng ban");
        this.departmentStatus = validateDepartment.validateDepartmentStatus("Nhập trạng thái của phòng ban");
    }
    @Override
    public String toString() {
        return String.format("ID: %d | Tên: %s | Mô tả: %s | Trạng thái: %s",
                departmentId, departmentName, departmentDescription,
                departmentStatus ? "Hoạt động" : "Không hoạt động");
    }

}
