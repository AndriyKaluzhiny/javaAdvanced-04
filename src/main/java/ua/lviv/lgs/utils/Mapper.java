package ua.lviv.lgs.utils;

import org.apache.log4j.Logger;
import ua.lviv.lgs.domain.Bucket;
import ua.lviv.lgs.domain.Product;
import ua.lviv.lgs.domain.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class Mapper {

    private static Logger LOGGER = Logger.getLogger(Mapper.class);

    public static Bucket bucketMapper(ResultSet result) throws SQLException {
        Bucket bucket = null;
        try {
            Integer userId = result.getInt("userId");
            Integer productId = result.getInt("product_id");
            Timestamp purchaseDate = result.getTimestamp("purchase_date");
            bucket = new Bucket(productId,userId,purchaseDate);

        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return bucket;
    }

    public static User userMapper(ResultSet result) throws SQLException {
        User user = null;
        try {
            String userName = result.getString("name");
            String lastName = result.getString("last_name");
            String email = result.getString("email");
            String password = result.getString("password");
            String role = result.getString("role");
            user = new User(userName, lastName, email, password, role);
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return user;
    }

    public static Product productMapper(ResultSet result) throws SQLException {
        Product product = null;
        try {
            Integer id = result.getInt("id");
            String name = result.getString("product_name");
            String description = result.getString("description");
            double cost = result.getDouble("cost");

            product = new Product(id,name,description,cost);
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return product;
    }
}
