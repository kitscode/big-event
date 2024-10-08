package com.husky.service;

import com.husky.pojo.User;

public interface UserService {
    User findByUserName(String username);

    void register(String username, String password);

    void update(User user);

    //更新头像
    void updateAvatar(String avatarUrl);

    void updatePwd(String newPwd);
}
