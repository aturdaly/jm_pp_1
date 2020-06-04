package service;

import dao.UserDao;
import dao.UserDaoFactory;
import exception.DBException;
import model.User;
import java.util.List;

public class UserService {
    private static UserService userService;
    private UserDao userDao = UserDaoFactory.getUserDaoImpl();

    private UserService() {}

    public static UserService getInstance() {
        if (userService == null) {
            userService = new UserService();
        }
        return userService;
    }

    public List<User> getAllUsers() {
        return userDao.getAllUser();
    }

    public User getUserById(long id) {
        return userDao.getUserById(id);
    }

    public User getUserByName(String name) {
        return userDao.getUserByName(name);
    }

    public boolean deleteUser(Long id) {
        try {
            userDao.deleteUser(getUserById(id));
            return getUserById(id)==null;
        } catch (DBException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean addUser(User user) {
        try {
            userDao.addUser(user);
            return true;
        } catch (DBException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateUser(User user) {
        try {
            userDao.updateUser(user);
            return true;
        } catch (DBException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean validateUser(String name, String password) {
        return userDao.validateUser(name, password);
    }
}
