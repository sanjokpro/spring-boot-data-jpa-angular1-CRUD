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
        return userRepository.findOne(Math.toIntExact(Id));
    }


    public User findById(Integer id) throws Exception {
        return userRepository.findOne(id);
    }

    @Override
    public User insert(User user) {
        return userRepository.save(user);
    }

    @Override
    public User update(User user) throws Exception {
        //findById(user.getUserId());

        return userRepository.save(user);
    }

    @Override
    public User deleteById(Long userId) throws Exception {
        User user = findById(userId);
        userRepository.delete(user);
        return null;
    }


    @Override
    public User findUserByUserNameAndPassword(User user) {

        List<User> usr = userRepository.findTop1ByUserNameAndPassword(user.getUserName(), user.getPassword());
        User u = null;
        if (null != usr) {
            System.out.println("data retrived"+usr.size());
            for (User userr : usr) {
                System.out.println(userr.getPassword()+"--=-=-=-=-=-=-=-=-");
            }
        } else {
            System.out.println("returned null");
        }
        try {
            u = findById(1l);
            System.out.println("byid:" + u);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("___________________Received:___" + u.getUserName() + "--+u.getPassword()+" + "________________________________");
        return u;

    }
}
