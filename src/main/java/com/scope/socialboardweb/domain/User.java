package com.scope.socialboardweb.domain;

import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
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

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Post> postList = new ArrayList<>();

}
