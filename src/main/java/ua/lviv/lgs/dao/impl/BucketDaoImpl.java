package ua.lviv.lgs.dao.impl;

import ua.lviv.lgs.dao.bucketDao;
import ua.lviv.lgs.domain.Bucket;
import ua.lviv.lgs.utils.ConnectionUtils;
import ua.lviv.lgs.utils.Mapper;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BucketDaoImpl implements bucketDao {

    private static String READ_ALL = "select * from bucket";
    private static String CREATE = "insert into bucket('user_id', 'product_id','purchase_date') values(" +
            "?,?,?)";
    private static String READ_BY_ID = "select * from bucket where id=?";
    private static String DELETE_BY_ID = "delete * from bucket where id=?";

    private PreparedStatement preparedStatement;
    private Connection connection;

    public BucketDaoImpl() throws SQLException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        connection = ConnectionUtils.openConnection();
    }

    @Override
    public void readAll() throws SQLException {
        preparedStatement = connection.prepareStatement(READ_ALL);
        ResultSet rs = preparedStatement.getResultSet();
        Mapper.bucketMapper(rs);
    }

    @Override
    public Bucket read(int id) throws SQLException {
        preparedStatement = connection.prepareStatement(READ_BY_ID);
        preparedStatement.setInt(1, id);
        ResultSet rs = preparedStatement.getResultSet();

        return Mapper.bucketMapper(rs);
    }

    @Override
    public Bucket create(Bucket bucket) throws SQLException {
        preparedStatement = connection.prepareStatement(CREATE);
        preparedStatement.setInt(1, bucket.getUserId());
        preparedStatement.setInt(2, bucket.getProductId());
        preparedStatement.setTimestamp(3, bucket.getPurchaseDate());
        preparedStatement.executeUpdate();

        return null;
    }

    @Override
    public Bucket update(Bucket bucket) throws Exception {
        throw new Exception("Update is unavailable in bucket");
    }

    @Override
    public void delete(Bucket bucket) throws SQLException {
        preparedStatement = connection.prepareStatement(DELETE_BY_ID);
        preparedStatement.setInt(1, bucket.getId());
    }
}
