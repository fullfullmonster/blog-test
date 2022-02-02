package com.example.blog.service;

import com.example.blog.dao.UserDao;
import com.example.blog.po.User;
import com.example.blog.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User checkUser(String username, String password) {
        User user = userDao.findUserByName(username, MD5Util.getMD5(password));
        return user;
    }
}
