package ua.lviv.lgs.utils;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtils {
    private static String USER_NAME = "root";
    private static String USER_PASSWORD = "root";
    private static String URL = "jdbc:mysql://localhost/shop";

    public static Connection openConnection() throws SQLException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
//        DOMConfigurator.configure("loggerConfig.xml");
        Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
        return DriverManager.getConnection(URL, USER_NAME, USER_PASSWORD);
    }

}
