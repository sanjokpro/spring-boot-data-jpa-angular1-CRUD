package com.sanjok.springbootdataangular1crud.controller;

import com.sanjok.springbootdataangular1crud.dto.UserDto;
import com.sanjok.springbootdataangular1crud.entity.User;
import com.sanjok.springbootdataangular1crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @RequestMapping(method = RequestMethod.POST, value = "/login")
    public @ResponseBody
    User login(@RequestBody UserDto userDto) {
        User user = new User();
        user.setPassword(userDto.getPassword());
        user.setUserName(userDto.getUserName());
        return userService.findUserByUserNameAndPassword(user);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/user/add")
    public @ResponseBody
    User addUser(@RequestBody UserDto userDto) {
        return userService.insert(userDto.toUser());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/user/all")
    public @ResponseBody
    List<User> getAllUser() {
        return userService.findAll();
    }
}
