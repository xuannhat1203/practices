package presentation;

import business.modal.CountEmployeebyDequartment;
import business.modal.Department;
import business.modal.Most_emloyee;
import business.modal.Most_salary;
import business.service.Employee.EmployeeServicess;

import java.util.List;

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
            sc.nextLine();
            switch (choice) {
                case 1:
                    countEmployeesByDepartment();
                    break;
                case 2:
                    totalEmployeesInSystem();
                    break;
                case 3:
                    departmentWithMostEmployees();
                    break;
                case 4:
                    departmentWithHighestSalary();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ!");
                    break;
            }
        } while (true);
    }
    public static void countEmployeesByDepartment(){
        EmployeeServicess employeeServicess = new EmployeeServicess();
        List<CountEmployeebyDequartment> list_employee_by_department = employeeServicess.array_employee_by_dequartment();
        for (CountEmployeebyDequartment count : list_employee_by_department) {
            System.out.println("Department ID: "+count.getDp_id());
            System.out.println("Department Name: "+count.getDp_name());
            System.out.println("Count Employee: "+count.getCount_employeeby_dequartment());
            System.out.println("--------------------------------------------");
        }
    }
    public static void totalEmployeesInSystem(){
        EmployeeServicess employeeServicess = new EmployeeServicess();
        System.out.println("Total employees: "+employeeServicess.totalEmployee());
    }
    public static void departmentWithMostEmployees(){
        EmployeeServicess employeeServicess = new EmployeeServicess();
        System.out.println("Phòng ban có nhiều nhân viên nhất");
        Most_emloyee department = employeeServicess.get_most_department_employee();
        System.out.println("Department Name: "+department.getDepartmentName());
        System.out.println("Total employees: "+employeeServicess.totalEmployee());
    }
    public static void departmentWithHighestSalary(){
        EmployeeServicess employeeServicess = new EmployeeServicess();
        System.out.println("Phòng ban có tổng lương cao nhất");
        Most_salary department = employeeServicess.get_most_department_salary();
        System.out.println("Department Name: "+department.getDepartmentName());
        System.out.println("Total Salary: "+department.getSalary());
    }
}
