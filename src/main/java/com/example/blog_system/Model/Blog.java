package com.example.blog_system.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
@Entity
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty(message = "title must be not empty")
//    @Size(min = 5, message = "title must be 5 or more")
    @Column(columnDefinition = "varchar(20) not null unique")
    private String title;
    @NotEmpty(message = "body must be not empty")
    @Column(columnDefinition = "varchar(500) not null")
//    @Size(min = 20, max = 500, message = "body must be between 20 and 500 ")
    private String body;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private User user;


}
