package com.example.blog_system.Service;

import com.example.blog_system.ApiException.ApiException;
import com.example.blog_system.Model.Blog;
import com.example.blog_system.Model.User;
import com.example.blog_system.Repository.BlogRepository;
import com.example.blog_system.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogService {

    private final BlogRepository blogRepository;
    private final UserRepository userRepository;

    public List<Blog> getAllBlogs() {
        return blogRepository.findAll();
    }

    public List<Blog> getBlog(Integer userId) {
        List<Blog> blogList = blogRepository.findBlogsByUserId(userId);
        if (blogList.isEmpty()) {
            throw new ApiException("No have any blog for you");
        }
        return blogList;
    }

    public void addBlog(Integer userId, Blog blog) {
        blog.setUser(userRepository.findUserById(userId));
        blogRepository.save(blog);
    }

    public void updateBlog(Integer userId, Blog blog, Integer idBlog) {
        Blog oldBlog = blogRepository.findBlogById(idBlog);
        if (oldBlog == null) {
            throw new ApiException("do not have any blog for this id");
        }
        if (oldBlog.getUser().getId() != userId) {
            throw new ApiException("wrong do not have blog for this user");
        }
        oldBlog.setTitle(blog.getTitle());
        oldBlog.setBody(blog.getBody());
        blogRepository.save(oldBlog);
    }

    public void deleteBlog(Integer userId, Integer idBlog) {
        Blog blog = blogRepository.findBlogById(idBlog);
        blog.setUser(null);
        blogRepository.delete(blog);
    }


    public Blog getBlogByIdBlog(Integer idBlog) {
        Blog blog = blogRepository.findBlogById(idBlog);
        if (blog == null)
            throw new ApiException("do not have any blog by this id");

        return blog;
    }

    public Blog getBlogByTitle(String title) {
        Blog blog = blogRepository.findBlogByTitle(title);
        if (blog == null)
            throw new ApiException("do not have any blog by this id");

        return blog;
    }
}
