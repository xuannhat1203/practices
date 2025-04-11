package business.modal;

import java.math.BigDecimal;

public class Most_salary {
    String departmentName;
    BigDecimal salary;
    public Most_salary() {};
    public Most_salary(String departmentName, BigDecimal salary) {
        this.departmentName = departmentName;
        this.salary = salary;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }
}
