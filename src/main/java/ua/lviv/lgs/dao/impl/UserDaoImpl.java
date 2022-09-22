package ua.lviv.lgs.dao.impl;

import ua.lviv.lgs.dao.userDao;
import ua.lviv.lgs.domain.User;
import ua.lviv.lgs.utils.ConnectionUtils;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImpl implements userDao {

    private static String READ_ALL = "select * from user";
    private static String CREATE = "insert into user('first_name', 'last_name', 'email', 'password') values(" +
            "?,?,?,?,?)";
    private static String READ_BY_ID = "select * from user where id=?";
    private static String UPDATE = "update user set name=?, last_name=?, email=?, password=? where id=?";
    private static String DELETE_BY_ID = "delete * from user where id=?";

    private PreparedStatement preparedStatement;
    private Connection connection;

    public UserDaoImpl() throws SQLException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        connection = ConnectionUtils.openConnection();
    }

    @Override
    public void readAll() throws SQLException {
        preparedStatement = connection.prepareStatement(READ_ALL);
        preparedStatement.executeUpdate();
    }

    @Override
    public User read(int id) throws SQLException {
        preparedStatement = connection.prepareStatement(READ_BY_ID);
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();

        ResultSet rs = preparedStatement.getResultSet();
        User user = new User(rs.getString("name"), rs.getString("last_name"), rs.getString("email"), rs.getString("password"));
        return user;
    }

    @Override
    public User create(User user) throws SQLException {
        preparedStatement = connection.prepareStatement(CREATE);
        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getLastName());
        preparedStatement.setString(3, user.getEmail());
        preparedStatement.setString(4, user.getPassword());
        preparedStatement.executeUpdate();

        return null;
    }

    @Override
    public User update(User user) throws SQLException {
        preparedStatement = connection.prepareStatement(UPDATE);
        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getLastName());
        preparedStatement.setString(3, user.getEmail());
        preparedStatement.setString(4, user.getPassword());
        preparedStatement.executeUpdate();

        return null;
    }

    @Override
    public void delete(User user) throws SQLException {
        preparedStatement = connection.prepareStatement(DELETE_BY_ID);
        preparedStatement.setInt(1, user.getId());
        preparedStatement.executeUpdate();
    }
}
