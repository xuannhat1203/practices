package presentation;

import business.modal.Department;
import business.modal.Employee;
import business.service.Department.DepartmentServicess;
import business.service.Employee.EmployeeServicess;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static validate.validateDepartment.sc;

public class DepartmentMenu {
    public static void menuDepartment() {
        int choice;
        do {
            System.out.println("\n====== QUẢN LÝ PHÒNG BAN ======");
            System.out.println("1. Xem danh sách phòng ban (phân trang)");
            System.out.println("2. Thêm mới phòng ban");
            System.out.println("3. Cập nhật phòng ban");
            System.out.println("4. Xóa phòng ban");
            System.out.println("5. Tìm kiếm phòng ban theo tên");
            System.out.println("6. Quay lại menu chính");
            System.out.print("Nhập lựa chọn của bạn: ");
            choice = sc.nextInt();
            sc.nextLine(); // clear buffer

            switch (choice) {
                case 1:
                    displayDepartmentWithPage();
                    break;
                case 2:
                    addNewDepartment();
                    break;
                case 3:
                    updateDepartment();
                    break;
                case 4:
                    deleteDepartment();
                    break;
                case 5:
                    searchDepartmentByName();
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ!");
                    break;
            }
        } while (true);
    }

    public static void displayDepartmentWithPage() {
        DepartmentServicess service = new DepartmentServicess();
        int page = 1;
        int size = 5;
        while (true) {
            List<Department> departments = service.getDepartmentsByPage(page, size);
            if (departments.isEmpty()) {
                System.out.println("Không có phòng ban nào ở trang này.");
            } else {
                System.out.println("\n--- Trang " + page + " ---");
                for (Department department : departments) {
                    System.out.println(department);
                }
            }
            System.out.println("\n[1] Trang sau | [2] Trang trước | [3] Thoát");
            System.out.print("Chọn hành động: ");
            String choice = sc.nextLine().trim().toLowerCase();

            if (choice.equals("1")) {
                page++;
            } else if (choice.equals("2")) {
                if (page > 1) {
                    page--;
                } else System.out.println("Đang ở trang đầu tiên.");
            } else if (choice.equals("3")) {
                break;
            } else {
                System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }

    public static void addNewDepartment() {
        DepartmentServicess service = new DepartmentServicess();
        System.out.print("Nhập số lượng phòng ban muốn thêm: ");
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            Department department = new Department();
            department.inputData();
            if (service.addNewDepartment(department)) {
                System.out.println("Thêm phòng ban thành công");
            }
        }
    }

    public static void updateDepartment() {
        DepartmentServicess service = new DepartmentServicess();
        System.out.print("Nhập mã phòng ban muốn cập nhật: ");
        int n = sc.nextInt();
        sc.nextLine();
        List<Department> departmentList = service.getAllDepartments();
        Optional<Department> isCheck = departmentList.stream()
                .filter(d -> d.getDepartmentId()== n)
                .findFirst();
        if (isCheck.isPresent()) {
            Department dp = isCheck.get();
            System.out.println("Nhập thông tin mới cho phòng ban (ID: " + dp.getDepartmentId() + "):");
            System.out.print("Tên phòng ban mới: ");
            String newName = sc.nextLine();
            System.out.print("Mô tả mới: ");
            String newDesc = sc.nextLine();
            System.out.print("Trạng thái (true/false): ");
            boolean newStatus = sc.nextBoolean();
            sc.nextLine();
            dp.setDepartmentName(newName);
            dp.setDepartmentDescription(newDesc);
            dp.setDepartmentStatus(newStatus);
            if (service.updateDepartment(dp)) {
                System.out.println("Cập nhật phòng ban thành công.");
            } else {
                System.out.println("Cập nhật thất bại.");
            }
        } else {
            System.out.println("⚠Không tìm thấy phòng ban với mã: " + n);
        }
    }
    public static void deleteDepartment() {
        EmployeeServicess employeeService = new EmployeeServicess();
        DepartmentServicess departmentService = new DepartmentServicess();
        System.out.print("Nhập ID phòng ban muốn xóa: ");
        int id = sc.nextInt();
        sc.nextLine();
        List<Department> departmentList = departmentService.getAllDepartments();
        Optional<Department> department = departmentList.stream()
                .filter(d -> d.getDepartmentId() == id)
                .findFirst();
        if (!department.isPresent()) {
            System.out.println("Không tồn tại phòng ban với ID: " + id);
            return;
        }
        List<Employee> employeeList = employeeService.getAllEmployee();
        boolean hasEmployee = employeeList.stream().anyMatch(e -> e.getDepartmentId() == id);
        if (hasEmployee) {
            System.out.println("Không thể xóa phòng ban vì vẫn còn nhân viên trong đó.");
        } else {
            boolean isDeleted = departmentService.deleteDepartment(id);
            if (isDeleted) {
                System.out.println("Xóa phòng ban thành công.");
            } else {
                System.out.println("Xóa phòng ban thất bại.");
            }
        }
    }
    public static void searchDepartmentByName() {
        System.out.print("Nhập tên department cần tìm: ");
        String name = sc.nextLine();
        DepartmentServicess service = new DepartmentServicess();
        Department finDepartment = service.getDepartmentByName(name);
        if (finDepartment != null) {
            System.out.println("ID: " + finDepartment.getDepartmentId());
            System.out.println("Name: " + finDepartment.getDepartmentName());
            System.out.println("Description: " + finDepartment.getDepartmentDescription());
            System.out.println("Status: " + finDepartment.isDepartmentStatus());
        } else {
            System.out.println("Không tìm thấy phòng ban có tên: " + name);
        }
    }
}
