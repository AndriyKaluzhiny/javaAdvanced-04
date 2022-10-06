package ua.lviv.lgs.shared;

import java.sql.SQLException;

public interface AbstractDao <T> {
    void readAll() throws SQLException;
    T read(int id) throws SQLException;
    T create(T t) throws SQLException;
    T update(T t) throws Exception;
    void delete(T t) throws SQLException;
}
