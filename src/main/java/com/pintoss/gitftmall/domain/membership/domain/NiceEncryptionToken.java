package com.pintoss.gitftmall.domain.membership.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NiceEncryptionToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String tokenVersionId;

    @Column(unique = true, nullable = false)
    private String symmetricKey;

    @Column(unique = true, nullable = false)
    private String initialVector;

    private LocalDateTime expiredAt;


}
