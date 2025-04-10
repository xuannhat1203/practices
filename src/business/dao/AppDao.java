package business.dao;

import java.util.List;

public interface AppDao<T> {
    boolean login(String username, String password);
    boolean logout();
}
