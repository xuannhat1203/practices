package presentation;

import static validate.validateDepartment.sc;

public class StatisticalMenu {
    public static void menuStatistic() {
        int choice;
        do {
            System.out.println("\n====== MENU THỐNG KÊ ======");
            System.out.println("1. Số lượng nhân viên theo từng phòng ban");
            System.out.println("2. Tổng số nhân viên của toàn hệ thống");
            System.out.println("3. Phòng ban có nhiều nhân viên nhất");
            System.out.println("4. Phòng ban có tổng lương cao nhất");
            System.out.println("5. Quay lại menu chính");
            System.out.print("Nhập lựa chọn của bạn: ");
            choice = sc.nextInt();
            sc.nextLine(); // clear buffer

            switch (choice) {
                case 1:
//                    statisticService.countEmployeesByDepartment();
                    break;
                case 2:
//                    statisticService.totalEmployeesInSystem();
                    break;
                case 3:
//                    statisticService.departmentWithMostEmployees();
                    break;
                case 4:
//                    statisticService.departmentWithHighestSalary();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ!");
                    break;
            }
        } while (true);
    }
}
