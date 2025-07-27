package com.cardverse.userapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDateTime;

@Entity
@Table(name="cv_users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(length=100, nullable=false)
    private String userName;

    @Column(length=100, nullable=false, unique=true)
    private String userEmail;

    @Column(length=15, nullable=false)
    private String userMobile;

    @Column(nullable=false)
    private String userPwdHash;
    private LocalDateTime created_at;
    private LocalDateTime modified_at;
}
