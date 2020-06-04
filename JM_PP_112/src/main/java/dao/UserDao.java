package dao;

import exception.DBException;
import model.User;
import java.util.List;

public interface UserDao {
    List<User> getAllUser();
    boolean validateUser(String name, String password);
    User getUserById(long id);
    User getUserByName(String name);
    void addUser(User user) throws DBException;
    void deleteUser(User user) throws DBException;
    void updateUser(User user) throws DBException;
}