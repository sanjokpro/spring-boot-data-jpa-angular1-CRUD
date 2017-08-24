package com.sanjok.springbootdataangular1crud.service;

import com.sanjok.springbootdataangular1crud.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService extends BaseService<User, Long> {
    public User findUserByUserNameAndPassword(User user);
}
