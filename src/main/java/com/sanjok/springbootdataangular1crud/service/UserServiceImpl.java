package com.sanjok.springbootdataangular1crud.service;


import com.sanjok.springbootdataangular1crud.entity.User;
import com.sanjok.springbootdataangular1crud.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long Id) throws Exception {
        return userRepository.findOne(Id);
    }

    @Override
    public User insert(User user) {
        return userRepository.save(user);
    }

    @Override
    public User update(User user) throws Exception {
        System.out.println("user to save=====>" + user);
        return userRepository.save(user);
    }

    @Override
    public User deleteById(Long userId) throws Exception {
        try {
            User user = findById(userId);
            if (null != user) {
                userRepository.delete(user);
            }
            return user;
        } catch (Exception e) {
            throw e;
        }


    }


    @Override
    public User findUserByUserNameAndPassword(User user) {
        User usr = userRepository.findByUserNameAndPassword(user.getUserName(), user.getPassword());
        return usr;

    }
}
