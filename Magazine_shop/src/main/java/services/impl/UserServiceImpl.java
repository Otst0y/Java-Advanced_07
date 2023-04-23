package services.impl;

import dao.UserDao;
import dao.impl.UserDaoImpl;
import domain.User;
import org.apache.log4j.Logger;
import services.UserService;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDao userDao;
    private static UserServiceImpl userServiceImpl;
    private static Logger logger = Logger.getLogger(UserServiceImpl.class);

    public UserServiceImpl() {
        try {
            userDao = new UserDaoImpl();
        } catch (ClassNotFoundException | SQLException e) {
            logger.error(e);
        }
    }

    public static UserService getUserService() {
        if(userServiceImpl == null) {
            userServiceImpl = new UserServiceImpl();
        }
        return userServiceImpl;
    }

    @Override
    public User create(User user) {
        return userDao.create(user);
    }

    @Override
    public User read(Integer id) {
        return userDao.read(id);
    }

    @Override
    public User update(User user) {
        return userDao.update(user);
    }

    @Override
    public void delete(Integer id) {
        userDao.delete(id);
    }

    @Override
    public List<User> readAll() {
        return userDao.readAll();
    }

    @Override
    public User getUserByEmail(String email) {
        return userDao.getUserByEmail(email);
    }
}
