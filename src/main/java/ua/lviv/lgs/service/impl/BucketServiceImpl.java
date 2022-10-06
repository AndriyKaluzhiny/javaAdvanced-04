package ua.lviv.lgs.service.impl;

import org.apache.log4j.Logger;
import ua.lviv.lgs.dao.BucketDao;
import ua.lviv.lgs.dao.impl.BucketDaoImpl;
import ua.lviv.lgs.domain.Bucket;
import ua.lviv.lgs.service.BucketService;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public class BucketServiceImpl implements BucketService {
    private static Logger LOGGER = Logger.getLogger(BucketServiceImpl.class);
    private static BucketService bucketServiceImpl;
    private BucketDao bucketDao;

    private BucketServiceImpl() {
        try {
            bucketDao = new BucketDaoImpl();
        } catch (InvocationTargetException | NoSuchMethodException | InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
            LOGGER.error(e);
        }
    }

    public static BucketService getBucketService() {
        if (bucketServiceImpl == null) {
            bucketServiceImpl = new BucketServiceImpl();
        }

        return bucketServiceImpl;
    }

    @Override
    public Bucket create(Bucket bucket) {
        try {
            return bucketDao.create(bucket);
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return null;
    }

    @Override
    public Bucket read(int id) {
        try {
            return bucketDao.read(id);
        } catch (SQLException e) {
            LOGGER.error(e);
        }

        return null;
    }

    @Override
    public Bucket update(Bucket t) {
        try {
            return bucketDao.update(t);
        } catch (Exception e) {
            LOGGER.error(e);
        }

        return null;
    }

    @Override
    public void delete(Bucket bucket) {
        try {
            bucketDao.delete(bucket);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void readAll() {
        try {
            bucketDao.readAll();
        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }

}
