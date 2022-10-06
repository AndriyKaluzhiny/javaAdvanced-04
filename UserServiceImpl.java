
package ua.lviv.lgs.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import ua.lviv.lgs.dao.UserDao;
import ua.lviv.lgs.dao.impl.UserDaoImpl;
import ua.lviv.lgs.domain.User;
import ua.lviv.lgs.service.UserService;


public class UserServiceImpl implements UserService {
    private static UserService userServiceImpl;
    private UserDao userDao;

    private static Logger LOGGER = Logger.getLogger(UserServiceImpl.class);
    public UserServiceImpl() {
        try {
            userDao = new UserDaoImpl();
        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
            LOGGER.error(e);
        }
    }

    public static UserService getUserService() {
        if (userServiceImpl == null) {
            userServiceImpl = new UserServiceImpl();
        }

        return userServiceImpl;
    }

    @Override
    public void readAll() throws SQLException {
        userDao.readAll();
    }

    @Override
    public User read(int id) throws SQLException {
        return userDao.read(id);
    }

    @Override
    public User create(User user) throws SQLException {
        return userDao.create(user);
    }

    @Override
    public User update(User user) throws Exception {
        return userDao.update(user);
    }

    @Override
    public void delete(User user) throws SQLException {
        userDao.delete(user);
    }

    @Override
    public User readByEmail(String email) {
        return userDao.readByEmail(email);
    }
}
