package com.pintoss.gitftmall.domain.membership.model;

import com.pintoss.gitftmall.domain.membership.model.value.*;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    @ElementCollection
    @CollectionTable(name="user_roles", joinColumns = @JoinColumn(name = "user_id"))
    private Set<UserRole> roles = new HashSet<>();

    @Column(name = "oauth_provider", nullable = false)
    @Enumerated(EnumType.STRING)
    private OAuthProvider oAuthProvider;

    private LocalDateTime createdAt;

    private User(Email email, Password password, String name, Phone phone, Set<UserRole> roles, OAuthProvider oAuthProvider){
        this.email = email;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.roles = roles;
        this.oAuthProvider = oAuthProvider;
        this.createdAt = LocalDateTime.now();
    }

    public static User create(Email email, String rawPassword, String name, Phone phone, Set<UserRole> roles, PasswordEncoder encoder) {
        return new User(
                email,
                new Password(rawPassword, encoder),
                name,
                phone,
                roles,
                OAuthProvider.NONE
        );
    }

    public static User createOAuthUser(Email email, String name, Set<UserRole> roles, OAuthProvider provider) {
        Password dummyPassword = new Password("Oauthuser0!", new BCryptPasswordEncoder());

        return new User(
                email,
                dummyPassword,
                name,
                new Phone("010-0000-0000"),
                roles,
                provider
        );
    }
}
