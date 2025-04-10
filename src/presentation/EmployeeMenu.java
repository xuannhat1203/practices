package presentation;

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
//                    displayEmployeesWithPaging();
                    break;
                case 2:
//                    addEmployee();
                    break;
                case 3:
//                    updateEmployee();
                    break;
                case 4:
//                    deleteEmployee();
                    break;
                case 5:
//                    searchEmployeeByName();
                    break;
                case 6:
//                    searchEmployeeByAgeRange();
                    break;
                case 7:
//                    employeeService.sortBySalaryDesc();
                    break;
                case 8:
//                    employeeService.sortByNameAsc();
                    break;
                case 9:
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }
        } while (true);
    }
}
