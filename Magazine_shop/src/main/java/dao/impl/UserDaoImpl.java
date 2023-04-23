package dao.impl;

import connection.ConnectionUtils;
import dao.UserDao;
import domain.User;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    private static String READ_ALL = "select * from user";
    private static String CREATE = "insert into user(`name`, `lastName`, `email`, `password`, `role`) values (?, ?, ?, ?, ?)";
    private static String READ_BY_ID = "select * from user where id = ?";
    private static String READ_BY_EMAIL = "select * from user where `email` = ?";
    private static String UPDATE_BY_ID = "update user set name = ?, lastName = ?, email = ?, role = ? where id = ?";
    private static String DELETE_BY_ID = "delete from user where id = ?";

    private static Logger logger = Logger.getLogger(UserDaoImpl.class);

    private Connection connection;
    private PreparedStatement preparedStatement;
    public UserDaoImpl() throws SQLException, ClassNotFoundException {
        connection = ConnectionUtils.openConnection();
    }

    @Override
    public User create(User user) {
        try {
            preparedStatement = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, user.getRole());
            preparedStatement.executeUpdate();

            ResultSet rs = preparedStatement.getGeneratedKeys();
            rs.next();
            user.setId(rs.getInt(1));
        } catch (SQLException e) {
            logger.error(e);
        }
        return user;
    }

    @Override
    public User read(Integer id) {
        User user = null;
        try {
            preparedStatement = connection.prepareStatement(READ_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet result = preparedStatement.executeQuery();
            result.next();

            Integer userId = result.getInt("id");
            String name = result.getString("name");
            String lastName = result.getString("lastName");
            String email = result.getString("email");
            String password = result.getString("password");
            String role = result.getString("role");

            user = new User(userId, name, lastName, email, password, role);
        } catch (SQLException e) {
            logger.error(e);
        }
        return user;
    }

    @Override
    public User update(User user) {
        try {
            preparedStatement = connection.prepareStatement(UPDATE_BY_ID);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, user.getRole());
            preparedStatement.executeUpdate();
        } catch(SQLException e) {
            logger.error(e);
        }
        return user;
    }

    @Override
    public void delete(Integer id) {
        try {
            preparedStatement = connection.prepareStatement(DELETE_BY_ID);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error(e);
        }
    }

    @Override
    public List<User> readAll() {
        List<User> userRecords = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(READ_ALL);
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                Integer userId = result.getInt("id");
                String name = result.getString("name");
                String lastName = result.getString("lastName");
                String email = result.getString("email");
                String password = result.getString("password");
                String role = result.getString("role");

                userRecords.add(new User(userId, name, lastName, email, password, role));
            }
        } catch (SQLException e) {
            logger.error(e);
        }
        return userRecords;
    }

    @Override
    public User getUserByEmail(String email) {
        User user = null;
        try {
            preparedStatement = connection.prepareStatement(READ_BY_EMAIL);
            preparedStatement.setString(1, email);
            ResultSet result = preparedStatement.executeQuery();

            if (result.next()) {
                Integer userId = result.getInt("id");
                String name = result.getString("name");
                String lastName = result.getString("lastName");
                String password = result.getString("password");
                String role = result.getString("role");

                user = new User(userId, name, lastName, email, password, role);
            }
        } catch (SQLException e) {
            logger.error(e);
        }
        return user;
    }

}
