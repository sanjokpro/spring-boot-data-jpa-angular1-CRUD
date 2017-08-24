package com.sanjok.springbootdataangular1crud.controller;

import com.sanjok.springbootdataangular1crud.dto.UserDto;
import com.sanjok.springbootdataangular1crud.entity.User;
import com.sanjok.springbootdataangular1crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class AppController {
    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        return "To Login POST[/login]";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/login")
    public @ResponseBody User login(@RequestBody UserDto userDto) {
        System.out.println("====================================================="+userDto);
        User user = new User();
        user.setPassword(userDto.getPassword());
        user.setUserName(userDto.getUserName());
        System.out.println("----------------------------------"+user);

        return userService.findUserByUserNameAndPassword(user);
    }
}
