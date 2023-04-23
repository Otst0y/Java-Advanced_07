package dao;

import domain.User;
import shared.AbstractCRUD;

public interface UserDao extends AbstractCRUD<User> {
    User getUserByEmail(String email);
}
