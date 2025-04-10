package business.modal;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Employee {
    private String employeeId;
    private String fullName;
    private String email;
    private String phoneNumber;
    private Gender gender;
    private int salaryLevel;
    private BigDecimal salary;
    private LocalDate birthDate;
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
                    int salaryLevel, BigDecimal salary, LocalDate birthDate, String address,
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

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
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
}

