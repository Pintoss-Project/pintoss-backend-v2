package com.pintoss.gitftmall.domain.membership.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String password;

    private String name;

    private String phone;

    private LocalDateTime createdAt;

    private User(String email, String password, String name, String phone){
        this.email = email;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.createdAt = LocalDateTime.now();
    }
    public static User create(String email, String password, String name, String phone) {
        return new User(email, password, name, phone);
    }

}
