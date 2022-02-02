package com.example.blog.service;

import com.example.blog.po.User;
import org.springframework.transaction.annotation.Transactional;

public interface UserService {

    User checkUser(String username, String password);

}
