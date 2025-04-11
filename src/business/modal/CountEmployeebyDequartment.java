package business.modal;

public class CountEmployeebyDequartment {
    int dp_id;
    String dp_name;
    int count_employeeby_dequartment;
    public CountEmployeebyDequartment() {};
    public CountEmployeebyDequartment(int departmentId, String dpName, int employeeCount) {
        this.dp_id = departmentId;
        this.dp_name = dpName;
        this.count_employeeby_dequartment = employeeCount;
    }

    public int getDp_id() {
        return dp_id;
    }

    public void setDp_id(int dp_id) {
        this.dp_id = dp_id;
    }

    public String getDp_name() {
        return dp_name;
    }

    public void setDp_name(String dp_name) {
        this.dp_name = dp_name;
    }

    public int getCount_employeeby_dequartment() {
        return count_employeeby_dequartment;
    }

    public void setCount_employeeby_dequartment(int count_employeeby_dequartment) {
        this.count_employeeby_dequartment = count_employeeby_dequartment;
    }
}
