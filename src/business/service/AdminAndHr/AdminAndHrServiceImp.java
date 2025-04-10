package business.service.AdminAndHr;

import business.dao.AdminAndHr.AdminAndHrDao;
import business.dao.AdminAndHr.AdminAndHrImp;

public class AdminAndHrServiceImp implements AdminAndHrService {
    private final AdminAndHrDao adminAndHrDao;
    public AdminAndHrServiceImp() {
        adminAndHrDao = new AdminAndHrImp();
    }
    @Override
    public boolean login(String username, String password) {
        return adminAndHrDao.login(username, password);
    }

    @Override
    public boolean logout() {
        return adminAndHrDao.logout();
    }
}
