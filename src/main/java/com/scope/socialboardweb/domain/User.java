package com.scope.socialboardweb.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String userId;

    private String nickname;

    private String password;

    // int? string?
    private String phoneNumber;

    private Boolean isVerifiedEmail;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Post> postList = new ArrayList<>();

}
