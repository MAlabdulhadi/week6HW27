package com.example.blog_system.Config;


import com.example.blog_system.Service.MyUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SpringConfiguration {

    private final MyUserDetailsService myUserDetailsService;

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(myUserDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and()
                .authenticationProvider(authenticationProvider())
                .authorizeHttpRequests()
                .requestMatchers("/api/v1/user/register").permitAll()
                .requestMatchers("/api/v1/user/login").permitAll()
                .requestMatchers("/api/v1/user/logout").permitAll()
                .requestMatchers("/api/v1/user/update").hasAuthority("USER")
                .requestMatchers("/api/v1/user/delete").hasAuthority("USER")
                .requestMatchers("/api/v1/blog/get-all").permitAll()
                .requestMatchers("/api/v1/blog/get-by-id/{idBlog}").permitAll()
                .requestMatchers("/api/v1/blog/get-by-title/{title}").permitAll()
                .requestMatchers("/api/v1/blog/get").hasAuthority("USER")
                .requestMatchers("/api/v1/blog/add").hasAuthority("USER")
                .requestMatchers("/api/v1/blog/update/{idBlog}").hasAuthority("USER")
                .requestMatchers("/api/v1/blog/delete/{idBlog}").hasAuthority("USER")


                .anyRequest().authenticated()
                .and()
                .logout().logoutUrl("/api/v1/user/logout")
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
                .and()
                .httpBasic();
        return http.build();

    }


}
