import business.service.AdminAndHr.AdminAndHrService;
import business.service.AdminAndHr.AdminAndHrServiceImp;
import presentation.DepartmentMenu;

import java.util.Scanner;
public class Main {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        do {
            System.out.println("1. Đăng nhập");
            System.out.println("2. Đăng xuất");
            System.out.print("Nhập lựa chọn của bạn: ");
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    if (login()) {
                        MenuManager();
                    } else {
                        System.out.println("Đăng nhập thất bại.");
                    }
                    return;
                case 2:
                    logout();
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ");
                    break;
            }
        } while (true);
    }
    public static boolean login() {
        AdminAndHrServiceImp admin = new AdminAndHrServiceImp();
        int attempt = 0;
        final int max_attempt = 3;
        while (attempt < max_attempt) {
            System.out.print("Nhập tên đăng nhập: ");
            String username = sc.nextLine();
            System.out.print("Nhập mật khẩu: ");
            String password = sc.nextLine();
            boolean success = admin.login(username, password);
            if (success) {
                return true;
            } else {
                attempt++;
                if (attempt < max_attempt) {
                    System.out.println("Sai tài khoản hoặc mật khẩu. Vui lòng thử lại (" + (max_attempt - attempt) + " lần còn lại)");
                }
            }
        }
        System.out.println("Bạn đã nhập sai quá số lần cho phép. Quay lại menu chính.");
        return false;
    }

    public static void logout() {
        AdminAndHrServiceImp admin = new AdminAndHrServiceImp();
        admin.logout();
    }
    public static void MenuManager() {
        do {
            System.out.println("************ MENU ************");
            System.out.println("1. Quản lí phòng ban");
            System.out.println("2. Quản lí nhân viên");
            System.out.println("3. Thoát chương trình");
            System.out.print("Nhập lựa chọn của bạn: ");
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    DepartmentMenu.menuDepartment();
                    break;
                case 2:
                    break;
                case 3:
                    System.out.println("Thoát chương trình");
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ");
                    break;
            }
        } while (true);
    }
}
