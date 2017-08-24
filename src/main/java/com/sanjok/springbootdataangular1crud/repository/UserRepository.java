package com.sanjok.springbootdataangular1crud.repository;

import com.sanjok.springbootdataangular1crud.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User , Integer> {
    //@Query(value = "SELECT u FROM User u WHERE u.userName=?1 AND u.password=?2 ")
    public List<User> findTop1ByUserNameAndPassword(String userName, String password);
}
