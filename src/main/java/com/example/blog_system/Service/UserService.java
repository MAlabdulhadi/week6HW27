package com.example.blog_system.Service;

import com.example.blog_system.Model.Blog;
import com.example.blog_system.Model.User;
import com.example.blog_system.Repository.BlogRepository;
import com.example.blog_system.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BlogRepository blogRepository;

    public void register(User user) {
        String hash = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setRole("USER");
        user.setPassword(hash);
        userRepository.save(user);
    }

    public void updateUser(Integer userId, User user) {
        User oldUser = userRepository.findUserById(userId);
        oldUser.setUsername(user.getUsername());
        String hash = new BCryptPasswordEncoder().encode(user.getPassword());
        oldUser.setPassword(hash);
        userRepository.save(oldUser);
    }

    public void deleteUser(Integer idUser) {

        User user = userRepository.findUserById(idUser);
        List<Blog> blogs = blogRepository.findBlogsByUser(user);
        for (int i = 0; i < blogs.size(); i++) {
            blogs.get(i).setUser(null);
        }
        userRepository.delete(user);
    }

}
