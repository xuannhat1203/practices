package presentation;

import business.modal.Department;
import business.modal.Employee;
import business.service.Employee.EmployeeServicess;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static validate.validateDepartment.sc;

public class EmployeeMenu {
    public static void menuEmployee() {
        int choice;
        do {
            System.out.println("\n====== QUẢN LÝ NHÂN VIÊN ======");
            System.out.println("1. Xem danh sách nhân viên (phân trang)");
            System.out.println("2. Thêm nhân viên");
            System.out.println("3. Cập nhật thông tin nhân viên");
            System.out.println("4. Xóa nhân viên (INACTIVE)");
            System.out.println("5. Tìm kiếm nhân viên theo tên");
            System.out.println("6. Tìm kiếm nhân viên theo khoảng tuổi");
            System.out.println("7. Sắp xếp nhân viên theo lương giảm dần");
            System.out.println("8. Sắp xếp nhân viên theo tên tăng dần");
            System.out.println("9. Quay lại menu chính");
            System.out.print("Nhập lựa chọn của bạn: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    displayEmployeesWithPaging();
                    break;
                case 2:
                    addEmployee();
                    break;
                case 3:
                    updateEmployee();
                    break;
                case 4:
                    deleteEmployee();
                    break;
                case 5:
                    searchEmployeeByName();
                    break;
                case 6:
                    searchEmployeeByAgeRange();
                    break;
                case 7:
                    sortBySalaryDesc();
                    break;
                case 8:
                    sortByNameAsc();
                    break;
                case 9:
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }
        } while (true);
    }
    public static void displayEmployeesWithPaging(){
        EmployeeServicess employeeServicess = new EmployeeServicess();
        int page = 1;
        int size = 5;
        while (true) {
            List<Employee> employees = employeeServicess.getEmployeeWithPagination(page, size);
            if (employees.isEmpty()) {
                System.out.println("Không có phòng ban nào ở trang này.");
            } else {
                System.out.println("\n--- Trang " + page + " ---");
                for (Employee employee : employees) {
                    System.out.println(employee.toString());
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
    public static void addEmployee() {
        EmployeeServicess employeeServicess = new EmployeeServicess();
        System.out.println("Nhập thông tin nhân viên mới");
        Employee employee = new Employee();
        employee.inputData();
        if (employeeServicess.addEmployee(employee)) {
            System.out.println("Thêm nhân viên thành công");
        }else{
            System.out.println("Thêm nhân viên không thành công");
        }
    }
    public static void updateEmployee() {
        EmployeeServicess employeeServicess = new EmployeeServicess();
        System.out.println("Nhập mã nhân viên cần cập nhật: ");
        String id = sc.nextLine().trim();
        List<Employee> employees = employeeServicess.getAllEmployee();
        boolean found = false;
        for (Employee e : employees) {
            if (e.getEmployeeId().equalsIgnoreCase(id)) {
                System.out.println("Thông tin hiện tại:");
                System.out.println(e);
                System.out.println("\nNhập thông tin mới:");
                Employee updatedEmployee = new Employee();
                updatedEmployee.setEmployeeId(e.getEmployeeId());
                System.out.print("Nhập họ tên đầy đủ: ");
                updatedEmployee.setFullName(sc.nextLine().trim());
                System.out.print("Nhập email: ");
                updatedEmployee.setEmail(sc.nextLine().trim());
                System.out.print("Nhập số điện thoại: ");
                updatedEmployee.setPhoneNumber(sc.nextLine().trim());
                System.out.print("Nhập giới tính (MALE/FEMALE/OTHER): ");
                String genderStr = sc.nextLine().trim().toUpperCase();
                updatedEmployee.setGender(Employee.Gender.valueOf(genderStr));
                System.out.print("Nhập cấp bậc lương (số nguyên > 0): ");
                updatedEmployee.setSalaryLevel(sc.nextInt());
                System.out.print("Nhập lương: ");
                updatedEmployee.setSalary(BigDecimal.valueOf(sc.nextDouble()));
                sc.nextLine();
                System.out.print("Nhập ngày sinh (dd/MM/yyyy): ");
                updatedEmployee.setBirthDate(Date.valueOf(sc.nextLine().trim()));
                System.out.print("Nhập địa chỉ: ");
                updatedEmployee.setAddress(sc.nextLine().trim());
                System.out.print("Nhập trạng thái (ACTIVE/INACTIVE/ONLEAVE/POLICYLEAVE): ");
                updatedEmployee.setStatus(Employee.Status.valueOf(sc.nextLine().trim()));
                System.out.print("Nhập mã phòng ban (số nguyên > 0): ");
                updatedEmployee.setDepartmentId(sc.nextInt());
                sc.nextLine();
                if (employeeServicess.updateEmployee(updatedEmployee)) {
                    System.out.println("Cập nhật nhân viên thành công.");
                } else {
                    System.out.println("Cập nhật thất bại.");
                }

                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Không tìm thấy nhân viên với mã: " + id);
        }
    }

    public static void deleteEmployee() {
        EmployeeServicess employeeServicess = new EmployeeServicess();
        System.out.println("Nhập mã nhân viên cần xóa (INACTIVE): ");
        String id = sc.nextLine().trim();
        List<Employee> employees = employeeServicess.getAllEmployee();
        boolean found = false;
        for (Employee e : employees) {
            if (e.getEmployeeId().equalsIgnoreCase(id)) {
                if (employeeServicess.deleteEmployee(id)) {
                    System.out.println("Đã đặt trạng thái INACTIVE cho nhân viên.");
                } else {
                    System.out.println("Không thể cập nhật trạng thái.");
                }
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Không tìm thấy nhân viên với mã: " + id);
        }
    }
    public static void searchEmployeeByName(){
        EmployeeServicess employeeServicess = new EmployeeServicess();
        System.out.println("Nhập tên nhân viên cần tìm: ");
        String name = sc.nextLine().trim();
        List<Employee> employees = employeeServicess.get_employee_by_name(name);

        if (employees.isEmpty()) {
            System.out.println("Không tìm thấy nhân viên nào.");
        } else {
            for (Employee e : employees) {
                System.out.println(e.toString());
            }
        }
    }
    public static void searchEmployeeByAgeRange(){
        System.out.println("Nhập tuổi nhỏ nhất: ");
        int min = sc.nextInt();
        System.out.println("Nhập tuổi lớn nhất: ");
        int max = sc.nextInt();
        sc.nextLine();
        EmployeeServicess employeeServicess = new EmployeeServicess();
        List<Employee> employees = employeeServicess.get_employee_by_age(min, max);

        if (employees.isEmpty()) {
            System.out.println("Không tìm thấy nhân viên nào trong khoảng tuổi.");
        } else {
            for (Employee e : employees) {
                System.out.println(e.toString());
            }
        }
    }
    public static void sortBySalaryDesc(){
        EmployeeServicess employeeServicess = new EmployeeServicess();
        List<Employee> employeeList = employeeServicess.sort_employee_by_salary_desc();
        if (employeeList.isEmpty()) {
            System.out.println("Không có nhân viên nào.");
        } else {
            for (Employee e : employeeList) {
                System.out.println(e.toString());
            }
        }
    }
    public static void sortByNameAsc(){
        EmployeeServicess employeeServicess = new EmployeeServicess();
        List<Employee> employeeList = employeeServicess.sort_employee_by_name_asc();

        if (employeeList.isEmpty()) {
            System.out.println("Không có nhân viên nào.");
        } else {
            for (Employee e : employeeList) {
                System.out.println(e.toString());
            }
        }
    }

}
