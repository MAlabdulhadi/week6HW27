package com.example.blog_system.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    //    @Size(min = 5, max = 12, message = "username must be between 5 and 12")
    @NotEmpty(message = "username must be not empty")
    @Column(columnDefinition = "varchar(20) not null unique")
    private String username;
    //    @Size(min = 5, message = "password must be 5 or more")
    @NotEmpty(message = "password must be not empty")
//    @Column(columnDefinition = "varchar(100) not null")
    private String password;
    private String role;


    @OneToMany(mappedBy = "user", cascade = CascadeType.DETACH)
    private Set<Blog> blogs;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(this.role));

    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
