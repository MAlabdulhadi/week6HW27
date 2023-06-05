package com.example.blog_system.Controller;

import com.example.blog_system.Model.Blog;
import com.example.blog_system.Model.User;
import com.example.blog_system.Service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/blog")
@RequiredArgsConstructor
public class BlogController {

    private final BlogService blogService;

    @GetMapping("/get-all")
    public ResponseEntity getAllBlogs() {
        return ResponseEntity.status(200).body(blogService.getAllBlogs());
    }

    @GetMapping("/get")
    public ResponseEntity getBlogsForUser(@AuthenticationPrincipal User user) {
        List<Blog> blogs = blogService.getBlog(user.getId());
        return ResponseEntity.status(200).body(blogs);
    }

    @PostMapping("/add")
    public ResponseEntity addBlog(@AuthenticationPrincipal User user, @RequestBody Blog blog) {
        blogService.addBlog(user.getId(), blog);
        return ResponseEntity.status(200).body("Blog add");
    }

    @PutMapping("/update/{idBlog}")
    public ResponseEntity updateBlog(@AuthenticationPrincipal User user, @RequestBody Blog blog, @PathVariable Integer idBlog) {
        blogService.updateBlog(user.getId(), blog, idBlog);
        return ResponseEntity.status(200).body("done updated !");
    }

    @DeleteMapping("/delete/{idBlog}")
    public ResponseEntity deleteBlog(@AuthenticationPrincipal User user, @PathVariable Integer idBlog) {
        blogService.deleteBlog(user.getId(), idBlog);
        return ResponseEntity.status(200).body("blog deleted");
    }

    @GetMapping("/get-by-id/{idBlog}")
    public ResponseEntity getBlogById(@PathVariable Integer idBlog) {

        return ResponseEntity.status(200).body(blogService.getBlogByIdBlog(idBlog));

    }

    @GetMapping("/get-by-title/{title}")
    public ResponseEntity getBlogByTitle(@PathVariable String title) {
        return ResponseEntity.status(200).body(blogService.getBlogByTitle(title));
    }


}
