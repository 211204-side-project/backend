package com.scope.socialboardweb.domain;

import com.scope.socialboardweb.dto.UserRequestDto;
import com.scope.socialboardweb.repository.UserRepository;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(nullable = false)
    private String accountId;
    @Column(nullable = false)
    private String nickname;
    @Column(nullable = false)
    @JsonIgnore
    private String password;
    @Column(nullable = false)
    private String phoneNumber;
    @Column
    private String userImgUrl;
    @Column
    private Boolean isVerifiedEmail;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Post> postList = new ArrayList<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Comment> commentList = new ArrayList<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Alert> alertList = new ArrayList<>();

    public User(String accountId, String nickname, String password, String phoneNumber) {
        this.accountId = accountId;
        this.nickname = nickname;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }

    public User(UserRequestDto userRequestDto) {
        this.accountId = userRequestDto.getAccountId();
        this.nickname = userRequestDto.getNickname();
        this.password = userRequestDto.getPassword();
        this.phoneNumber = userRequestDto.getPhoneNumber();
        if(userRequestDto.getUserImgUrl() != null) this.userImgUrl = userRequestDto.getUserImgUrl();
        this.isVerifiedEmail = false;
    }


}
