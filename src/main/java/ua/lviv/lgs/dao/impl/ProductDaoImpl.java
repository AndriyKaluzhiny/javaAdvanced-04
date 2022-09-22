package ua.lviv.lgs.dao.impl;

import ua.lviv.lgs.dao.productDao;
import ua.lviv.lgs.domain.Product;
import ua.lviv.lgs.utils.ConnectionUtils;
import ua.lviv.lgs.utils.Mapper;

import javax.xml.transform.Result;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductDaoImpl implements productDao {
    private static String READ_ALL = "select * from product";
    private static String CREATE = "insert into product('name', 'description', 'cost') values(" +
            "?,?,?)";
    private static String READ_BY_ID = "select * from product where id=?";
    private static String UPDATE = "update product set name=?, description=?, cost=? where id=?";
    private static String DELETE_BY_ID = "delete * from product where id=?";

    private PreparedStatement preparedStatement;
    private Connection connection;

    public ProductDaoImpl() throws SQLException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        connection = ConnectionUtils.openConnection();
    }

    @Override
    public void readAll() throws SQLException {
        preparedStatement = connection.prepareStatement(READ_ALL);
        ResultSet rs = preparedStatement.getResultSet();
        Mapper.productMapper(rs);
    }

    @Override
    public Product read(int id) throws SQLException {
        preparedStatement = connection.prepareStatement(READ_BY_ID);
        preparedStatement.setInt(1, id);
        ResultSet rs = preparedStatement.getResultSet();

        return Mapper.productMapper(rs);
    }

    @Override
    public Product create(Product product) throws SQLException {
        preparedStatement = connection.prepareStatement(CREATE);
        preparedStatement.setString(1, product.getName());
        preparedStatement.setString(2, product.getDescription());
        preparedStatement.setDouble(3, product.getCost());
        preparedStatement.executeUpdate();

        return null;
    }

    @Override
    public Product update(Product product) throws SQLException {
        preparedStatement = connection.prepareStatement(UPDATE);
        preparedStatement.setString(1, product.getName());
        preparedStatement.setString(2, product.getDescription());
        preparedStatement.setDouble(3, product.getCost());
        preparedStatement.executeUpdate();

        return null;
    }

    @Override
    public void delete(Product product) throws SQLException {
        preparedStatement = connection.prepareStatement(DELETE_BY_ID);
        preparedStatement.setInt(1, product.getId());
        preparedStatement.executeUpdate();
    }
}
