package business.modal;

public class Account {
    String username, password;
    boolean status;
    HrOrAdmin role;

    public Account() {
    }

    public Account(String username, String password, boolean status, HrOrAdmin role) {
        this.username = username;
        this.password = password;
        this.status = status;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public HrOrAdmin getRole() {
        return role;
    }

    public void setRole(HrOrAdmin role) {
        this.role = role;
    }
}
