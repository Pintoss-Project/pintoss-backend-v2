package com.pintoss.gitftmall.domain.membership.domain;

import com.pintoss.gitftmall.domain.membership.domain.vo.*;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    @AttributeOverrides(
            @AttributeOverride(name = "email", column = @Column(name = "email", nullable = false, unique = true))
    )
    private Email email;

    @Embedded
    @AttributeOverrides(
            @AttributeOverride(name = "password", column = @Column(name = "password", nullable = false))
    )
    private Password password;

    @Column(nullable = false)
    private String name;

    @Embedded
    @AttributeOverrides(
            @AttributeOverride(name = "phone", column = @Column(name = "phone", nullable = false, unique = true))
    )
    private Phone phone;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name="user_roles", joinColumns = @JoinColumn(name = "user_id"))
    private Set<UserRole> roles = new HashSet<>();

    private String refreshToken;

    private LocalDateTime createdAt;

    private User(Email email, Password password, String name, Phone phone, Set<UserRole> roles){
        this.email = email;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.roles = roles;
        this.createdAt = LocalDateTime.now();
    }

    public static User create(Email email, String rawPassword, String name, Phone phone, Set<UserRole> roles, PasswordEncoder encoder) {
        return new User(
                email,
                new Password(rawPassword, encoder),
                name,
                phone,
                roles
        );
    }

    public void storeRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }


    public boolean isEqualsRefreshToken(String refreshToken) {
        return this.refreshToken.equals(refreshToken);
    }
}
