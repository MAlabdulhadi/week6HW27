package com.example.blog_system.Repository;


import com.example.blog_system.Model.Blog;
import com.example.blog_system.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public interface BlogRepository extends JpaRepository<Blog, Integer> {


    List<Blog> findBlogsByUserId(Integer userId);

    Blog findBlogById(Integer idBlog);

    List<Blog> findBlogsByUser(User user);

    Blog findBlogByTitle(String title);

}
