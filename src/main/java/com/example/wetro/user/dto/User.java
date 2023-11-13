package com.example.wetro.user.dto;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Entity
@Getter
@Setter
public class User {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String userid;

    @NotEmpty
    private String password;

    @NotNull
    @Email
    private String email;

    public User() {
    }

    public User( String userid, String password, String email) {
        this.userid = userid;
        this.password = password;
        this.email = email;
    }
}
