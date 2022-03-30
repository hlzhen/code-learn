package org.example.web.action;

import com.opensymphony.xwork2.Action;
import org.example.web.entity.User;
import org.example.web.service.UserService;

/**
 * Created by Ale on 2022/3/30
 */
public class RegAction implements Action {
    private UserService userService;

    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute() throws Exception {
        userService.register(user);
        return Action.SUCCESS;
    }
}
