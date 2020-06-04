package dao;

import exception.DBException;
import model.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBC implements UserDao{
    private static UserDaoJDBC userDaoJDBC;
    private Connection connection;

    private UserDaoJDBC(Connection connection) {
        this.connection = connection;
    }

    public static UserDaoJDBC getInstance(Connection connection) {
        if (userDaoJDBC == null) {
            userDaoJDBC = new UserDaoJDBC(connection);
        }
        return userDaoJDBC;
    }

    @Override
    public List<User> getAllUser() {
        List<User> userList = new ArrayList<>();
        try {
            Statement stmt = connection.createStatement();
            stmt.execute("select * from users");
            ResultSet result = stmt.getResultSet();
            while (result.next()) {
                userList.add(new User(result.getLong("id"),
                        result.getString("name"),
                        result.getString("password"),
                        result.getLong("age"),
                        result.getString("role")));
            }
            result.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    @Override
    public boolean validateUser(String name, String password) {
        boolean result = false;
        try {
            String sql = "select * from users where name = ? and password = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setString(2, password);
            ResultSet resultSet = pstmt.executeQuery();
            result = resultSet.next();
            resultSet.close();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public User getUserById(long id) {
        User user = null;
        try {
            String sql = "select * from users where id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, id);
            ResultSet result = pstmt.executeQuery();
            if (result.next()) {
                String name = result.getString("name");
                String password = result.getString("password");
                Long age = result.getLong("age");
                String role = result.getString("role");
                user = new User(id, name, password, age, role);
            }
            result.close();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public User getUserByName(String name) {
        User user = null;
        try {
            String sql = "select * from users where name = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, name);
            ResultSet result = pstmt.executeQuery();
            if (result.next()) {
                Long id = result.getLong("id");
                String password = result.getString("password");
                Long age = result.getLong("age");
                String role = result.getString("role");
                user = new User(id, name, password, age, role);

            }
            result.close();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public void addUser(User user) throws DBException {
        try {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("INSERT users (id, name, password, age, role) VALUES (" +
                    user.getId() + ", '" +
                    user.getName() + "', '" +
                    user.getPassword() + "', " +
                    user.getAge() + ", '" +
                    user.getRole() + "')");
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException(e);
        }
    }

    @Override
    public void deleteUser(User user) throws DBException {
        try {
            String sql = "DELETE FROM users WHERE id=?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, user.getId());
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException(e);
        }
    }

    @Override
    public void updateUser(User user) throws DBException {
        try {
            connection.setAutoCommit(false);
            String sql = "UPDATE users SET name = ?, password = ?, age = ?, role = ? WHERE id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getPassword());
            pstmt.setLong(3, user.getAge());
            pstmt.setString(4, user.getRole());
            pstmt.setLong(5, user.getId());
            pstmt.executeUpdate();
            pstmt.close();
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException(e);
        }
    }
}