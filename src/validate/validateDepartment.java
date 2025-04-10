package validate;

import business.modal.Department;
import business.service.Department.DepartmentServicess;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class validateDepartment {
    public static Scanner sc = new Scanner(System.in);
    public static String validateDepartmentName(String message) {
        DepartmentServicess departmentService = new DepartmentServicess();
        List<Department> listDepartment = departmentService.getAllDepartments();
        System.out.println(message);
        while (true) {
            String departmentName = sc.nextLine();
            departmentName = departmentName.replaceAll("\\p{C}", "").trim();
            if (departmentName.isEmpty()) {
                System.out.println("Không được để trống tên phòng ban.");
            } else {
                String finalDepartmentName = departmentName;
                boolean isDuplicate = listDepartment.stream()
                        .anyMatch(d -> d.getDepartmentName() != null &&
                                d.getDepartmentName().equalsIgnoreCase(finalDepartmentName));

                if (isDuplicate) {
                    System.out.println("Tên phòng ban đã tồn tại. Vui lòng nhập tên khác.");
                } else {
                    return departmentName;
                }
            }
        }
    }
    public static String validateDepartmentDescription(String message) {
        System.out.println(message);
        String departmentDescription;
        while (true) {
            departmentDescription = sc.nextLine().trim();
            if (departmentDescription.isEmpty()) {
                System.out.println("Mô tả không được để trống.");
            } else if (departmentDescription.length() > 255) {
                System.out.println("Mô tả không được vượt quá 255 ký tự.");
            } else {
                return departmentDescription;
            }
        }
    }
    public static Boolean validateDepartmentStatus(String message) {
        System.out.println(message + " (true/false): ");
        while (true) {
            String input = sc.nextLine().trim().toLowerCase();
            if (input.equals("true")) {
                return true;
            } else if (input.equals("false")) {
                return false;
            } else {
                System.out.println("Giá trị trạng thái không hợp lệ. Vui lòng nhập 'true' hoặc 'false'.");
            }
        }
    }
}
