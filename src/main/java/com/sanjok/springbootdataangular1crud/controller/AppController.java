package com.sanjok.springbootdataangular1crud.controller;

import com.sanjok.springbootdataangular1crud.dto.UserDto;
import com.sanjok.springbootdataangular1crud.entity.User;
import com.sanjok.springbootdataangular1crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/")
public class AppController {
    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        return "To Login POST[/login]";
    }

    //=======================/login [POST]=would make sense before enabaling role based Security========
/*
    @RequestMapping(method = RequestMethod.POST, value = "/login")
    public @ResponseBody
    User login(@RequestBody UserDto userDto) {
        User user = new User();
        user.setPassword(userDto.getPassword());
        user.setUserName(userDto.getUserName());
        return userService.findUserByUserNameAndPassword(user);
    }
    */

    //=========================================================================================
    @RequestMapping(method = RequestMethod.POST, value = "/user/add")
    public @ResponseBody
    User addUser(@RequestBody UserDto userDto) {
        return userService.insert(userDto.toUser());
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(method = RequestMethod.DELETE, value = "/user/remove/{id}")
    public @ResponseBody
    User removeUser(@PathVariable String id) {
        try {
            if (null != id && !id.isEmpty())
                return userService.deleteById(Long.valueOf(id));
        } catch (InvalidDataAccessApiUsageException e) {

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new User();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/currentUser")
    public User getCurrentUser(Authentication authentication) {
        return userService.findUserByUsername(authentication.getName());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/user/all")
    public @ResponseBody
    List<User> getAllUser() {
        return userService.findAll();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(method = RequestMethod.PUT, value = "/user/update")
    public User updateUser(@RequestBody UserDto user) {
        try {
            userService.update(user.toUser());
            return userService.findById(user.getUserId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new User();

    }
}
