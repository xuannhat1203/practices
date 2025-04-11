package business.modal;

import validate.validateEmployee;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Scanner;

public class Employee {
    private static Scanner sc = new Scanner(System.in);
    private String employeeId;
    private String fullName;
    private String email;
    private String phoneNumber;
    private Gender gender;
    private int salaryLevel;
    private BigDecimal salary;
    private Date birthDate;
    private String address;
    private Status status;
    private int departmentId;

    public Employee() {

    }


    public enum Gender {
        male, female, other
    }

    public enum Status {
        active, inactive, onleave, policyleave
    }

    public Employee(String employeeId, String fullName, String email, String phoneNumber, Gender gender,
                    int salaryLevel, BigDecimal salary, Date birthDate, String address,
                    Status status, int departmentId) {
        setEmployeeId(employeeId);
        setFullName(fullName);
        setEmail(email);
        setPhoneNumber(phoneNumber);
        setGender(gender);
        setSalaryLevel(salaryLevel);
        setSalary(salary);
        setBirthDate(birthDate);
        setAddress(address);
        setStatus(status);
        setDepartmentId(departmentId);
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        if (!employeeId.matches("^E[0-9]{4}$")) {
            throw new IllegalArgumentException("Invalid employee ID format");
        }
        this.employeeId = employeeId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        if (fullName == null || fullName.length() < 15 || fullName.length() > 150) {
            throw new IllegalArgumentException("Full name must be between 15 and 150 characters");
        }
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (!email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
            throw new IllegalArgumentException("Invalid email format");
        }
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        if (!phoneNumber.matches("^0[0-9]{9}$")) {
            throw new IllegalArgumentException("Phone number must start with 0 and contain 10 digits");
        }
        this.phoneNumber = phoneNumber;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        if (gender == null) {
            throw new IllegalArgumentException("Gender cannot be null");
        }
        this.gender = gender;
    }

    public int getSalaryLevel() {
        return salaryLevel;
    }

    public void setSalaryLevel(int salaryLevel) {
        if (salaryLevel <= 0) {
            throw new IllegalArgumentException("Salary level must be greater than 0");
        }
        this.salaryLevel = salaryLevel;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        if (salary == null || salary.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Salary must be greater than 0");
        }
        this.salary = salary;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        if (birthDate == null) {
            throw new IllegalArgumentException("Birth date cannot be null");
        }
        this.birthDate = birthDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        if (address == null || address.isEmpty()) {
            throw new IllegalArgumentException("Address cannot be null or empty");
        }
        this.address = address;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        if (status == null) {
            throw new IllegalArgumentException("Status cannot be null");
        }
        this.status = status;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        if (departmentId <= 0) {
            throw new IllegalArgumentException("Department ID must be greater than 0");
        }
        this.departmentId = departmentId;
    }
    public void inputData() {
        while (true) {
            try {
                this.employeeId = validateEmployee.validateEmployeeId("Nhập mã nhân viên:");
                this.fullName = validateEmployee.validateEmployeeName("Nhập họ tên đầy đủ:");
                this.email = validateEmployee.validateEmployeeEmail("Nhập email:");
                this.phoneNumber = validateEmployee.validateEmployeePhone("Nhập số điện thoại:");
                this.gender = validateEmployee.validateGender("Nhập giới tính (MALE/FEMALE/OTHER):");
                this.salaryLevel = validateEmployee.validateSalaryLevel("Nhập cấp bậc lương (số nguyên > 0):");
                this.salary = validateEmployee.validateSalary("Nhập lương:");
                this.birthDate = Date.valueOf(validateEmployee.validateBirthDate("Nhập ngày sinh (dd/MM/yyyy):"));
                this.address = validateEmployee.validateAddress("Nhập địa chỉ:");
                this.status = validateEmployee.validateStatus("Nhập trạng thái (ACTIVE/INACTIVE/ONLEAVE/POLICYLEAVE):");

                System.out.print("Nhập mã phòng ban (số nguyên > 0): ");
                while (true) {
                    try {
                        int deptId = Integer.parseInt(validateEmployee.sc.nextLine());
                        if (deptId > 0) {
                            this.departmentId = deptId;
                            break;
                        } else {
                            System.out.println("Mã phòng ban phải lớn hơn 0.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Mã phòng ban phải là số nguyên.");
                    }
                }

                break;

            } catch (Exception e) {
                System.out.println("Lỗi: " + e.getMessage());
                System.out.println("Vui lòng nhập lại!\n");
            }
        }
    }


    @Override
    public String toString() {
        return "\n===== Employee Information =====\n" +
                "Employee ID   : " + employeeId + "\n" +
                "Full Name     : " + fullName + "\n" +
                "Email         : " + email + "\n" +
                "Phone Number  : " + phoneNumber + "\n" +
                "Gender        : " + gender.name() + "\n" +
                "Salary Level  : " + salaryLevel + "\n" +
                "Salary        : " + salary + "\n" +
                "Birth Date    : " + birthDate + "\n" +
                "Address       : " + address + "\n" +
                "Status        : " + status.name() + "\n" +
                "Department ID : " + departmentId + "\n" +
                "================================\n";
    }
}

