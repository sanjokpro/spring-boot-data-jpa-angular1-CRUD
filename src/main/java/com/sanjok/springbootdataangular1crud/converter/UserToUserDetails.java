package com.sanjok.springbootdataangular1crud.converter;

import com.sanjok.springbootdataangular1crud.entity.Role;
import com.sanjok.springbootdataangular1crud.entity.User;
import com.sanjok.springbootdataangular1crud.service.security.UserDetailsImpl;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

@Component
public class UserToUserDetails implements Converter<User, UserDetails> {
    @Override
    public UserDetails convert(User source) {
        UserDetailsImpl userDetails = new UserDetailsImpl();
        if (null != source) {
            userDetails.setUsername(source.getUserName());
            userDetails.setPassword(source.getPassword());
            userDetails.setEnabled(source.isEnabled());
            Collection<GrantedAuthority> authorities = new ArrayList<>();
            for (Role role : source.getRoles()) {
                authorities.add(new SimpleGrantedAuthority(role.getRole()));
            }
            userDetails.setAuthorities(authorities);
        }
        return userDetails;
    }
}
