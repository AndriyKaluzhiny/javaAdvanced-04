package ua.lviv.lgs.dao.impl;

import org.apache.log4j.Logger;
import ua.lviv.lgs.dao.BucketDao;
import ua.lviv.lgs.domain.Bucket;
import ua.lviv.lgs.service.BucketService;
import ua.lviv.lgs.service.impl.BucketServiceImpl;
import ua.lviv.lgs.utils.ConnectionUtils;
import ua.lviv.lgs.utils.Mapper;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;

public class BucketDaoImpl implements BucketDao {

    private static String READ_ALL = "select * from bucket";
    private static String CREATE = "insert into bucket('user_id', 'product_id','purchase_date') values(" +
            "?,?,?)";
    private static String READ_BY_ID = "select * from bucket where id=?";
    private static String DELETE_BY_ID = "delete * from bucket where id=?";

    private PreparedStatement preparedStatement;
    private Connection connection;

    private static Logger LOGGER = Logger.getLogger(BucketDaoImpl.class);

    public BucketDaoImpl() throws SQLException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        connection = ConnectionUtils.openConnection();
    }

    @Override
    public void readAll() {

        try {
            preparedStatement = connection.prepareStatement(READ_ALL);
            ResultSet rs = preparedStatement.getResultSet();
            Mapper.bucketMapper(rs);
            throw new SQLException();
        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public Bucket read(int id) throws SQLException {
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(READ_BY_ID);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.getResultSet();
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return Mapper.bucketMapper(resultSet);
    }

    @Override
    public Bucket create(Bucket bucket) throws SQLException {
        try {
            preparedStatement = connection.prepareStatement(CREATE);
            preparedStatement.setInt(1, bucket.getUserId());
            preparedStatement.setInt(2, bucket.getProductId());
            preparedStatement.setTimestamp(3, bucket.getPurchaseDate());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return null;
    }

    @Override
    public Bucket update(Bucket bucket) {
        LOGGER.error(new Exception("There is no update in Bucket class"));
        return null;
    }

    @Override
    public void delete(Bucket bucket) {
        try {
        preparedStatement = connection.prepareStatement(DELETE_BY_ID);
        preparedStatement.setInt(1, bucket.getId());
        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }
}
