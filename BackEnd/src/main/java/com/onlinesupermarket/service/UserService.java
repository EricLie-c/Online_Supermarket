package com.onlinesupermarket.service;

import com.onlinesupermarket.pojo.User;

public interface UserService {
    Object registry(User user);

    void deleteUser(String userName);

    Object changeAccount(User user);

    Object getUserName();
}
