package ua.lviv.lgs.dao.impl;

import org.apache.log4j.Logger;
import ua.lviv.lgs.dao.UserDao;
import ua.lviv.lgs.domain.User;
import ua.lviv.lgs.domain.UserRole;
import ua.lviv.lgs.service.UserService;
import ua.lviv.lgs.service.impl.UserServiceImpl;
import ua.lviv.lgs.utils.ConnectionUtils;
import ua.lviv.lgs.utils.Mapper;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;

import static ua.lviv.lgs.utils.ConnectionUtils.openConnection;

public class UserDaoImpl implements UserDao {

    private static String READ_ALL = "select * from user";
    private static String CREATE = "insert into user(`name`, `last_name`, `email`, `password`, `role`) values (?,?,?,?,?)";
    private static String READ_BY_ID = "select * from user where id =?";
    private static String READ_BY_EMAIL = "select * from user where email=?";
    private static String UPDATE = "update user set email = ?, first_name = ?, last_name = ?, role=?, password = ?  where id = ?";
    private static String DELETE_BY_ID = "delete from user where id = ?";

    private Connection connection;
    private PreparedStatement preparedStatement;


//    private static Logger LOGGER = Logger.getLogger(UserDaoImpl.class);

    public UserDaoImpl() throws SQLException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        connection = openConnection();
    }

    @Override
    public void readAll() throws SQLException {
        try {
            preparedStatement = connection.prepareStatement(READ_ALL);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
//            LOGGER.error(e);
        }
    }

    @Override
    public User read(int id) throws SQLException {
        User user = null;
        try {
            preparedStatement = connection.prepareStatement(READ_BY_ID);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

            ResultSet rs = preparedStatement.getResultSet();
            rs.next();
            user = new User(rs.getString("name"), rs.getString("last_name"), rs.getString("email"), rs.getString("password"), rs.getString("role"));
        } catch (SQLException e) {
//            LOGGER.error(e);
        }

        return user;
    }

    @Override
    public User create(User user) throws SQLException {
        try {
            preparedStatement = connection.prepareStatement(CREATE,  Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, UserRole.USER.toString());
            preparedStatement.executeUpdate();

            ResultSet rs = preparedStatement.getGeneratedKeys();
            rs.next();
            user.setId(rs.getInt(1));
        } catch (SQLException e) {
            System.out.println(e.toString() + 123);
        }

        return user;
    }

    @Override
    public User update(User user) throws SQLException {
        try {
            preparedStatement = connection.prepareStatement(UPDATE);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
//            LOGGER.error(e);
        }
        return null;
    }

    @Override
    public void delete(User user) throws SQLException {
        try {
            preparedStatement = connection.prepareStatement(DELETE_BY_ID);
            preparedStatement.setInt(1, user.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
//            LOGGER.error(e);
        }
    }

    @Override
    public User readByEmail(String email) {
        User user = null;
        try {
            preparedStatement = connection.prepareStatement(READ_BY_EMAIL);
            preparedStatement.setString(1, email);
            ResultSet result = preparedStatement.executeQuery();
            result.next();

            Integer userId = result.getInt("id");
            String name = result.getString("name");
            String lastName = result.getString("last_name");
            String role = result.getString("role");
            String password = result.getString("password");
            user = new User(userId, name, lastName, email, password, role);
        } catch (SQLException e) {
            System.out.println(e.toString());
//            LOGGER.error(e);
        }
        return user;
    }
}
