package ua.lviv.lgs.dao.impl;

import org.apache.log4j.Logger;
import ua.lviv.lgs.dao.ProductDao;
import ua.lviv.lgs.domain.Product;
import ua.lviv.lgs.utils.ConnectionUtils;
import ua.lviv.lgs.utils.Mapper;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductDaoImpl implements ProductDao {
    private static String READ_ALL = "select * from product";
    private static String CREATE = "insert into product('name', 'description', 'cost') values(" +
            "?,?,?)";
    private static String READ_BY_ID = "select * from product where id=?";
    private static String UPDATE = "update product set name=?, description=?, cost=? where id=?";
    private static String DELETE_BY_ID = "delete * from product where id=?";

    private PreparedStatement preparedStatement;
    private Connection connection;

    private static Logger LOGGER = Logger.getLogger(ProductDaoImpl.class);

    public ProductDaoImpl() throws SQLException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        connection = ConnectionUtils.openConnection();
    }

    @Override
    public void readAll() {
        try {
            preparedStatement = connection.prepareStatement(READ_ALL);
            ResultSet rs = preparedStatement.getResultSet();
            Mapper.productMapper(rs);
            throw new SQLException();
        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public Product read(int id) throws SQLException {
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(READ_BY_ID);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.getResultSet();
        } catch (SQLException e) {
            LOGGER.error(e);
        }

        return Mapper.productMapper(resultSet);
    }

    @Override
    public Product create(Product product) throws SQLException {
        try {
            preparedStatement = connection.prepareStatement(CREATE);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getDescription());
            preparedStatement.setDouble(3, product.getCost());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e);
        }

        return null;
    }

    @Override
    public Product update(Product product) throws SQLException {
        try {
            preparedStatement = connection.prepareStatement(UPDATE);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getDescription());
            preparedStatement.setDouble(3, product.getCost());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return null;
    }

    @Override
    public void delete(Product product) throws SQLException {
        try {
            preparedStatement = connection.prepareStatement(DELETE_BY_ID);
            preparedStatement.setInt(1, product.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }
}
