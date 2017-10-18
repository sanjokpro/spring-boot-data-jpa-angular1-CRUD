package com.sanjok.springbootdataangular1crud.controller;

import com.sanjok.springbootdataangular1crud.dto.UserDto;
import com.sanjok.springbootdataangular1crud.entity.User;
import com.sanjok.springbootdataangular1crud.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.text.MessageFormat;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class AppController {
    private static final Logger log = LoggerFactory.getLogger(AppController.class);
    @Autowired
    private UserService userService;



    @PostMapping(value = "/user/add")
    public User addUser(@RequestBody UserDto userDto) {
        return userService.insert(userDto.toUser());
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping(value = "/user/remove/{id}")
    public User removeUser(@PathVariable("id") String id) {
        try {
            if (null != id && !id.isEmpty())
                return userService.deleteById(Long.valueOf(id));
        }  catch (Exception e) {
           log.debug(MessageFormat.format("unable to remove user with id {0} due to {1}", id,e.getCause()),e);
            e.printStackTrace();
        }
        return new User();
    }

    @GetMapping(value = "/currentUser")
    public User getCurrentUser(Authentication authentication) {
        return userService.findUserByUsername(authentication.getName());
    }

    @GetMapping(value = "/user/all")
    public List<User> getAllUser() {
        return userService.findAll();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping(value = "/user/update")
    public User updateUser(@RequestBody UserDto dto) {
        try {
            userService.update(dto.toUser());
            return userService.findById(dto.getUserId());
        } catch (Exception e) {
            log.debug(MessageFormat.format("unable to find user with id {0} due to {1}", dto.getUserId(),e.getCause()),e);
        }
        return new User();

    }
}
