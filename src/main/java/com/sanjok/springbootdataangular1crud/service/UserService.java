package com.sanjok.springbootdataangular1crud.service;

import com.sanjok.springbootdataangular1crud.entity.User;


public interface UserService extends BaseService<User, Long> {
    public User findUserByUserNameAndPassword(User user);
}
