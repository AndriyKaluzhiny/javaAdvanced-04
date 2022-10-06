package ua.lviv.lgs.service.impl;

import org.apache.log4j.Logger;
import ua.lviv.lgs.dao.ProductDao;
import ua.lviv.lgs.domain.Product;
import ua.lviv.lgs.service.ProductService;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public class ProductServiceImpl implements ProductService {
    private ProductDao productDao;
    private static ProductService productService;
    private static Logger LOGGER = Logger.getLogger(ProductServiceImpl.class);

    private ProductServiceImpl() {
        try {
            productDao = new ua.lviv.lgs.dao.impl.ProductDaoImpl();
        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException | InvocationTargetException | NoSuchMethodException e) {
            LOGGER.error(e);
        }
    }

    public ProductService getProductService() {
        if (productService == null) {
            productService = new ProductServiceImpl();
        }

        return productService;
    }


    @Override
    public void readAll() throws SQLException {
        productDao.readAll();
    }

    @Override
    public Product read(int id) throws SQLException {
        return productDao.read(id);
    }

    @Override
    public Product create(Product product) throws SQLException {
        return productDao.create(product);
    }

    @Override
    public Product update(Product product) throws Exception {
        return productDao.update(product);
    }

    @Override
    public void delete(Product product) throws SQLException {
        productDao.delete(product);
    }
}
