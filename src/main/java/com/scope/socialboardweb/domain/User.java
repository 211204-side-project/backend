package com.scope.socialboardweb.domain;

import com.scope.socialboardweb.dto.UserRequestDto;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(nullable = false)
    private String userId;
    @Column(nullable = false)
    private String nickname;
    @Column(nullable = false)
    @JsonIgnore
    private String password;
    @Column(nullable = false)
    private String phoneNumber;
    @Column(name = "profileImg")
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

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Post> postList = new ArrayList<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Comment> commentList = new ArrayList<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Alert> alertList = new ArrayList<>();

    /* TODO
    Follow와 ChatRoom에 대해, 양방향 관계로 할건지 의논 필요
     */


}
