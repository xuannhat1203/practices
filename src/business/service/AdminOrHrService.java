package business.service;

public interface AdminOrHrService {
    boolean login(String username, String password);
    boolean logout();
}
