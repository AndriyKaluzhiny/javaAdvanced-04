package ua.lviv.lgs.dao;

import ua.lviv.lgs.domain.User;
import ua.lviv.lgs.shared.AbstractDao;

public interface UserDao extends AbstractDao<User> {
    User readByEmail(String email);
}
