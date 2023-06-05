package com.example.blog_system.Repository;

import com.example.blog_system.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {


    User findUserByUsername(String username);

    User findUserById(Integer id);
}
