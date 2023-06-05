package com.example.blog_system.Controller;


import com.example.blog_system.Model.User;
import com.example.blog_system.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    //add
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody User user) {
        userService.register(user);
        return ResponseEntity.status(200).body("welcome user");
    }

    @PostMapping("/login")
    public ResponseEntity login() {
        return ResponseEntity.status(200).body("login");
    }

    @GetMapping("/logout")
    public ResponseEntity logout() {
        return ResponseEntity.status(200).body("logout");
    }

    @PutMapping("/update")
    public ResponseEntity updateUser(@AuthenticationPrincipal User olduser, @RequestBody User user) {
        userService.updateUser(olduser.getId(), user);
        return ResponseEntity.status(200).body("updated !");
    }

    @DeleteMapping("/delete")
    public ResponseEntity deleteUser(@AuthenticationPrincipal User user) {
        userService.deleteUser(user.getId());

        return ResponseEntity.status(200).body("deleted !");
    }


}
