package com.sanjok.springbootdataangular1crud.repository;

import com.sanjok.springbootdataangular1crud.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // @Query(value = "SELECT u FROM User u WHERE u.userName=?1 AND u.password=?2 ")
    public User findByUserNameAndPassword(@Param("userName") String userName, @Param("password") String password);

    public User findByUserName(String userName);
}
