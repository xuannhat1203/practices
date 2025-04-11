package validate;

import business.modal.Employee;
import business.service.Employee.EmployeeServicess;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class validateEmployee {
    public static Scanner sc = new Scanner(System.in);
    public enum Gender {
        male, female, other
    }

    public enum Status {
        active, inactive, onleave, policyleave
    }

    public static String validateEmployeeId(String message) {
        EmployeeServicess service = new EmployeeServicess();
        List<Employee> employeeList = service.getAllEmployee();
        System.out.println(message);
        String id;
        while (true) {
            id = sc.nextLine().trim();
            if (id.equals("")) {
                System.out.println("Không được để trống mã nhân viên, vui lòng nhập lại.");
            } else if (!id.matches("^E\\d{4}$")) {
                System.out.println("Mã nhân viên phải có định dạng E#### (E và 4 chữ số). Vui lòng nhập lại.");
            } else {
                String finalId = id;
                boolean isDuplicate = employeeList.stream()
                        .anyMatch(e -> e.getEmployeeId().equals(finalId));
                if (isDuplicate) {
                    System.out.println("Mã nhân viên đã tồn tại, vui lòng nhập mã khác.");
                } else {
                    return id;
                }
            }
        }
    }


    public static String validateEmployeeName(String message) {
        System.out.println(message);
        String name;
        while (true) {
            name = sc.nextLine();
            if (name.equals("")) {
                System.out.println("Tên nhân viên không được để trống");
                System.out.println("Vui lòng nhập lại");
            } else if (name.length() < 15 || name.length() > 150) {
                System.out.println("Tên nhân viên không được phải gồm 15 -> 150 kí tự");
            } else {
                return name;
            }
        }
    }

    public static String validateEmployeeEmail(String message) {
        System.out.println(message);
        String email;
        while (true) {
            email = sc.nextLine();
            if (email.equals("")) {
                System.out.println("Email không được để trống");
                System.out.println("Vui lòng nhập lại");
            } else if (!email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
                System.out.println("Email không đúng định dạng.");
            } else {
                return email;
            }
        }
    }

    public static String validateEmployeePhone(String message) {
        System.out.println(message);
        String phone;
        while (true) {
            phone = sc.nextLine();
            if (phone.equals("")) {
                System.out.println("Số điện thoại không được để trống");
            } else if (!phone.matches("^(03[2-9]|05[6|8|9]|07[0|6-9]|08[1-9]|09[0-9])\\d{7}$")) {
                System.out.println("Số điện thoại không đúng định dạng di động Việt Nam.");
            } else {
                return phone;
            }
        }
    }

    public static Employee.Gender validateGender(String msg) {
        System.out.print(msg);
        try {
            return Employee.Gender.valueOf(sc.nextLine().trim().toLowerCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Giới tính không hợp lệ (male/female/other)");
        }
    }

    public static Employee.Status validateStatus(String msg) {
        System.out.print(msg);
        try {
            return Employee.Status.valueOf(sc.nextLine().trim().toLowerCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Trạng thái không hợp lệ (active/inactive/...)");
        }
    }

    public static int validateSalaryLevel(String message) {
        System.out.println(message);
        while (true) {
            try {
                int level = Integer.parseInt(sc.nextLine());
                if (level > 0) {
                    return level;
                } else {
                    System.out.println("Bậc lương phải lớn hơn 0.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Bậc lương phải là số nguyên.");
            }
        }
    }

    public static BigDecimal validateSalary(String message) {
        System.out.println(message);
        while (true) {
            try {
                BigDecimal salary = new BigDecimal(sc.nextLine());
                if (salary.compareTo(BigDecimal.ZERO) > 0) {
                    return salary;
                } else {
                    System.out.println("Lương phải lớn hơn 0.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Lương phải là số thực.");
            }
        }
    }

    public static LocalDate validateBirthDate(String message) {
        System.out.println(message);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        while (true) {
            try {
                return LocalDate.parse(sc.nextLine(), formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Ngày sinh không hợp lệ. Định dạng đúng: dd/MM/yyyy");
            }
        }
    }

    public static String validateAddress(String message) {
        System.out.println(message);
        String address;
        while (true) {
            address = sc.nextLine().trim();
            if (address.isEmpty()) {
                System.out.println("Địa chỉ không được để trống.");
            } else {
                return address;
            }
        }
    }

}
