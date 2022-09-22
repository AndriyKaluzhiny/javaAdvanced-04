package ua.lviv.lgs.utils;

import ua.lviv.lgs.domain.Bucket;
import ua.lviv.lgs.domain.Product;
import ua.lviv.lgs.domain.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class Mapper {
    public static Bucket bucketMapper(ResultSet result) throws SQLException {
        Integer userId = result.getInt("userId");
        Integer productId = result.getInt("product_id");
        Timestamp purchaseDate = result.getTimestamp("purchase_date");
        return new Bucket(userId,productId,purchaseDate);
    }

    public static User userMapper(ResultSet result) throws SQLException {
        String userName = result.getString("name");
        String lastName = result.getString("last_name");
        String email = result.getString("email");
        String password = result.getString("password");

        return new User(userName,lastName,email,password);
    }

    public static Product productMapper(ResultSet result) throws SQLException {
        Integer id = result.getInt("id");
        String name = result.getString("product_name");
        String description = result.getString("description");
        double cost = result.getDouble("cost");

        return new Product(id,name,description,cost);
    }
}
