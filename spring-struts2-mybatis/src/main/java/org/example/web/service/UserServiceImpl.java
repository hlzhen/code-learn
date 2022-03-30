package org.example.web.service;

import org.example.web.dao.UserDAO;
import org.example.web.entity.User;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Ale on 2022/3/30
 */
public class UserServiceImpl implements UserService {
    private UserDAO userDAO;

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Transactional
    @Override
    public void register(User user) {
        userDAO.save(user);
    }
}
