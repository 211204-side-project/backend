package com.scope.socialboardweb.domain;

import com.scope.socialboardweb.dto.UserRequestDto;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@RequiredArgsConstructor
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

    public User(UserRequestDto userRequestDto) {
        this.userId = userRequestDto.getUserId();
        this.nickname = userRequestDto.getNickname();
        this.password = userRequestDto.getPassword();
        this.phoneNumber = userRequestDto.getPhoneNumber();
        if(userRequestDto.getUserImgUrl() != null) this.userImgUrl = userRequestDto.getUserImgUrl();
        this.isVerifiedEmail = false;
    }

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Post> postList = new ArrayList<>();



}
