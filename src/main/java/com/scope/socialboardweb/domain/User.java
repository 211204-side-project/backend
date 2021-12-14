package com.scope.socialboardweb.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;


@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String userId;
    @Column(nullable = false)
    private String nickname;
    @Column(nullable = false)
    private String password;

    // int? string?
    @Column(nullable = false)
    private String phoneNumber;
    @Column
    private String userImgUrl;
    @Column
    private Boolean isVerifiedEmail;

}
